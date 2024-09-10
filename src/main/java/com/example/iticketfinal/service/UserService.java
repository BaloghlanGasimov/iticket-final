package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.*;
import com.example.iticketfinal.dao.repository.*;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.dto.payment.PaymentReqDto;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.enums.OperationStatus;
import com.example.iticketfinal.exceptions.*;
import com.example.iticketfinal.mapper.CountryMapper;
import com.example.iticketfinal.mapper.PhoneMapper;
import com.example.iticketfinal.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;
    private final PhoneMapper phoneMapper;

    public BaseResponseDto<UserRespDto> saveUserPrimary(UserPrimaryLoginReqDto userPrimaryLoginReqDto){
        log.info("ActionLog.saveUser.start userPrimaryLoginReqDto: {}",userPrimaryLoginReqDto);

        UserEntity userEntity = userMapper.mapToEntity(userPrimaryLoginReqDto);
        userRepository.save(userEntity);
        UserRespDto userRespDto = userMapper.mapToRespDto(userEntity);

        log.info("ActionLog.saveUser.end userPrimaryLoginReqDto: {}",userPrimaryLoginReqDto);
        return BaseResponseDto.success(userRespDto);
    }

    @Transactional
    public BaseResponseDto<UserRespDto> updateUser(Long id, UserLoginReqDto userLoginReqDto){
        log.info("ActionLog.updateUser.start id: {}, userLoginReqDto: {}",id,userLoginReqDto);

        UserEntity userEntity = findUser(id);

        if(userLoginReqDto.getName()!=null){
            userEntity.setName(userLoginReqDto.getName());
        }
        if(userLoginReqDto.getSurname()!=null){
            userEntity.setSurname(userLoginReqDto.getSurname());
        }
        if(userLoginReqDto.getEmail()!=null){
            userEntity.setEmail(userLoginReqDto.getEmail());
        }
        if(userLoginReqDto.getBirthDate()!=null){
            userEntity.setBirthDate(userLoginReqDto.getBirthDate());
        }
        if(userLoginReqDto.getGender()!=null){
            userEntity.setGender(userLoginReqDto.getGender());
        }
        if(userLoginReqDto.getCountryId()!=null){
            CountryEntity countryEntity = countryRepository.findById((long)userLoginReqDto.getCountryId())
                    .orElse(null);

            userEntity.setCountry(countryEntity);
        }
        if(userLoginReqDto.getPhones()!=null){
            List<PhoneEntity> phones = userLoginReqDto.getPhones().stream().map(phoneMapper::mapToEntity).collect(Collectors.toList());
            for (PhoneEntity phone : phones) {
                phone.setUser(userEntity);
            }
            userEntity.setPhones(phones);
        }
        userRepository.save(userEntity);
        UserRespDto userRespDto = userMapper.mapToRespDto(userEntity);
        log.info("ActionLog.updateUser.end id: {} userLoginReqDto: {}",id,userLoginReqDto);

        return BaseResponseDto.success(userRespDto);
    }

    public BaseResponseDto<List<UserRespDto>> getUsers(){
        log.info("ActionLog.getUsers.start");

        List<UserEntity> userEntities = userRepository.findAll();
        List<UserRespDto> userRespDtos = userEntities.stream().map(userMapper::mapToRespDto).collect(Collectors.toList());

        log.info("ActionLog.getUsers.end");
        return BaseResponseDto.success(userRespDtos);
    }

    public BaseResponseDto<UserRespDto> getUserById(Long id){
        log.info("ActionLog.getUserById.start id: {}",id);

        UserEntity userEntity = findUser(id);
        UserRespDto userRespDto = userMapper.mapToRespDto(userEntity);

        log.info("ActionLog.getUserById.end id: {}",id);
        return BaseResponseDto.success(userRespDto);
    }


    public BaseResponseDto<UserRespDto> deleteUser(Long id){
        log.info("ActionLog.deleteUser.start id: {}",id);

        UserEntity userEntity = findUser(id);
        UserRespDto userRespDto = userMapper.mapToRespDto(userEntity);
        userRepository.deleteById(id);

        log.info("ActionLog.deleteUser.end id: {}",id);
        return BaseResponseDto.success(userRespDto);
    }

    public BaseResponseDto<UserRespDto> addToWallet(Long id, double money){
        log.info("ActionLog.addToWallet.start id: {} money: {}",id,money);

        UserEntity user = findUser(id);

        if(money<=0){
            throw new NegativeMoneyException(
                    "Request money is negative or zero",
                    String.format("ActionLog.addToWallet.error NegativeMoneyException: money: %f",money)
            );
        }

        WalletEntity wallet = user.getWallet();
        double newBalance = wallet.getBalance()+money;
        wallet.setBalance(newBalance);
        user.setWallet(wallet);
        userRepository.save(user);

        UserRespDto userRespDto = userMapper.mapToRespDto(user);
        log.info("ActionLog.addToWallet.end id: {} money: {}",id,money);

        return BaseResponseDto.success(userRespDto) ;
    }

    @Transactional
    public void buyTicketsOfEventByWallet(Long userId, Long eventId, PaymentReqDto paymentReqDto){
        log.info("ActionLog.buyTicketsOfEventByWallet.start userId: {},eventId: {}, paymentReqDto: {}",userId,eventId,paymentReqDto);

        UserEntity userEntity = findUser(userId);

        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(()->new NotFoundException(
                        Exceptions.EVENT_NOT_FOUND.toString(),
                        String.format("ActionLog.buyTicketsOfEventByWallet.error NotFoundEvent eventId: %d",eventId)
                ));

        TicketEntity wantBuyTicket=null;
        boolean isNot = true;
        for(TicketEntity ticket : eventEntity.getTickets()){
            if(ticket.getCategory().equals(paymentReqDto.getCategory())){
                wantBuyTicket=ticket;
                isNot=false;
                break;
            }
        }

        if(isNot){
            String errorMessage = "That event doesn't have that category ticket";
            throw new NotCategoryException(
                    errorMessage,
                    String.format("ActionLog.buyTicketsOfEventByWallet.error NotCategoryException: eventId: %d,category: %s",eventId,paymentReqDto.getCategory())
            );
        }

        Double userMoney = userEntity.getWallet().getBalance();
        Double totalTicketMoney = wantBuyTicket.getPrice()*paymentReqDto.getCount();
        PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
        paymentHistoryEntity.setUser(userEntity);
        paymentHistoryEntity.setEvent(eventEntity);
        paymentHistoryEntity.setTicket(wantBuyTicket);
        paymentHistoryEntity.setTicketCount(paymentReqDto.getCount());
        paymentHistoryEntity.setTotalPayment(totalTicketMoney);


        if(paymentReqDto.getCount()>wantBuyTicket.getCount()){
            String errorMessage = "Your count is upper than ticket count";
            paymentHistoryEntity.setErrorMessage(errorMessage);
            paymentHistoryEntity.setStatus(OperationStatus.FAILURE);
            paymentHistoryRepository.save(paymentHistoryEntity);

            throw new MaxLimitExceededException(
                    errorMessage,
                    String.format("ActionLog.buyTicketsOfEventByWallet.error MaxLimitExceededException: eventId: %d,request count: %d,ticket count: %d",eventId,paymentReqDto.getCount(),wantBuyTicket.getCount())
            );
        }

        else if(userMoney<totalTicketMoney){
            String errorMessage = "Balance is not enough";
            paymentHistoryEntity.setErrorMessage(errorMessage);
            paymentHistoryEntity.setStatus(OperationStatus.FAILURE);
            paymentHistoryRepository.save(paymentHistoryEntity);

            throw new NotEnoughBalanceException(
                    errorMessage,
                    String.format("ActionLog.buyTicketsOfEventByWallet.error NotEnoughBalanceException: eventId: %d,user money: %f,totalTicketMoney: %f",eventId,userMoney,totalTicketMoney)
            );
        }

        else{
            Double afterPaymentBalance = userMoney-totalTicketMoney;
            WalletEntity walletEntity = userEntity.getWallet();
            walletEntity.setBalance(afterPaymentBalance);
            userEntity.setWallet(walletEntity);

            Integer afterBuyCount = wantBuyTicket.getCount()-paymentReqDto.getCount();
            wantBuyTicket.setCount(afterBuyCount);
            userRepository.save(userEntity);
            ticketRepository.save(wantBuyTicket);
            paymentHistoryEntity.setStatus(OperationStatus.SUCCESS);
            paymentHistoryRepository.save(paymentHistoryEntity);
        }

        log.info("ActionLog.buyTicketsOfEventByWallet.end userId: {},eventId: {}, paymentReqDto: {}",userId,eventId,paymentReqDto);
    }

    private UserEntity findUser(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        Exceptions.USER_NOT_FOUND.toString(),
                        String.format("ActionLog.findUser.error NotFound id: %d",id)
                ));
        return user;
    }

}

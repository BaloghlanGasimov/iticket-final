package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.*;
import com.example.iticketfinal.dao.repository.*;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.payment.PaymentReqDto;
import com.example.iticketfinal.dto.payment.PaymentRespDto;
import com.example.iticketfinal.dto.ticket.TicketRespDto;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.enums.OperationStatus;
import com.example.iticketfinal.enums.Roles;
import com.example.iticketfinal.exceptions.*;
import com.example.iticketfinal.mapper.CommonMapper;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final CommonMapper commonMapper;
    private final CountryRepository countryRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailService myUserDetailService;

    public BaseResponseDto<UserRespDto> saveUserPrimary(UserPrimaryLoginReqDto userPrimaryLoginReqDto) {
        log.info("ActionLog.saveUser.start userPrimaryLoginReqDto: {}", userPrimaryLoginReqDto);

        UserEntity checkUserEntity = userRepository.findByEmail(userPrimaryLoginReqDto.getEmail())
                .orElse(null);

        if(checkUserEntity == null) {
            UserEntity userEntity = commonMapper.mapToEntity(userPrimaryLoginReqDto);
            userEntity.setRole(Roles.USER);
            userEntity.setPassword(passwordEncoder.encode(userPrimaryLoginReqDto.getPassword()));
            userRepository.save(userEntity);
            UserRespDto userRespDto = commonMapper.mapToRespDto(userEntity);

            log.info("ActionLog.saveUser.end userPrimaryLoginReqDto: {}", userPrimaryLoginReqDto);
            return BaseResponseDto.success(userRespDto);

        }else{
            throw new UserRegisteredException(
                    Exceptions.EMAIL_REGISTERED.name(),
                    String.format("ActionLog.saveUserPrimary.error email:%s",userPrimaryLoginReqDto.getEmail())
            );
        }
    }

    @Transactional
    public BaseResponseDto<UserRespDto> updateUser(UserLoginReqDto userLoginReqDto) {
        log.info("ActionLog.updateUser.start, userLoginReqDto: {}", userLoginReqDto);

//        UserEntity userEntity = findUser(id);
        UserEntity userEntity = myUserDetailService.getCurrentAuthenticatedUser();
        if (userLoginReqDto.getName() != null) {
            userEntity.setName(userLoginReqDto.getName());
        }
        if (userLoginReqDto.getSurname() != null) {
            userEntity.setSurname(userLoginReqDto.getSurname());
        }
        if (userLoginReqDto.getEmail() != null) {
            userEntity.setEmail(userLoginReqDto.getEmail());
        }
        if (userLoginReqDto.getBirthDate() != null) {
            userEntity.setBirthDate(userLoginReqDto.getBirthDate());
        }
        if (userLoginReqDto.getGender() != null) {
            userEntity.setGender(userLoginReqDto.getGender());
        }
        if (userLoginReqDto.getCountryId() != null) {
            CountryEntity countryEntity = countryRepository.findById((long) userLoginReqDto.getCountryId())
                    .orElse(null);

            userEntity.setCountry(countryEntity);
        }
        if (userLoginReqDto.getPhones() != null) {
            List<PhoneEntity> phones = userLoginReqDto.getPhones().stream().map(commonMapper::mapToEntity).collect(Collectors.toList());
            for (PhoneEntity phone : phones) {
                phone.setUser(userEntity);
            }
            userEntity.setPhones(phones);
        }
        userRepository.save(userEntity);
        UserRespDto userRespDto = commonMapper.mapToRespDto(userEntity);
        log.info("ActionLog.updateUser.end id: {} userLoginReqDto: {}", userEntity.getId(), userLoginReqDto);

        return BaseResponseDto.success(userRespDto);
    }

    public BaseResponseDto<List<UserRespDto>> getUsers() {
        log.info("ActionLog.getUsers.start");

        List<UserEntity> userEntities = userRepository.findAll();
        List<UserRespDto> userRespDtos = userEntities.stream().map(commonMapper::mapToRespDto).collect(Collectors.toList());

        log.info("ActionLog.getUsers.end");
        return BaseResponseDto.success(userRespDtos);
    }

    public BaseResponseDto<UserRespDto> getUserById() {
        log.info("ActionLog.getUserById.start");

        UserEntity userEntity = myUserDetailService.getCurrentAuthenticatedUser();
        UserRespDto userRespDto = commonMapper.mapToRespDto(userEntity);

        log.info("ActionLog.getUserById.end id: {}", userEntity.getId());
        return BaseResponseDto.success(userRespDto);
    }

    public BaseResponseDto<List<PaymentRespDto>> getPaymentTicketUser(OperationStatus status){
        log.info("ActionLog.getPurchasedTicketUser.start");

        UserEntity userEntity = myUserDetailService.getCurrentAuthenticatedUser();
        List<PaymentHistoryEntity> paymentHistoryEntities = paymentHistoryRepository.findByUserId(userEntity.getId());
        paymentHistoryEntities.stream().filter((payment)-> payment==null || payment.getStatus()==status);
        List<PaymentRespDto> paymentRespDtos = paymentHistoryEntities.stream().map(commonMapper::mapToDto).toList();

        log.info("ActionLog.getPurchasedTicketUser.end id: {}", userEntity.getId());
        return BaseResponseDto.success(paymentRespDtos);
    }

    public BaseResponseDto<UserRespDto> deleteUser() {
        log.info("ActionLog.deleteUser.start ");

        UserEntity userEntity = myUserDetailService.getCurrentAuthenticatedUser();
        UserRespDto userRespDto = commonMapper.mapToRespDto(userEntity);
        userRepository.delete(userEntity);

        log.info("ActionLog.deleteUser.end id: {}",userEntity.getId());
        return BaseResponseDto.success(userRespDto);
    }

    public BaseResponseDto<UserRespDto> addToWallet(double money) {
        log.info("ActionLog.addToWallet.start money: {}", money);

        UserEntity user = myUserDetailService.getCurrentAuthenticatedUser();

        if (money <= 0) {
            throw new NegativeMoneyException(
                    "Request money is negative or zero",
                    String.format("ActionLog.addToWallet.error NegativeMoneyException: money: %f", money)
            );
        }

        WalletEntity wallet = user.getWallet();
        double newBalance = wallet.getBalance() + money;
        wallet.setBalance(newBalance);
        user.setWallet(wallet);
        userRepository.save(user);

        UserRespDto userRespDto = commonMapper.mapToRespDto(user);
        log.info("ActionLog.addToWallet.end id: {} money: {}", user.getId(), money);

        return BaseResponseDto.success(userRespDto);
    }

    @Transactional
    public void buyTicketsOfEventByWallet(Long eventId, PaymentReqDto paymentReqDto) {
        log.info("ActionLog.buyTicketsOfEventByWallet.start eventId: {}, paymentReqDto: {}", eventId, paymentReqDto);

        UserEntity userEntity = myUserDetailService.getCurrentAuthenticatedUser();

        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(
                        Exceptions.EVENT_NOT_FOUND.toString(),
                        String.format("ActionLog.buyTicketsOfEventByWallet.error NotFoundEvent eventId: %d", eventId)
                ));

        TicketEntity wantBuyTicket = null;
        boolean isNot = true;
        for (TicketEntity ticket : eventEntity.getTickets()) {
            if (ticket.getCategory().equals(paymentReqDto.getCategory())) {
                wantBuyTicket = ticket;
                isNot = false;
                break;
            }
        }

        if (isNot) {
            String errorMessage = "That event doesn't have that category ticket";
            throw new NotCategoryException(
                    errorMessage,
                    String.format("ActionLog.buyTicketsOfEventByWallet.error NotCategoryException: eventId: %d,category: %s", eventId, paymentReqDto.getCategory())
            );
        }

        Double userMoney = userEntity.getWallet().getBalance();
        Double totalTicketMoney = wantBuyTicket.getPrice() * paymentReqDto.getCount();
        PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
        paymentHistoryEntity.setUser(userEntity);
        paymentHistoryEntity.setEvent(eventEntity);
        paymentHistoryEntity.setTicket(wantBuyTicket);
        paymentHistoryEntity.setTicketCount(paymentReqDto.getCount());
        paymentHistoryEntity.setTotalPayment(totalTicketMoney);


        if (paymentReqDto.getCount() > wantBuyTicket.getCount()) {
            String errorMessage = "Your count is upper than ticket count";
            paymentHistoryEntity.setErrorMessage(errorMessage);
            paymentHistoryEntity.setStatus(OperationStatus.FAILURE);
            paymentHistoryRepository.save(paymentHistoryEntity);

            throw new MaxLimitExceededException(
                    errorMessage,
                    String.format("ActionLog.buyTicketsOfEventByWallet.error MaxLimitExceededException: eventId: %d,request count: %d,ticket count: %d", eventId, paymentReqDto.getCount(), wantBuyTicket.getCount())
            );
        } else if (userMoney < totalTicketMoney) {
            String errorMessage = "Balance is not enough";
            paymentHistoryEntity.setErrorMessage(errorMessage);
            paymentHistoryEntity.setStatus(OperationStatus.FAILURE);
            paymentHistoryRepository.save(paymentHistoryEntity);

            throw new NotEnoughBalanceException(
                    errorMessage,
                    String.format("ActionLog.buyTicketsOfEventByWallet.error NotEnoughBalanceException: eventId: %d,user money: %f,totalTicketMoney: %f", eventId, userMoney, totalTicketMoney)
            );
        } else {
            Double afterPaymentBalance = userMoney - totalTicketMoney;
            WalletEntity walletEntity = userEntity.getWallet();
            walletEntity.setBalance(afterPaymentBalance);
            userEntity.setWallet(walletEntity);

            Integer afterBuyCount = wantBuyTicket.getCount() - paymentReqDto.getCount();
            wantBuyTicket.setCount(afterBuyCount);
            userRepository.save(userEntity);
            ticketRepository.save(wantBuyTicket);
            paymentHistoryEntity.setStatus(OperationStatus.SUCCESS);
            paymentHistoryRepository.save(paymentHistoryEntity);

            try {
//                emailService.postEmailWithAttachment(userEntity.getEmail(),"Your tickets","Thanks for buying");
                for (int i = 0; i < paymentReqDto.getCount(); i++) {
                    emailService.postEmailWithAttachmentv2(userEntity.getEmail(), "Your tickets", eventEntity.getTitle(), wantBuyTicket.getCategory().toString(), wantBuyTicket.getPrice());
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("ActionLog.buyTicketsOfEventByWallet.end userId: {},eventId: {}, paymentReqDto: {}", userEntity.getId(), eventId, paymentReqDto);
    }

    private UserEntity findUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        Exceptions.USER_NOT_FOUND.toString(),
                        String.format("ActionLog.findUser.error NotFound id: %d", id)
                ));
        return user;
    }

}

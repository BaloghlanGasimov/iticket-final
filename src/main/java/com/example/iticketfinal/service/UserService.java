package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dao.repository.UserRepository;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.CountryMapper;
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
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CountryMapper countryMapper;

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
        if(userLoginReqDto.getPhone()!=null){
            userEntity.setPhone(userLoginReqDto.getPhone());
        }
        if(userLoginReqDto.getBirthDate()!=null){
            userEntity.setBirthDate(userLoginReqDto.getBirthDate());
        }
        if(userLoginReqDto.getGender()!=null){
            userEntity.setGender(userLoginReqDto.getGender());
        }
        if(userLoginReqDto.getCountry()!=null){
            CountryEntity countryEntity =countryMapper.mapToEntity(userLoginReqDto.getCountry());
            userEntity.setCountry(countryEntity);
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

    private UserEntity findUser(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        Exceptions.USER_NOT_FOUND.toString(),
                        String.format("ActionLog.findUser.error NotFound id: %d",id)
                ));
        return user;
    }

}

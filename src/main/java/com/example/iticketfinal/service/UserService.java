package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dao.repository.UserRepository;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.UserMapper;
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

    public void saveUser(UserPrimaryLoginReqDto userPrimaryLoginReqDto){
        log.info("ActionLog.saveUser.start userPrimaryLoginReqDto: {}",userPrimaryLoginReqDto);

        UserEntity userEntity = userMapper.mapToEntity(userPrimaryLoginReqDto);
        userRepository.save(userEntity);

        log.info("ActionLog.saveUser.end userPrimaryLoginReqDto: {}",userPrimaryLoginReqDto);
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

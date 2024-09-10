package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/primary")
    public BaseResponseDto<UserRespDto> saveUserPrimary(
            @Valid @RequestBody UserPrimaryLoginReqDto userPrimaryLoginReqDto
    ){
        return userService.saveUserPrimary(userPrimaryLoginReqDto);
    }

    @PutMapping("/{id}")
    public BaseResponseDto<UserRespDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserLoginReqDto userLoginReqDto
            ){
        return userService.updateUser(id,userLoginReqDto);
    }

    @GetMapping
    public BaseResponseDto<List<UserRespDto>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public BaseResponseDto<UserRespDto> getUserById(
            @PathVariable Long id
    ){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto<UserRespDto> deleteUser(
            @PathVariable Long id
    ){
        return userService.deleteUser(id);
    }

    @PatchMapping("/{id}/wallet/add")
    public BaseResponseDto<UserRespDto> addToWallet(
            @PathVariable Long id,
            @RequestParam double money
    ){
        return userService.addToWallet(id,money);
    }

}

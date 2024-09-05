package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/primary")
    public void saveUser(
            @Valid @RequestBody UserPrimaryLoginReqDto userPrimaryLoginReqDto
    ){
        userService.saveUser(userPrimaryLoginReqDto);
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

}

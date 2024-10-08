package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.payment.PaymentReqDto;
import com.example.iticketfinal.dto.payment.PaymentRespDto;
import com.example.iticketfinal.dto.ticket.TicketRespDto;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import com.example.iticketfinal.enums.OperationStatus;
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

    @PostMapping("/register/primary")
    public BaseResponseDto<UserRespDto> saveUserPrimary(
            @Valid @RequestBody UserPrimaryLoginReqDto userPrimaryLoginReqDto
    ) {
        return userService.saveUserPrimary(userPrimaryLoginReqDto);
    }

    @PutMapping("/{id}")
    public BaseResponseDto<UserRespDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserLoginReqDto userLoginReqDto
    ) {
        return userService.updateUser(id, userLoginReqDto);
    }

    @GetMapping
    public BaseResponseDto<List<UserRespDto>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public BaseResponseDto<UserRespDto> getUserById(
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/payments")
    public BaseResponseDto<List<PaymentRespDto>> getPaymentTicketUser(
            @PathVariable Long id,
            @RequestParam OperationStatus status
    ) {
        return userService.getPaymentTicketUser(id,status);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto<UserRespDto> deleteUser(
            @PathVariable Long id
    ) {
        return userService.deleteUser(id);
    }

    @PatchMapping("/{id}/wallet/add")
    public BaseResponseDto<UserRespDto> addToWallet(
            @PathVariable Long id,
            @RequestParam double money
    ) {
        return userService.addToWallet(id, money);
    }

    @PostMapping("/{userId}/buy/events/{eventId}/ticket")
    public void buyTicketsOfEventByWallet(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @Valid @RequestBody PaymentReqDto paymentReqDto
    ) {
        userService.buyTicketsOfEventByWallet(userId, eventId, paymentReqDto);
    }

}

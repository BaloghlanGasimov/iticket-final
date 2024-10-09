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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @PostMapping("user/register/primary")
    public BaseResponseDto<UserRespDto> saveUserPrimary(
            @Valid @RequestBody UserPrimaryLoginReqDto userPrimaryLoginReqDto
    ) {
        return userService.saveUserPrimary(userPrimaryLoginReqDto);
    }

    @PutMapping("/user")
    public BaseResponseDto<UserRespDto> updateUser(
            @Valid @RequestBody UserLoginReqDto userLoginReqDto
    ) {
        return userService.updateUser(userLoginReqDto);
    }

    @GetMapping("/users")
    public BaseResponseDto<List<UserRespDto>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user")
    public BaseResponseDto<UserRespDto> getUserById(
    ) {
        return userService.getUserById();
    }

    @GetMapping("/user/payments")
    public BaseResponseDto<List<PaymentRespDto>> getPaymentTicketUser(
            @RequestParam OperationStatus status
    ) {
        return userService.getPaymentTicketUser(status);
    }

    @DeleteMapping("/user")
    public BaseResponseDto<UserRespDto> deleteUser(
    ) {
        return userService.deleteUser();
    }

    @PatchMapping("/user/wallet/add")
    public BaseResponseDto<UserRespDto> addToWallet(
            @RequestParam double money
    ) {
        return userService.addToWallet(money);
    }

    @PostMapping("/user/buy/events/{eventId}/ticket")
    public void buyTicketsOfEventByWallet(
            @PathVariable Long eventId,
            @Valid @RequestBody PaymentReqDto paymentReqDto
    ) {
        userService.buyTicketsOfEventByWallet(eventId, paymentReqDto);
    }

}

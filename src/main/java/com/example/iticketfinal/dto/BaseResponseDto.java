package com.example.iticketfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDto<T> {
    private boolean success;
    private String message;
    private String messageId;
    private T data;

    public BaseResponseDto(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.messageId = UUID.randomUUID().toString();
        this.data = data;
    }

    public static <T> BaseResponseDto<T> success(T data) {
        return new BaseResponseDto<>(true, "Operation successful", data);
    }

    public static <T> BaseResponseDto<T> success(T data, String message) {
        return new BaseResponseDto<>(true, message, data);
    }

    public static <T> BaseResponseDto<T> error(String message) {
        return new BaseResponseDto<>(false, message, null);
    }
}

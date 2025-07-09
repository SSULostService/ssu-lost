package com.example.ssu_lost.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    //Common
    OK(HttpStatus.OK, "COMMON_200", "성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

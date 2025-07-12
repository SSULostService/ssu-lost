package com.example.ssu_lost.global.exception;

import com.example.ssu_lost.global.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class GeneralException extends RuntimeException{

    private final ErrorCode errorCode;

    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

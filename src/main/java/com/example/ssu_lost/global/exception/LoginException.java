package com.example.ssu_lost.global.exception;

import com.example.ssu_lost.global.code.ErrorCode;

public class LoginException extends GeneralException{

    public LoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}

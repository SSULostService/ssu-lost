package com.example.ssu_lost.global.exception;

import com.example.ssu_lost.global.code.ErrorCode;

public class TestException extends GeneralException{
    public TestException(ErrorCode code) {
        super(code);
    }
}

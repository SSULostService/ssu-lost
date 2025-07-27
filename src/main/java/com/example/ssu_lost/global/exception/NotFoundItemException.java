package com.example.ssu_lost.global.exception;


import com.example.ssu_lost.global.code.ErrorCode;

public class NotFoundItemException extends GeneralException {
    public NotFoundItemException() {
        super(ErrorCode._NOT_FOUND);
    }
}

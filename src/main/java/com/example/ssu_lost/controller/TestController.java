package com.example.ssu_lost.controller;

import com.example.ssu_lost.global.code.ErrorCode;
import com.example.ssu_lost.global.code.ResponseCode;
import com.example.ssu_lost.global.exception.TestException;
import com.example.ssu_lost.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @GetMapping("/execute/{example}")
    public ApiResponse<Long> test(@PathVariable(name = "example") Long error){

//        if(error.equals("yes")) {
//            throw new TestException(ErrorCode._NOT_FOUND);
//        }

        return ApiResponse.onSuccess(ResponseCode.OK, error);
    }
}

package com.example.ssu_lost.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    //Common
    OK(HttpStatus.OK, "COMMON_200", "성공입니다."),

    // LostItem
    SUCCESS_CREATE_LOST_ITEM(HttpStatus.CREATED, "LOST_ITEM_201", "분실물 등록 성공입니다."),
    SUCCESS_GET_LOST_ITEM(HttpStatus.OK, "LOST_ITEM_200", "분실물 단일 조회 성공입니다."),
    SUCCESS_GET_LOST_ITEM_LIST(HttpStatus.OK, "LOST_ITEM_200", "분실물 리스트 조회 성공입니다."),
    SUCCESS_DELETE_LOST_ITEM(HttpStatus.OK, "LOST_ITEM_200", "분실물 삭제 성공입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

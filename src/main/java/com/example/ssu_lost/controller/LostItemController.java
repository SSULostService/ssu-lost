package com.example.ssu_lost.controller;

import com.example.ssu_lost.dto.request.LostItemWriteDto;
import com.example.ssu_lost.dto.response.LostItemHomeResponseDto;
import com.example.ssu_lost.dto.response.LostItemListResponseDto;
import com.example.ssu_lost.dto.response.LostItemResponseDto;
import com.example.ssu_lost.global.code.ResponseCode;
import com.example.ssu_lost.global.response.ApiResponse;
import com.example.ssu_lost.service.LostItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lostitem")
public class LostItemController {

    private final LostItemService lostItemService;

    @PostMapping
    public ResponseEntity<ApiResponse<LostItemResponseDto>> createLostItem(
            @RequestBody LostItemWriteDto request
            // TO DO: 멤버 구현 시 결합, 임시로 memberId 사용
            ) {
        LostItemResponseDto createdItem = lostItemService.createLostItem(request);
        return ResponseEntity.ok(ApiResponse.onSuccess(ResponseCode.SUCCESS_CREATE_LOST_ITEM, createdItem));
    }

    @GetMapping("/{lostItemId}")
    public ResponseEntity<ApiResponse<LostItemResponseDto>> getLostItemById(@PathVariable Long lostItemId) {
        LostItemResponseDto foundItem = lostItemService.getLostItemById(lostItemId);
        return ResponseEntity.ok(ApiResponse.onSuccess(ResponseCode.SUCCESS_GET_LOST_ITEM, foundItem));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<LostItemListResponseDto>> getLostItemsForList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            // TO DO: 기획 확정 시 변경
    ) {
        LostItemListResponseDto items = lostItemService.getLostItemsForList(page, size);
        return ResponseEntity.ok(ApiResponse.onSuccess(ResponseCode.SUCCESS_GET_LOST_ITEM_LIST, items));
    }

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<LostItemHomeResponseDto>> getLostItemsForHome() {
        LostItemHomeResponseDto items = lostItemService.getLostItemForHome();
        return ResponseEntity.ok(ApiResponse.onSuccess(ResponseCode.SUCCESS_GET_LOST_ITEM_LIST, items));
    }

    @DeleteMapping("/{lostItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteLostItemById(@PathVariable Long lostItemId) {
        lostItemService.deleteLostItemById(lostItemId);
        return ResponseEntity.ok(ApiResponse.onSuccess(ResponseCode.SUCCESS_DELETE_LOST_ITEM, null));
    }
}

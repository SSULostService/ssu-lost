package com.example.ssu_lost.dto.response;

import com.example.ssu_lost.entity.LostItem;

import java.util.List;

public record LostItemHomeResponseDto(
        List<LostItemResponseDto>  lostItems
) {
    public static LostItemHomeResponseDto from(List<LostItem> lostItems) {
        return new LostItemHomeResponseDto(
                lostItems.stream()
                        .map(LostItemResponseDto::ofHome)
                        .toList()
        );
    }
}

package com.example.ssu_lost.dto.response;

import com.example.ssu_lost.entity.LostItem;
import org.springframework.data.domain.Page;

import java.util.List;

public record LostItemListResponseDto (
        List<LostItemResponseDto> lostItems,
        int currentPage,
        int totalPages,
        long elementsThisPage
){

    public static LostItemListResponseDto of (Page<LostItem> response){
        return new LostItemListResponseDto(
                response.getContent().stream()
                        .map(LostItemResponseDto::ofList)
                        .toList(),
                response.getNumber(),
                response.getTotalPages(),
                response.getTotalElements()
        );
    }
}

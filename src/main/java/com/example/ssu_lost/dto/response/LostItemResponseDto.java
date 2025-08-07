package com.example.ssu_lost.dto.response;

import com.example.ssu_lost.entity.LostItem;
import com.example.ssu_lost.entity.Member;
import com.example.ssu_lost.enums.ItemStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

public record LostItemResponseDto(
        Long itemId,
        String imgUrl,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String description,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Double latitude,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Double longitude,
        ItemStatus itemStatus,
        Member member
) {
    public static LostItemResponseDto ofDetail(LostItem lostItem) {
        return new LostItemResponseDto(
                lostItem.getId(),
                lostItem.getImgUrl(),
                lostItem.getDescription(),
                lostItem.getLatitude(),
                lostItem.getLongitude(),
                lostItem.getItemStatus(),
                lostItem.getMember()
        );
    }

    public static LostItemResponseDto ofHome(LostItem lostItem) {
        return new LostItemResponseDto(
                lostItem.getId(),
                lostItem.getImgUrl(),
                null,
                null,
                null,
                lostItem.getItemStatus(),
                null
        );
    }

    public static LostItemResponseDto ofList(LostItem lostItem) {
        return new LostItemResponseDto(
                lostItem.getId(),
                lostItem.getImgUrl(),
                null,
                null,
                null,
                lostItem.getItemStatus(),
                null
        );
    }
}

package com.example.ssu_lost.dto.request;

import com.example.ssu_lost.entity.LostItem;
import com.example.ssu_lost.enums.ItemStatus;

public record LostItemWriteDto(
        String imgUrl,
        String description,
        Double latitude,
        Double longitude,
        ItemStatus itemStatus
) {
    // TO DO: 멤버 구현 시 추가
    public LostItem toEntity() {
        return LostItem.builder()
                .imgUrl(imgUrl)
                .description(description)
                .latitude(latitude)
                .longitude(longitude)
                .itemStatus(itemStatus)
                .build();
    }
}

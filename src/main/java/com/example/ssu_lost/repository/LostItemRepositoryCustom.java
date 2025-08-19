package com.example.ssu_lost.repository;

import com.example.ssu_lost.entity.LostItem;
import com.example.ssu_lost.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LostItemRepositoryCustom {

    Page<LostItem> findLostItemsByItemStatus(
            ItemStatus itemStatus,
            Pageable pageable
    );
}


package com.example.ssu_lost.repository;

import com.example.ssu_lost.entity.LostItem;
import com.example.ssu_lost.entity.QLostItem;
import com.example.ssu_lost.enums.ItemStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class LostItemRepositoryImpl implements LostItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<LostItem> findLostItemsByItemStatus(
            ItemStatus itemStatus,
            Pageable pageable
    ){
        QLostItem qLostItem = QLostItem.lostItem;
        int pageSize = pageable.getPageSize();

        List<LostItem> events = queryFactory.selectFrom(qLostItem)
                .where(
                        qLostItem.itemStatus.eq(itemStatus)
                )
                .orderBy(qLostItem.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageSize + 1)
                .fetch();

        return new PageImpl<>(events);
    }

}

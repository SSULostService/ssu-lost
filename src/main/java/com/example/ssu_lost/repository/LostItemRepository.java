package com.example.ssu_lost.repository;

import com.example.ssu_lost.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItem,Long>, LostItemRepositoryCustom {
    //TO DO: 홈 조회 기능 확정 시 변경
    List<LostItem> findTop9ByOrderByCreatedDateDesc();
}

package com.example.ssu_lost.service;

import com.example.ssu_lost.dto.request.LostItemWriteDto;
import com.example.ssu_lost.dto.response.LostItemHomeResponseDto;
import com.example.ssu_lost.dto.response.LostItemListResponseDto;
import com.example.ssu_lost.dto.response.LostItemResponseDto;
import com.example.ssu_lost.entity.LostItem;
import com.example.ssu_lost.repository.LostItemRepository;
import com.example.ssu_lost.global.exception.NotFoundItemException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LostItemService {

    private final LostItemRepository lostItemRepository;

    public LostItemService(LostItemRepository lostItemRepository) {
        this.lostItemRepository = lostItemRepository;
    }

    public LostItemResponseDto createLostItem (LostItemWriteDto request) {

        LostItem lostItem =  request.toEntity();

        return LostItemResponseDto.ofDetail(lostItemRepository.save(lostItem));
    }

    public LostItemResponseDto getLostItemById (Long lostItemId){

        return LostItemResponseDto
                .ofDetail(lostItemRepository.findById(lostItemId)
                .orElseThrow(NotFoundItemException::new));
    }

    public LostItemListResponseDto getLostItemsForList (int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<LostItem> pageInfo = lostItemRepository.findAll(pageable);

        return LostItemListResponseDto.of(pageInfo);
    }

    public LostItemHomeResponseDto  getLostItemForHome (){

        return LostItemHomeResponseDto.from(lostItemRepository.findTop9ByOrderByCreatedDateDesc());
    }

    public void deleteLostItemById (Long lostItemId){

        lostItemRepository.deleteById(lostItemId);
    }
}

package com.example.ssu_lost.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.ssu_lost.dto.request.LostItemWriteDto;
import com.example.ssu_lost.dto.response.LostItemHomeResponseDto;
import com.example.ssu_lost.dto.response.LostItemListResponseDto;
import com.example.ssu_lost.dto.response.LostItemResponseDto;
import com.example.ssu_lost.entity.LostItem;
import com.example.ssu_lost.enums.ItemStatus;
import com.example.ssu_lost.repository.LostItemRepository;
import com.example.ssu_lost.global.exception.NotFoundItemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LostItemServiceTest {

    private static final Logger log = LoggerFactory.getLogger(LostItemServiceTest.class);

    @Mock
    private LostItemRepository lostItemRepository;

    @Mock
    private S3Service s3Service;

    @InjectMocks
    private LostItemService lostItemService;

    private LostItem sampleItem;

    @BeforeEach
    void setUp() {
        sampleItem = LostItem.builder()
                .id(1L)
                .description("지갑 분실")
                .latitude(37.5)
                .longitude(127.0)
                .itemStatus(ItemStatus.FINDING)
                .imgUrl("test-url")
                .build();
    }

    @Test @DisplayName("분실물 생성 성공")
    void createLostItem_성공() throws IOException {
        LostItemWriteDto request = new LostItemWriteDto( "지갑을 잃어버렸어요", 37.5665, 126.9780, ItemStatus.FINDING );
        MultipartFile mockFile = mock(MultipartFile.class); String testImageUrl = "https://fake-s3-url.com/image.jpg";

        when(s3Service.uploadImageFileToS3(mockFile)).thenReturn(testImageUrl);
        LostItem savedEntity = request.toEntity(testImageUrl);

        when(lostItemRepository.save(any(LostItem.class))).thenReturn(savedEntity);
        LostItemResponseDto result = lostItemService.createLostItem(request, mockFile);

        assertThat(result).isNotNull();
        assertThat(result.description()).isEqualTo("지갑을 잃어버렸어요");
        assertThat(result.imgUrl()).isEqualTo(testImageUrl);

        verify(s3Service, times(1)).uploadImageFileToS3(mockFile);
        verify(lostItemRepository, times(1)).save(any(LostItem.class));
    }

    @Test
    @DisplayName("PK값으로 단일 분실물 조회 성공")
    void getLostItemById_성공() {
        when(lostItemRepository.findById(1L)).thenReturn(Optional.of(sampleItem));

        LostItemResponseDto result = lostItemService.getLostItemById(1L);

        assertThat(result).isNotNull();
        assertThat(result.description()).isEqualTo("지갑 분실");
        assertThat(result.imgUrl()).isEqualTo("test-url");
    }

    @Test
    @DisplayName("ID로 단일 분실물 조회 실패")
    void getLostItemById_실패() {
        when(lostItemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundItemException.class, () -> lostItemService.getLostItemById(1L));
    }

    @Test
    @DisplayName("목록 조회 - 전체")
    void getLostItemsForList_전체조회() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<LostItem> page = new PageImpl<>(List.of(sampleItem));

        when(lostItemRepository.findAll(pageable)).thenReturn(page);
        LostItemListResponseDto result = lostItemService.getLostItemsForList(null, 0, 10);

        assertThat(result).isNotNull();
        assertThat(result.lostItems()).hasSize(1);
    }

    @Test
    @DisplayName("목록 조회 - 상태별")
    void getLostItemsForList_상태조회() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<LostItem> page = new PageImpl<>(List.of(sampleItem));

        when(lostItemRepository.findLostItemsByItemStatus(ItemStatus.FINDING, pageable)).thenReturn(page);
        LostItemListResponseDto result = lostItemService.getLostItemsForList("FINDING", 0, 10);

        assertThat(result).isNotNull();
        assertThat(result.lostItems()).hasSize(1);
    }

    @Test
    @DisplayName("홈 화면 최신 분실물 조회")
    void getLostItemForHome_성공() {
        List<LostItem> items = List.of(sampleItem);

        when(lostItemRepository.findTop9ByOrderByCreatedDateDesc()).thenReturn(items);

        LostItemHomeResponseDto result = lostItemService.getLostItemForHome();

        assertThat(result).isNotNull();
        assertThat(result.lostItems()).hasSize(1);
    }

    @Test
    @DisplayName("분실물 삭제 메서드")
    void deleteLostItemById_성공() {
        Long lostItemId = 1L;

        lostItemService.deleteLostItemById(lostItemId);
    }
}

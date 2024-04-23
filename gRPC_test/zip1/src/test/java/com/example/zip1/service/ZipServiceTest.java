//package com.example.zip1.service;
//
//import com.example.zip1.dto.ZipDTO;
//import com.example.zip1.entity.Zip;
//import com.example.zip1.exception.MessageException;
//import com.example.zip1.model.ZipRepository;
//import jakarta.annotation.Resource;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito.*;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class ZipServiceTest {
//
//    @Mock
//    private ZipRepository zipRepository;
//
//    @Autowired
//    private ModelMapper mapper;
//
//    @InjectMocks
//    private ZipService zipService;
//
//    @DisplayName("zip service getZip test success")
//    @Test
//    void getZip_success() throws Exception {
//        //given
//        String id = "1";
//        Zip mockZipEntity = Zip.builder()
//                .id("1")
//                .attachments("data")
//                .agentId("명진 부동산")
//                .direction("남향")
//                .totalFloor(5)
//                .buildingFloor(2)
//                .buildingType("아파트")
//                .deposit(1000)
//                .fee(45)
//                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
//                .hashtag("#역세권")
//                .m2((float) 22.0)
//                .build();
//
//        ZipDTO mockZipDTO = ZipDTO.builder()
//                .id("1")
//                .attachments("data")
//                .agentId("명진 부동산")
//                .direction("남향")
//                .totalFloor(5)
//                .buildingFloor(2)
//                .buildingType("아파트")
//                .deposit(1000)
//                .fee(45)
//                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
//                .hashtag("#역세권")
//                .m2((float) 22.0)
//                .build();
//
//        // Mock repository의 findById() 메서드가 성공적으로 조회할 때의 동작 설정
//        when(zipRepository.findById(id)).thenReturn(Optional.of(mockZipEntity));
//
//        // Mock mapper의 map() 메서드가 성공적으로 DTO로 변환할 때의 동작 설정
////        when(mapper.map(mockZipEntity, mockZipDTO)).thenReturn(mockZipDTO);
//
//        //when
//        ZipDTO result = zipService.getZip(id);
//
//        //then
//        assertNotNull(result);
//        assertEquals(mockZipDTO, result);
//    }
//
//    @DisplayName("zip service getZip test fail")
//    @Test
//    void getZip_fail() {
//        //given
//
//        //when
//
//        //then
//
//    }
//
//    @DisplayName("zip service getZipAll test")
//    @Test
//    void getZipAll() {
//    }
//
//    @DisplayName("zip service insertZip test")
//    @Test
//    void insertZip() throws MessageException {
//
////        //given
////        ZipDTO zipDTO = new ZipDTO();
////        Zip expectedZip = new Zip();
////
////        //모의 객체의 행동을 설정
////        when(zipRepository.save(any(Zip.class))).thenReturn(expectedZip);
////
////        //when
////        Zip result = zipService.insertZip(zipDTO);
////
////        //then
////        assertNotNull(result);
////        assertEquals(expectedZip, result);
//    }
//
//    @DisplayName("zip service updateZip test")
//    @Test
//    void updateZip() {
//    }
//
//    @DisplayName("zip service deleteZip test")
//    @Test
//    void deleteZip() throws Exception{
////        //given
////        String zipId = "1";
////
////        //모의 객체의 행동
////        doNothing().when(zipRepository).deleteById(zipId);
////
////        //when
////        boolean result = zipService.deleteZip(zipId);
////
////        //then
////        assertTrue(result);
//    }
//
//    @DisplayName("zip service isSame test")
//    @Test
//    void isSame() {
//    }
//}
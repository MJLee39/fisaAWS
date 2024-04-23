package com.example.zip1.controller;

import com.example.zip1.dto.ZipDTO;
import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.entity.Zip;
import com.example.zip1.repository.ZipRepository;
import com.example.zip1.service.ZipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ZipRestController.class)
@AutoConfigureMockMvc
class ZipRestControllerTest {

    @MockBean
    private ZipService zipService;

    @Autowired
    private ZipRestController zipRestController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZipRepository zipRepository;

    @DisplayName("zip controller 하나 조회")
    @Test
    void getZip_Success() throws Exception {
        //Given
        Zip zip = Zip.builder()
                .id("1")
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build();

        when(zipService.getZip(zip.getId())).thenReturn(ZipDTO.fromEntity(zip));

        // when
        MvcResult result = mockMvc.perform(
                get("/zipOne")
                        .param("zip_id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        //then
        Assertions.assertThat(zipRestController.getZip(zip.getId()).getAgentId()).isEqualTo("명진 부동산");

    }

    @DisplayName("zip controller 모두 조회")
    @Test
    void getZipALL_Success() throws Exception {
        // Mock 데이터 설정
        List<ZipDTO> mockZipList = new ArrayList<>();
        mockZipList.add(ZipDTO.builder()
                .id("1")
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build());
        mockZipList.add(ZipDTO.builder()
                .id("2")
                .attachments("data2")
                .agentId("명진 부동산2")
                .direction("북향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build());
        when(zipService.getZipAll()).thenReturn(mockZipList);

        // when
        mockMvc.perform(
                get("/zipAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @DisplayName("zip controller 생성")
    @Test
    void insertZip_Success() throws Exception {
        //given
        ZipDTO zipDTO = new ZipDTO();
        zipDTO.builder()
                .id("1")
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build();

        Zip insertedZip = Zip.builder()
                .id("1")
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build();

        when(zipService.insertZip(any(ZipDTO.class))).thenReturn(insertedZip);

        //when, then
        mockMvc.perform(post("/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(zipDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.attachments").value("data"))
                .andExpect(jsonPath("$.agentId").value("명진 부동산"))
                .andExpect(jsonPath("$.direction").value("남향"))
                .andExpect(jsonPath("$.totalFloor").value(5))
                .andExpect(jsonPath("$.buildingFloor").value(2))
                .andExpect(jsonPath("$.buildingType").value("아파트"))
                .andExpect(jsonPath("$.deposit").value(1000))
                .andExpect(jsonPath("$.fee").value(45))
                .andExpect(jsonPath("$.available").value("2024-04-09T15:00:00"))
                .andExpect(jsonPath("$.hashtag").value("#역세권"))
                .andExpect(jsonPath("$.m2").value(22.0));
    }


    @DisplayName("Zip controller update")
    @Test
    void updateZip_Success() throws Exception {
        // Given
        ZipUpdateDTO zipUpdateDTO = new ZipUpdateDTO();
        zipUpdateDTO.builder()
                .id("1")
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build();

        Zip updatedZip = Zip.builder()
                .id("1")
                .agentId("명진 부동산")
                .direction("북향")
                .totalFloor(6)
                .buildingFloor(3)
                .buildingType("빌라")
                .deposit(1500)
                .fee(50)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#풀옵션")
                .m2((float) 25.0)
                .build();

        when(zipService.updateZip(any(ZipUpdateDTO.class))).thenReturn(updatedZip);

        // When & Then
        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(zipUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.agentId").value("명진 부동산"))
                .andExpect(jsonPath("$.direction").value("북향"))
                .andExpect(jsonPath("$.totalFloor").value(6))
                .andExpect(jsonPath("$.buildingFloor").value(3))
                .andExpect(jsonPath("$.buildingType").value("빌라"))
                .andExpect(jsonPath("$.deposit").value(1500))
                .andExpect(jsonPath("$.fee").value(50))
                .andExpect(jsonPath("$.available").value("2024-04-09T15:00:00"))
                .andExpect(jsonPath("$.hashtag").value("#풀옵션"))
                .andExpect(jsonPath("$.m2").value(25.0));
    }


    @DisplayName("zip controller 삭제 성공")
    @Test
    void deleteZip_Success() throws Exception {
        //given
        String zipId = "1";
        Zip zip = Zip.builder()
                .id(zipId)
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build();


        when(zipService.deleteZip(zip.getId())).thenReturn(true);

        //when, then
        mockMvc.perform(delete("/delete/{id}","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @DisplayName("zip controller 삭제 실패")
    @Test
    void deleteZip_Fail() throws Exception {
        //given
        String zipId = "1";
        Zip zip = Zip.builder()
                .id(zipId)
                .attachments("data")
                .agentId("명진 부동산")
                .direction("남향")
                .totalFloor(5)
                .buildingFloor(2)
                .buildingType("아파트")
                .deposit(1000)
                .fee(45)
                .available(LocalDateTime.parse("2024-04-09T15:00:00"))
                .hashtag("#역세권")
                .m2((float) 22.0)
                .build();


        when(zipService.deleteZip(zip.getId())).thenReturn(false);

        //when, then
        mockMvc.perform(delete("/delete/{id}","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();

    }
}
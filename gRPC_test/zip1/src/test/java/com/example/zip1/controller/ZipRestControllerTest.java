package com.example.zip1.controller;

import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.service.ZipService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ZipRestControllerTest {

    @Mock
    private ZipService zipService;

    @InjectMocks
    private ZipRestController zipController;

    @Test
    public void testUpdateZip() throws Exception {
//        String id = "1";
//        ZipUpdateDTO zipUpdateDTO = new ZipUpdateDTO();
//        ResponseEntity<Void> expectedResponse = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        when(zipService.updateZip(id, zipUpdateDTO)).thenReturn(expectedResponse);
//
//        // 컨트롤러 메서드를 호출하여 응답을 받습니다.
//        ResponseEntity<Void> actualResponse = zipController.updateZip(id, zipUpdateDTO);
//
//        // 서비스 메서드가 호출되었는지 확인합니다.
//        verify(zipService).updateZip(id, zipUpdateDTO);
//
//        // 기대한 응답과 실제 응답이 일치하는지 확인합니다.
//        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
//        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }
}
package com.example.zip1.controller;

import com.example.zip1.dto.ZipDTO;
import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.entity.Zip;
import com.example.zip1.service.ZipService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class ZipRestController {

    private final ZipService zipService;

    @Autowired
    public ZipRestController(ZipService zipService) {
        this.zipService = zipService;
    }

    @GetMapping("/zipOne")
    public ZipDTO getZip(@RequestParam("zip_id") String id) throws Exception{
        return zipService.getZip(id);
    }

    @GetMapping("/zipAll")
    public List<ZipDTO> getZipAll() throws Exception{
        return zipService.getZipAll();
    }

    @PostMapping("/insert")
    public ResponseEntity<Zip> insertZip(@RequestBody ZipDTO zipDTO) throws Exception{
        log.info("zipDTO : {}", zipDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(zipService.insertZip(zipDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Zip> updateZip(@RequestBody ZipUpdateDTO zipUpdateDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(zipService.updateZip(zipUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteZip(@PathVariable String id) throws Exception{

        boolean deleted = zipService.deleteZip(id);
        if (deleted) {
            return ResponseEntity.ok("Zip 정보가 삭제되었습니다.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

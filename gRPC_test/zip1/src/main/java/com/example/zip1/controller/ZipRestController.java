package com.example.zip1.controller;

import com.example.zip1.dto.ZipDTO;
import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.entity.Zip;
import com.example.zip1.service.ZipService;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    ZipService zipService;

    @GetMapping("/zipOne")
    public ZipDTO getZip(@RequestParam("zip_id") String id) throws Exception{
        return zipService.getZip(id);
    }

    @GetMapping("/zipAll")
    public List<ZipDTO> getZipAll() throws Exception{
        return zipService.getZipAll();
    }

    @PostMapping("/insert")
    public ResponseEntity<Boolean> insertZip(@RequestBody ZipDTO zipDTO) throws Exception{
        log.info("zipDTO : {}", zipDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(zipService.insertZip(zipDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Zip> updateZip(@RequestBody ZipUpdateDTO zipUpdateDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(zipService.updateZip(zipUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteZip(@PathVariable String id){
        try{
            boolean deleted = zipService.deleteZip(id);
            if (deleted) {
                return ResponseEntity.ok("Zip 정보가 삭제되었습니다.");
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (EntityNotFoundException e) {
            return (ResponseEntity<String>) ResponseEntity.notFound();
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}

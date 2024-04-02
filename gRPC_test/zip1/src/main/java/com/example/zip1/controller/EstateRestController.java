package com.example.zip1.controller;

import com.example.zip1.dto.EstateDTO;
import com.example.zip1.dto.EstateUpdateDTO;
import com.example.zip1.dto.ZipDTO;
import com.example.zip1.entity.Estate;
import com.example.zip1.service.EstateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class EstateRestController {

    @Autowired
    EstateService estateService;

    @GetMapping("/estateOne")
    public EstateDTO getEstate(@RequestParam("estate_id") String id) throws Exception{
        return estateService.getEstate(id);
    }

    @GetMapping("/estateAll")
    public List<EstateDTO> getEstateAll() throws Exception{
        return estateService.getEstateAll();
    }

    @PostMapping("/insert/estate")
    public ResponseEntity<Boolean> insertEstate(@RequestBody EstateDTO estateDTO) throws Exception{
        log.info("estateDTO : {}", estateDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(estateService.insertEstate(estateDTO));
    }

    @PutMapping("/update/estate")
    public ResponseEntity<Estate> updateEstate(@RequestBody EstateUpdateDTO estateUpdateDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(estateService.updateEstate(estateUpdateDTO));
    }

    @DeleteMapping("/delete/estate/{id}")
    public ResponseEntity<String> deleteEstate(@PathVariable String id){
        try{
            boolean deleted = estateService.deleteEstate(id);
            if(deleted){
                return ResponseEntity.ok("Estate 정보가 삭제되었습니다.");
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch (EntityNotFoundException e){
            return   (ResponseEntity<String>) ResponseEntity.notFound();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다");
        }
    }

}

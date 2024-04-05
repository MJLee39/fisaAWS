package org.example.estate.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.estate.dto.EstateDTO;
import org.example.estate.dto.EstateUpdateDTO;
import org.example.estate.entity.Estate;
import org.example.estate.service.EstateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EstateRestController {

    private final EstateService estateService;

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

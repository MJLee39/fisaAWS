package com.example.zip1.service;

import com.example.zip1.dto.ZipDTO;
import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.entity.Zip;
import com.example.zip1.exception.MessageException;
import com.example.zip1.exception.NotExistException;
import com.example.zip1.model.ZipRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ZipService {

    @Autowired
    private ZipRepository zipRepository;

    @Autowired
    private ModelMapper mapper; // ModelMapper 주입


    //집 정보 하나 id로 조회
    public ZipDTO getZip(String id) throws Exception{
        Optional<Zip> zipEntity = zipRepository.findById(id);

        if(!zipEntity.isPresent() && zipEntity.get() == null){
            throw new NotExistException("해당하는 zip이 존재하지 않습니다.");
        }
        ZipDTO zipDTO = mapper.map(zipEntity.get(), ZipDTO.class);
        log.info("get zip dto {}", zipDTO.toString());
        log.info("get zip entity {}", zipEntity.toString());
        return zipDTO;
    }

    //집 모두 조회
    public List<ZipDTO> getZipAll() throws Exception{
        List<Zip> zipEntitys = zipRepository.findAll();

        List<ZipDTO> zipDTOs = Arrays.asList(mapper.map(zipEntitys, ZipDTO[].class));
        return zipDTOs;
    }

    //집 정보 insert
    @Transactional
    public boolean insertZip(ZipDTO zipDTO) throws MessageException{
        if(zipDTO.getId()==null){
            UUID uuid = UUID.randomUUID();
            zipDTO.setId(uuid.toString());
        }
        Zip zipEntity = mapper.map(zipDTO, Zip.class);
        log.info("zip dto {}", zipDTO.toString());
        log.info("zip entity {}", zipEntity.toString());

        // UUID를 자동 생성하기 때문에 별도의 설정이 필요 없음
        // 이미 존재하는지 확인 후 추가
        if (zipRepository.findById(zipEntity.getId()).isPresent()) {
            throw new MessageException("이미 존재하는 zip입니다.");
        }
        zipEntity = zipRepository.save(zipEntity);
        return true;

    }

    //집 정보 update
    @Transactional
    public Zip updateZip(ZipUpdateDTO zipUpdateDTO) throws Exception {
        Zip zip = zipRepository.findById(zipUpdateDTO.getId()).orElseThrow(() -> new EntityNotFoundException(zipUpdateDTO.getId()+" 이러한 id의 zip이 없습니다."));

        //만약 DTO와 엔티티가 다르다면 업데이트를 수행
        if(!isSame(zip, zipUpdateDTO)){
            zip.setAttachments(zipUpdateDTO.getAttachments());
            zip.setAvailable(zipUpdateDTO.getAvailable());
            zip.setM2(zipUpdateDTO.getM2());
            zip.setDeposit(zipUpdateDTO.getDeposit());
            zip.setDirection(zipUpdateDTO.getDirection());
            zip.setBuildingFloor(zipUpdateDTO.getBuildingFloor());
            zip.setTotalFloor(zipUpdateDTO.getTotalFloor());
            zip.setBuildingType(zipUpdateDTO.getBuildingType());
            zip.setHashtag(zipUpdateDTO.getHashtag());
            zip.setFee(zipUpdateDTO.getFee());
            zip.setCheckedAt(zipUpdateDTO.getCheckedAt());
            zip.setAgentId(zipUpdateDTO.getAgentId());
            zip.setEstateId(zip.getEstateId());
        }

        return zip;
    }


    //집 정보 삭제
    @Transactional
    public boolean deleteZip(String id) throws Exception{
        Zip zip = zipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id+" 이러한 id의 zip이 없습니다."));

        zipRepository.deleteById(id);

        return true;
    }

    // Zip과 ZipUpdateDTO가 같은지 비교하는 메서드
    public boolean isSame(Zip zip, ZipUpdateDTO zipUpdateDTO) {

        return zip.getAttachments().equals(zipUpdateDTO.getAttachments())
                && zip.getCheckedAt().equals(zipUpdateDTO.getCheckedAt())
                && zip.getDirection().equals(zipUpdateDTO.getDirection())
                && zip.getTotalFloor() == zipUpdateDTO.getTotalFloor()
                && zip.getBuildingFloor() == zipUpdateDTO.getBuildingFloor()
                && zip.getBuildingType().equals(zipUpdateDTO.getBuildingType())
                && zip.getDeposit() == zipUpdateDTO.getDeposit()
                && zip.getFee() == zipUpdateDTO.getFee()
                && zip.getAvailable().equals(zipUpdateDTO.getAvailable())
                && zip.getHashtag().equals(zipUpdateDTO.getHashtag())
                && Float.compare(zip.getM2(), zipUpdateDTO.getM2()) == 0
                && zip.getAgentId().equals(zipUpdateDTO.getAgentId())
                && zip.getEstateId().equals(zipUpdateDTO.getEstateId());
    }
}
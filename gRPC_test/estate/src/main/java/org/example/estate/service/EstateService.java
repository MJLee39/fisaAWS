package org.example.estate.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.estate.dto.EstateDTO;
import org.example.estate.dto.EstateUpdateDTO;
import org.example.estate.entity.Estate;
import org.example.estate.exception.MessageException;
import org.example.estate.exception.NotExistException;
import org.example.estate.model.EstateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EstateService {

    @Autowired
    private EstateRepository estateRepository;

    @Autowired
    private ModelMapper mapper;

    //estate 정보 하나 id로 조회
    public EstateDTO getEstate(String id) throws Exception{
        Optional<Estate> estateEntity = estateRepository.findById(id);

        if(!estateEntity.isPresent() && estateEntity.get() == null){
            throw new NotExistException("해당하는 Estate 존재하지 않습니다.");
        }
        EstateDTO estateDTO = mapper.map(estateEntity.get(), EstateDTO.class);
        log.info("get estate dto {}", estateDTO.toString());
        log.info("get estate entity {}", estateEntity.toString());
        return estateDTO;
    }

    //집 모두 조회
    public List<EstateDTO> getEstateAll() throws Exception{
        List<Estate> estateEntitys = estateRepository.findAll();

        List<EstateDTO> estateDTOs = Arrays.asList(mapper.map(estateEntitys, EstateDTO[].class));
        return estateDTOs;
    }

    //estate 정보 insert
    @Transactional
    public boolean insertEstate(EstateDTO estateDTO) throws MessageException {
        if(estateDTO.getId() == null){
           //건물 주소 + 상세정보 -> md5 해싱
           String bulidingAddress = "서울시 상암동 IT 타워";
           String detailAddress = "6층 fisa";
           estateDTO.setId(changeToMD5(bulidingAddress+detailAddress));
        }
        Estate estateEntity = mapper.map(estateDTO, Estate.class);
        log.info("estate insert dto {}", estateDTO.toString());
        log.info("zip entity {}", estateEntity.toString());

//        Estate estate = estateRepository.findById(estateEntity.getId()).orElseThrow(EntityNotFoundException::new);

        if(estateRepository.findById(estateEntity.getId()).isPresent()){
            throw new MessageException("이미 존재하는 estate입니다.");
        }
        estateEntity = estateRepository.save(estateEntity);
        return true;
    }

    //md5 해싱 하는 메소드
    public String changeToMD5(String str){
        String  MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<byteData.length; i++){
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        }catch (Exception e){
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }

    //estate 정보 update
    @Transactional
    public Estate updateEstate(EstateUpdateDTO estateUpdateDTO) throws Exception{
        Estate estate = estateRepository.findById(estateUpdateDTO.getId()).orElseThrow(() -> new EntityNotFoundException(estateUpdateDTO.getId()+"이러한 id의 estate가 없습니다."));

        //만약 DTO와 엔티티가 다르다면 업데이트를 수행
        if(!isSame(estate, estateUpdateDTO)){
            estate.setLocation(estateUpdateDTO.getLocation());
            estate.setEstateType(estateUpdateDTO.getEstateType());
            estate.setDirection(estateUpdateDTO.getDirection());
            estate.setTotalFloor(estateUpdateDTO.getTotalFloor());
            estate.setBuildingFloor(estateUpdateDTO.getBuildingFloor());
            estate.setRoom(estateUpdateDTO.getRoom());
            estate.setToilet(estateUpdateDTO.getToilet());
            estate.setHashtag(estateUpdateDTO.getHashtag());
            estate.setM2(estateUpdateDTO.getM2());
        }

        return estate;
    }

    //estate 정보 delete
    @Transactional
    public boolean deleteEstate(String id) throws Exception{
        Estate estate = estateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id+" 이러한 id의 estate가 없습니다."));

        estateRepository.deleteById(id);

        return true;
    }

    //estate와 estateUpdateDTO가 같은지 비교
    public boolean isSame(Estate estate, EstateUpdateDTO estateUpdateDTO){
        return estate.getLocation().equals(estateUpdateDTO.getLocation())
                && estate.getEstateType().equals(estateUpdateDTO.getEstateType())
                && estate.getDirection().equals(estateUpdateDTO.getDirection())
                && estate.getTotalFloor() == estateUpdateDTO.getTotalFloor()
                && estate.getBuildingFloor() == estateUpdateDTO.getBuildingFloor()
                && estate.getRoom() == estateUpdateDTO.getRoom()
                && estate.getToilet() == estateUpdateDTO.getToilet()
                && estate.getHashtag() == estateUpdateDTO.getHashtag()
                && estate.getM2() == estateUpdateDTO.getM2();
    }
}

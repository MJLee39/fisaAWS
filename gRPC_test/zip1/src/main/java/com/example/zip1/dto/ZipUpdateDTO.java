package com.example.zip1.dto;

import com.example.zip1.entity.Zip;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ZipUpdateDTO {

    private String id;

    private String attachments;

    private String agentId;

    private LocalDateTime checkedAt;

    private String estateId;

    private String direction;

    private int totalFloor;

    private int buildingFloor;

    private String buildingType;

    private int deposit;

    private int fee;

    private LocalDateTime available;

    private String hashtag;

    private float m2;

    private String location;

    private String note;


//    public static ZipUpdateDTO fromEntity(Zip zip) {
//        ZipUpdateDTO zipUpdateDTO = new ZipUpdateDTO();
//        zipUpdateDTO.setId(zip.getId());
//        zipUpdateDTO.setAttachments(zip.getAttachments());
//        zipUpdateDTO.setAgentId(zip.getAgentId());
//        zipUpdateDTO.setCheckedAt(zip.getCheckedAt());
//        zipUpdateDTO.setEstateId(zip.getEstateId());
//        zipUpdateDTO.setDirection(zip.getDirection());
//        zipUpdateDTO.setTotalFloor(zip.getTotalFloor());
//        zipUpdateDTO.setBuildingFloor(zip.getBuildingFloor());
//        zipUpdateDTO.setBuildingType(zip.getBuildingType());
//        zipUpdateDTO.setDeposit(zip.getDeposit());
//        zipUpdateDTO.setFee(zip.getFee());
//        zipUpdateDTO.setAvailable(zip.getAvailable());
//        zipUpdateDTO.setHashtag(zip.getHashtag());
//        zipUpdateDTO.setM2(zip.getM2());
//        return zipUpdateDTO;
//    }
}

package com.example.zip_grpc.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
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

    private String showYes;


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

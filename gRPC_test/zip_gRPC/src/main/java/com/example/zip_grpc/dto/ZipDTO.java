package com.example.zip_grpc.dto;

import java.time.LocalDateTime;

import com.example.zip_grpc.entity.Zip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ZipDTO {

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

    private String showYes;

    private String note;

    public void setId(String id) {
        this.id = id;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public void setBuildingFloor(int buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setAvailable(LocalDateTime available) {
        this.available = available;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setM2(float m2) {
        this.m2 = m2;
    }

    public void setLocation(String location){ this.location = location; }

    public void setNote(String note) {
        this.note = note;
    }

    public void setShowYes(String showYes) {
        this.showYes = showYes;
    }

    public static ZipDTO fromEntity(Zip zip) {
        ZipDTO zipDTO = new ZipDTO();
        zipDTO.setId(zip.getId());
        zipDTO.setAttachments(zip.getAttachments());
        zipDTO.setAgentId(zip.getAgentId());
        zipDTO.setCheckedAt(zip.getCheckedAt());
        zipDTO.setEstateId(zip.getEstateId());
        zipDTO.setDirection(zip.getDirection());
        zipDTO.setTotalFloor(zip.getTotalFloor());
        zipDTO.setBuildingFloor(zip.getBuildingFloor());
        zipDTO.setBuildingType(zip.getBuildingType());
        zipDTO.setDeposit(zip.getDeposit());
        zipDTO.setFee(zip.getFee());
        zipDTO.setAvailable(zip.getAvailable());
        zipDTO.setHashtag(zip.getHashtag());
        zipDTO.setM2(zip.getM2());
        zipDTO.setLocation(zip.getLocation());
        zipDTO.setNote(zip.getNote());
        zipDTO.setShowYes(zip.getShowYes());
        return zipDTO;
    }
}

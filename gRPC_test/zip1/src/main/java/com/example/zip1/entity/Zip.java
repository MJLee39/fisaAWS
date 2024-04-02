package com.example.zip1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Zip {

    @Id
    private String id; // UUID 타입의 식별자 필드

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
}

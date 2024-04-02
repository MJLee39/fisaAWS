package com.example.zip1.dto;

import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class EstateDTO {

    private String id;

    private String location;

    private String estateType;

    private String direction;

    private int totalFloor;

    private int buildingFloor;

    private int room;

    private int toilet;

    private String hashtag;

    private float m2;

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEstateType(String estateType) {
        this.estateType = estateType;
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

    public void setRoom(int room) {
        this.room = room;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setM2(float m2) {
        this.m2 = m2;
    }
}

package com.example.zip1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EstateUpdateDTO {

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

}

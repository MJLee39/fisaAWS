package com.example.zip1.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import com.example.zip1.dto.ZipDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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

	private String location;

	private String note;

	private String showYes;

	private int room;

	private int toilet;

	private float maintenanceFee;

	private LocalDateTime premium;

	public void setPremium(LocalDateTime premium) {
		this.premium = premium;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public void setToilet(int toilet) {
		this.toilet = toilet;
	}

	public void setMaintenanceFee(float maintenanceFee) {
		this.maintenanceFee = maintenanceFee;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setLocation(String location) {
		this.location = location;
	}

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

	public void setShowYes(String showYes) {
		this.showYes = showYes;
	}

	// DTO를 Entity로 변환하는 메서드
	public static Zip toEntity(ZipDTO zipDTO) {
		Zip zip = new Zip();
		zip.setId(zipDTO.getId());
		zip.setAttachments(zipDTO.getAttachments());
		zip.setAgentId(zipDTO.getAgentId());
		zip.setCheckedAt(zipDTO.getCheckedAt());
		zip.setEstateId(zipDTO.getEstateId());
		zip.setDirection(zipDTO.getDirection());
		zip.setTotalFloor(zipDTO.getTotalFloor());
		zip.setBuildingFloor(zipDTO.getBuildingFloor());
		zip.setBuildingType(zipDTO.getBuildingType());
		zip.setDeposit(zipDTO.getDeposit());
		zip.setFee(zipDTO.getFee());
		zip.setAvailable(zipDTO.getAvailable());
		zip.setHashtag(zipDTO.getHashtag());
		zip.setM2(zipDTO.getM2());
		zip.setLocation(zipDTO.getLocation());
		zip.setNote(zipDTO.getNote());
		zip.setShowYes(zipDTO.getShowYes());
		zip.setRoom(zipDTO.getRoom());
		zip.setToilet(zipDTO.getToilet());
		zip.setMaintenanceFee(zipDTO.getMaintenanceFee());
		zip.setPremium(zipDTO.getPremium());
		return zip;
	}
}

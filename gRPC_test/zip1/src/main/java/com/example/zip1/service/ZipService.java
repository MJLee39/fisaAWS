package com.example.zip1.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zip1.dto.AgentRequestDTO;
import com.example.zip1.dto.ZipDTO;
import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.entity.QZip;
import com.example.zip1.entity.Zip;
import com.example.zip1.exception.MessageException;
import com.example.zip1.exception.NotExistException;
import com.example.zip1.repository.ZipRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZipService {

	private final ZipRepository zipRepository;

	@Autowired
	private JPAQueryFactory queryFactory;

	@Autowired
	public ZipService(ZipRepository zipRepository) {
		this.zipRepository = zipRepository;
	}

	@Autowired
	private ModelMapper mapper; // ModelMapper 주입

	//집 정보 하나 id로 조회
	public ZipDTO getZip(String id) throws Exception {
		Optional<Zip> zipEntity = zipRepository.findById(id);

		if (zipEntity.isEmpty()) {
			throw new NotExistException("해당하는 zip이 존재하지 않습니다.");
		}
		ZipDTO zipDTO = mapper.map(zipEntity.get(), ZipDTO.class);

		return zipDTO;
	}

	//집 모두 조회
	public List<ZipDTO> getZipAll() throws Exception {
		List<Zip> zipEntitys = zipRepository.findAll();

		//        List<ZipDTO> list = zipEntitys.stream().map(zipEntity -> mapper.map(zipEntity, ZipDTO.class)).toList();
		List<ZipDTO> zipDTOs = Arrays.asList(mapper.map(zipEntitys, ZipDTO[].class));
		return zipDTOs;
	}

	//public인 집만 모두 조회
	public List<ZipDTO> getZipShowYes(String str) throws Exception {
		//List<Zip> zipEntitys = zipRepository.findByShowYes(str);
		List<Zip> zipEntitys = zipRepository.findMinFeeByEstateId(str);
		List<ZipDTO> zipDTOs = Arrays.asList(mapper.map(zipEntitys, ZipDTO[].class));
		log.info("zip dto {}", zipDTOs.toString());
		log.info("zip entity {}", zipEntitys.toString());
		return zipDTOs;
	}

	//private인 집만 모두 조회
	public List<ZipDTO> getZipShowNo(String str) throws Exception {
		//List<Zip> zipEntitys = zipRepository.findByShowYes(str);
		List<Zip> zipEntitys = zipRepository.findMinFeeByEstateId(str);
		List<ZipDTO> zipDTOs = Arrays.asList(mapper.map(zipEntitys, ZipDTO[].class));
		return zipDTOs;
	}

	//중개사 id로 매물 조회
	public List<ZipDTO> getZipAgentId(AgentRequestDTO agentID) throws Exception {
		List<Zip> zipEntitys = zipRepository.findByAgentId(agentID.getAgentId());
		List<ZipDTO> zipDTOs = Arrays.asList(mapper.map(zipEntitys, ZipDTO[].class));
		return zipDTOs;
	}

	//estateid로 zip 조회
	public List<ZipDTO> getZipByEstate(String estateID) {
		List<Zip> zipEntitys = zipRepository.findByEstateId(estateID);
		List<ZipDTO> zipDTOs = Arrays.asList(mapper.map(zipEntitys, ZipDTO[].class));

		return zipDTOs;

	}

	//집 정보 insert
	@Transactional
	public Zip insertZip(ZipDTO zipDTO) throws MessageException {
		if (zipDTO.getId() == null) {
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
		return zipEntity;
	}

	//집 정보 update
	@Transactional
	public Zip updateZip(ZipUpdateDTO zipUpdateDTO) throws Exception {
		Zip zip = zipRepository.findById(zipUpdateDTO.getId())
			.orElseThrow(() -> new EntityNotFoundException(zipUpdateDTO.getId() + " 이러한 id의 zip이 없습니다."));

		//만약 DTO와 엔티티가 다르다면 업데이트를 수행
		if (!isSame(zip, zipUpdateDTO)) {
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
			zip.setEstateId(zipUpdateDTO.getEstateId());
			zip.setNote(zipUpdateDTO.getNote());
			zip.setShowYes(zipUpdateDTO.getShowYes());
			zip.setRoom(zipUpdateDTO.getRoom());
			zip.setToilet(zipUpdateDTO.getToilet());
			zip.setMaintenanceFee(zipUpdateDTO.getMaintenanceFee());
			zip.setPremium(zipUpdateDTO.getPremium());
		}

		return zip;
	}

	//집 정보 삭제
	@Transactional
	public boolean deleteZip(String id) throws Exception {
		Zip zip = zipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " 이러한 id의 zip이 없습니다."));

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
			&& zip.getEstateId().equals(zipUpdateDTO.getEstateId())
			&& zip.getLocation().equals(zipUpdateDTO.getLocation())
			&& zip.getNote().equals(zipUpdateDTO.getNote())
			&& zip.getShowYes().equals(zipUpdateDTO.getShowYes())
			&& zip.getRoom() == zipUpdateDTO.getRoom()
			&& zip.getToilet() == zipUpdateDTO.getToilet()
			&& zip.getMaintenanceFee() == zipUpdateDTO.getMaintenanceFee()
			&& zip.getPremium() == zipUpdateDTO.getPremium();
	}

	//집 검색
	public List<ZipDTO> searchList(String buildingType, String deposit, String fee, String location) {
		BooleanBuilder builder = new BooleanBuilder();
		QZip zip = QZip.zip;
		QZip zip2 = new QZip("zip2");

		SubQueryExpression<Tuple> subQuery = JPAExpressions
			.select(zip2.estateId, zip2.fee.min())
			.from(zip2)
			.groupBy(zip2.estateId);

		Predicate inExpression = Expressions.list(
			zip.estateId,
			zip.fee
		).in(subQuery);

		builder.and(inExpression);

		builder.and(zip.showYes.eq("public"));

		if (buildingType != null) {
			String[] listBuildingType = buildingType.split(",");
			builder.and(zip.buildingType.in(listBuildingType));
		}
		if (deposit != null) {
			if (deposit.equals("~500")) {
				builder.and(zip.deposit.lt(500));
			} else if (deposit.equals("~1000")) {
				builder.and(zip.deposit.between(500, 1000));
			} else if (deposit.equals("~2000")) {
				builder.and(zip.deposit.between(1000, 2000));
			} else if (deposit.equals("2000~")) {
				builder.and(zip.deposit.gt(2000));
			}
		}
		if (fee != null) {
			if (fee.equals("~50")) {
				builder.and(zip.fee.lt(50));
			} else if (fee.equals("~75")) {
				builder.and(zip.fee.between(50, 75));
			} else if (fee.equals("~100")) {
				builder.and(zip.fee.between(75, 100));
			} else if (fee.equals("100~")) {
				builder.and(zip.fee.gt(100));
			}
		}
		if (location != null) {
			builder.and(zip.location.like("%" + location + "%"));
		}
		log.info("builder: {}", builder.toString());
		List<ZipDTO> result = queryFactory
			.select(zip)
			.from(zip)
			.where(builder)
			.fetch()
			.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
		log.info("list dto: {}", result);
		return result;
	}

	private ZipDTO convertToDTO(Zip zip) {
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
		zipDTO.setRoom(zip.getRoom());
		zipDTO.setToilet(zip.getToilet());
		zipDTO.setMaintenanceFee(zip.getMaintenanceFee());
		zipDTO.setPremium(zip.getPremium());
		return zipDTO;
	}
}
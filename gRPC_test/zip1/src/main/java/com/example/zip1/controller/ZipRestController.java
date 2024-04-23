package com.example.zip1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zip1.dto.AgentRequestDTO;
import com.example.zip1.dto.ZipDTO;
import com.example.zip1.dto.ZipUpdateDTO;
import com.example.zip1.entity.Zip;
import com.example.zip1.service.ZipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ZipRestController {

	private final ZipService zipService;

	@Autowired
	public ZipRestController(ZipService zipService) {
		this.zipService = zipService;
	}

	@CrossOrigin("*")
	@GetMapping("/zipOne")
	public ZipDTO getZip(@RequestParam("zip_id") String id) throws Exception {
		return zipService.getZip(id);
	}

	@CrossOrigin("*")
	@GetMapping("/zipAll")
	public ResponseEntity<List<ZipDTO>> getZipAll() throws Exception {
		return ResponseEntity.ok(zipService.getZipAll());
	}

	//public 보기
	@CrossOrigin("*")
	@PostMapping("/zipShowYes")
	public ResponseEntity<List<ZipDTO>> getZipShowYes() throws Exception {
		return ResponseEntity.ok(zipService.getZipShowYes("public"));
	}

	//private 보기
	@CrossOrigin("*")
	@PostMapping("/zipShowNo")
	public List<ZipDTO> getZipShowNo() throws Exception {
		return zipService.getZipShowYes("private");
	}

	//중개사id로 zip 조회
	@CrossOrigin("*")
	@PostMapping("/zipListByAgent")
	public List<ZipDTO> getZipListByAgent(@RequestBody AgentRequestDTO agentID) throws Exception {
		return zipService.getZipAgentId(agentID);
	}

	@CrossOrigin("*")
	@PostMapping("/insert")
	public ResponseEntity<Zip> insertZip(@RequestBody ZipDTO zipDTO) throws Exception {
		log.info("zipDTO : {}", zipDTO.toString());
		return ResponseEntity.status(HttpStatus.OK).body(zipService.insertZip(zipDTO));
	}

	@CrossOrigin("*")
	@PutMapping("/update")
	public ResponseEntity<Zip> updateZip(@RequestBody ZipUpdateDTO zipUpdateDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(zipService.updateZip(zipUpdateDTO));
	}

	@CrossOrigin("*")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteZip(@PathVariable String id) throws Exception {

		boolean deleted = zipService.deleteZip(id);
		if (deleted) {
			return ResponseEntity.ok("Zip 정보가 삭제되었습니다.");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@CrossOrigin("*")
	@GetMapping("/search")
	public List<ZipDTO> searchList(
		@RequestParam(required = false) String location,
		@RequestParam(required = false) String deposit,
		@RequestParam(required = false) String fee,
		@RequestParam(required = false) String buildingType
	) throws Exception {
		// 각 매개변수가 null이 아닌 경우에만 검색에 활용할 수 있도록 서비스로 전달
		if (location != null || deposit != null || fee != null || buildingType != null) {
			return zipService.searchList(buildingType, deposit, fee, location);
		} else {
			// 모든 매개변수가 null인 경우 전체 매물을 조회
			return zipService.getZipAll();
		}
	}

}

package com.example.zip_grpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zip_grpc.entity.Zip;

public interface ZipRepository extends JpaRepository<Zip, String> {

    List<Zip> findByShowYes(String showYes);

    List<Zip> findByAgentId(String id);

    List<Zip> findByEstateId(String estateId);

}

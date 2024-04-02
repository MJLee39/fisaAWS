package com.example.zip1.model;

import com.example.zip1.entity.Zip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ZipRepository extends JpaRepository<Zip, String> {

}

package com.example.zip1.model;

import com.example.zip1.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstateRepository extends JpaRepository<Estate,String> {

}

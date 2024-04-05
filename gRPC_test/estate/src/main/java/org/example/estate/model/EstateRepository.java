package org.example.estate.model;

import org.example.estate.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstateRepository extends JpaRepository<Estate,String> {

}

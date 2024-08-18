package com.playerdatatracking.repositories.indexaldata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playerdatatracking.entities.indexaldata.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long>{

	
	Pais findByCode(String code);
	Pais findByName(String name);
}

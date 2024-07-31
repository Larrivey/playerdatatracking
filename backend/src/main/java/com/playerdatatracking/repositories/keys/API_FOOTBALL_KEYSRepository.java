package com.playerdatatracking.repositories.keys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playerdatatracking.entities.keys.Keys;

@Repository
public interface API_FOOTBALL_KEYSRepository extends JpaRepository<Keys, Long>{

	
	Keys findByValor(String valor);
}

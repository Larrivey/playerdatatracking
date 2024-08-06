package com.playerdatatracking.repositories.keys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playerdatatracking.entities.keys.Keys;

@Repository
public interface API_FOOTBALL_KEYSRepository extends JpaRepository<Keys, Long>{

	
	Keys findByValor(String valor);
	Keys findByHashKey(String hashKey);
	List<Keys> findByMail(String mail);
	List<Keys> findByIsValid(boolean isValid);
}

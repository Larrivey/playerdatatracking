package com.playerdatatracking.repositories.indexaldata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playerdatatracking.entities.indexaldata.Torneo;

public interface TorneoRepository extends JpaRepository<Torneo, Long>{
	
	
	List<Torneo> findByName(String name);
	List<Torneo> findByStudied(boolean studied);
	List<Torneo> findAllByOrderByLastupdateAsc();
}

package com.playerdatatracking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.playerdatatracking.entities.PLAYER_QUALITIES;

@Repository
public interface PLAYER_QUALITIESRepository extends JpaRepository<PLAYER_QUALITIES, Long> {

	
	
	List<PLAYER_QUALITIES> findByPlayerId(Long id);
}

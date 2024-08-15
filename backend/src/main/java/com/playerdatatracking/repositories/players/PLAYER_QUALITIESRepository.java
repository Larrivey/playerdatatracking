package com.playerdatatracking.repositories.players;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playerdatatracking.entities.players.PLAYER_QUALITIES;

@Repository
public interface PLAYER_QUALITIESRepository extends JpaRepository<PLAYER_QUALITIES, Long> {

	
	void deleteByPlayerId(Long id);
	List<PLAYER_QUALITIES> findByPlayerId(Long id);
}

package com.playerdatatracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;

@Repository
public interface MANUAL_TRACKED_PLAYERRepository extends JpaRepository<MANUAL_TRACKED_PLAYER, Long> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario
	
	
	MANUAL_TRACKED_PLAYER findByNombre(String nombre);
	
}

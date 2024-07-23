package com.playerdatatracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.playerdatatracking.entities.MANUAL_TRACKED_DATA;

@Repository
public interface MANUAL_TRACKED_DATARepository extends JpaRepository<MANUAL_TRACKED_DATA, Long> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario
}

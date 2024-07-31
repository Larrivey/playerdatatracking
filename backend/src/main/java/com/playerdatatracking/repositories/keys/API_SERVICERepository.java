package com.playerdatatracking.repositories.keys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playerdatatracking.entities.keys.API_SERVICES;

@Repository
public interface API_SERVICERepository extends JpaRepository<API_SERVICES, Long> {

}

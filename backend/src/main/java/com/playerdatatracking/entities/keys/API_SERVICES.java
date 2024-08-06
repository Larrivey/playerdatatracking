package com.playerdatatracking.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "API_SERVICES")
public class API_SERVICES {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false, name = "id_service")
    private Long idService;
	
	@Column(nullable=false)
	private String name;
}

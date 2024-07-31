package com.playerdatatracking.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "API_FOOTBALL_KEYS")
public class Keys {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
    private Long id;
	
	@Column(nullable=false)
	private String valor;
	
	@Column(name="last_used")
	private String lastUsed;
	
	@Column(name="today_uses")
	private int todayUses;
	
	@Column(name="total_uses")
	private int totalUses;
	
	private boolean isValid;
	
	@Column(nullable=false, name="id_service")
	private int idService;
	
	@Column(nullable=false)
	private String mail;
	
	@Column(nullable=false)
	private boolean isPertmanentlyValid;
	
}

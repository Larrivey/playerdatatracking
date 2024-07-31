package com.playerdatatracking.entities.players;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PLAYER_QUALITIES")
public class PLAYER_QUALITIES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name="player_id", nullable = false)
    private Long playerId;
	
	@Column(nullable = false)
	private String quality;

	public Long getplayerId() {
		return playerId;
	}

	public void setplayerId(Long id) {
		this.playerId = id;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

}

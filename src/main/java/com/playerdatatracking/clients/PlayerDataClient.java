package com.playerdatatracking.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlayerDataClient {

	
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;
	
	
	public boolean savePlayer(MANUAL_TRACKED_PLAYER player) {
		try {
			repository.save(player);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}

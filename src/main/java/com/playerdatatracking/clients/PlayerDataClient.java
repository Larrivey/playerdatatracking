package com.playerdatatracking.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlayerDataClient {

	
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;
	
	@Transactional
	public boolean savePlayer(MANUAL_TRACKED_PLAYER player) throws PlayerDataDBException {
		try {
			repository.save(player);
			return true;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	public MANUAL_TRACKED_PLAYER getPlayerbyName(String name) {
		return repository.findByNombre(name);
		
	}
	
	
}

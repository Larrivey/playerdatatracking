package com.playerdatatracking.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.entities.PLAYER_QUALITIES;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.repositories.PLAYER_QUALITIESRepository;

import jakarta.transaction.Transactional;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlayerDataClient {

	
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository mpRepository;
	@Autowired
	private PLAYER_QUALITIESRepository pqRepository;
	
	@Transactional
	public boolean savePlayer(MANUAL_TRACKED_PLAYER player) throws PlayerDataDBException {
		try {
			mpRepository.save(player);
			return true;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	@Transactional
	public MANUAL_TRACKED_PLAYER getPlayerbyName(String name) throws PlayerDataDBException {
		try {
			return mpRepository.findByNombre(name);
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	@Transactional
	public List<MANUAL_TRACKED_PLAYER> getAllPlayers() {
		return mpRepository.findAll();
	}
	
	@Transactional
	public List<String> getAllQualities (Long player) throws PlayerDataDBException{
		try {
			List<String> response = new ArrayList<>();
			List<PLAYER_QUALITIES> pqList = pqRepository.findByPlayerId(player);
			if (pqList.size()==0)
				return null;
			for (PLAYER_QUALITIES q : pqList) {
				response.add(q.getQuality());
			}
			return response;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	
}

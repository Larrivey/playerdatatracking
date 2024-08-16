package com.playerdatatracking.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.playerdatatracking.entities.keys.Keys;
import com.playerdatatracking.entities.players.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.entities.players.PLAYER_QUALITIES;
import com.playerdatatracking.exceptions.db.PlayerDataDBException;
import com.playerdatatracking.repositories.keys.API_FOOTBALL_KEYSRepository;
import com.playerdatatracking.repositories.players.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.repositories.players.PLAYER_QUALITIESRepository;

import jakarta.transaction.Transactional;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlayerDataClient {

	
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository mpRepository;
	@Autowired
	private PLAYER_QUALITIESRepository pqRepository;
	@Autowired
	private API_FOOTBALL_KEYSRepository akRepository;
	
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
	
	@Transactional
	public boolean saveApiKey(Keys newKey) throws PlayerDataDBException{
		try{
			akRepository.save(newKey);
			return true;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	@Transactional
	public Keys getKeyByKey (String key) throws PlayerDataDBException{
		try {
			Keys storedKey = akRepository.findByValor(key);
			return storedKey;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	@Transactional
	public Keys getKeyByHash (String key) throws PlayerDataDBException{
		try {
			Keys storedKey = akRepository.findByHashKey(key);
			return storedKey;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	@Transactional
	public Keys getValidKey() throws PlayerDataDBException{
		try {
			List<Keys> storedKeys = akRepository.findByIsValid(true);
			if (storedKeys != null)
				if (storedKeys.size()>0)
					return storedKeys.get(0);
			return null;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	
	
	@Transactional
	public boolean deleteKey (Keys key) throws PlayerDataDBException{
		if (key==null)
			throw new PlayerDataDBException("no se puede borrar una apikey que sea nula");
		try {
			akRepository.delete(key);
			return true;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
		
	}
	
	@Transactional
	public boolean deletePlayer(String name) throws PlayerDataDBException{
		try {
			MANUAL_TRACKED_PLAYER player = mpRepository.findByNombre(name);
			if (player==null)
				throw new PlayerDataDBException("no player was found with name " + name);
			mpRepository.delete(player);
			return true;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	@Transactional
	public List<Keys> getKeysByMail (String mail)throws PlayerDataDBException{
		try {
			List<Keys> storedKeys = akRepository.findByMail(mail);
			if(storedKeys!=null)
				return storedKeys;
			return new ArrayList<Keys>();
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	@Transactional
	public List<Keys> getAllKeys() throws PlayerDataDBException{
		try {
			return akRepository.findAll();
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
	
	@Transactional
	public MANUAL_TRACKED_PLAYER getPlayer(Long id) throws PlayerDataDBException{
		try {
			Optional<MANUAL_TRACKED_PLAYER> player = mpRepository.findById(id);
			return player.isPresent() ? player.get() : null;
		} catch (Exception e) {
			throw new PlayerDataDBException(e.getMessage());
		}
	}
}

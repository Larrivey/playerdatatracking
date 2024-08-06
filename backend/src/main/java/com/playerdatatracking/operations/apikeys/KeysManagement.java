package com.playerdatatracking.operations.apikeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.env.Environment;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.keys.Keys;
import com.playerdatatracking.exceptions.ApiKeyManagementException;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.operations.Crypto.AESCrypto;
import com.playerdatatracking.responses.GenericResponse;

public class KeysManagement {

	
	private AESCrypto crypt = new AESCrypto();
	private PlayerDataClient pdClient;
	private Environment env;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public void setEnv(Environment extenv) {
		this.env = extenv;
	}

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}
	
	
	public Keys keyManagement(Keys actualKey) throws PlayerDataDBException {
		try {
			actualKey = checkReadiness(actualKey);
			Date actualDate = new Date();
			actualKey.setLastUsed(actualDate.toString());
			actualKey.setTodayUses(actualKey.getTodayUses()+1);
			if (actualKey.getTodayUses()>= actualKey.getTotalUses()) {
				actualKey.setValid(false);
				return actualKey;
			}
			else
				actualKey.setValid(true);
			return nextKey();
		} catch (Exception e) {
			throw e;
		}
	}
	public boolean deleteKey(Keys key) throws PlayerDataDBException {
		try {
			return pdClient.deleteKey(key);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Keys setNewPlan(String key, String newPlan) throws Exception {
		try {
			Keys storedKey = getKeyByKey(key).getEntity();
			pdClient.deleteKey(storedKey);
			switch (newPlan) {
				case Constants.APISPORTS_FREE:
					storedKey.setTotalUses(Constants.USAGES_APISPORTS_FREE);
					break;
				case Constants.APISPORTS_MEGA:
					storedKey.setTotalUses(Constants.USAGES_APISPORTS_MEGA);
					break;
				case Constants.APISPORTS_PRO:
					storedKey.setTotalUses(Constants.USAGES_APISPORTS_PRO);
					break;
				case Constants.APISPORTS_ULTRA:
					storedKey.setTotalUses(Constants.USAGES_APISPORTS_ULTRA);
					break;
				default:
					break;	
		}
			storedKey.setValid(true);
			pdClient.saveApiKey(storedKey);
			return storedKey;
		} catch (Exception e) {
			throw e;
		}
	}
	public Keys SuscribeKeys(String key)throws Exception {
		try{
			Keys storedKey = getKeyByKey(key).getEntity();
			pdClient.deleteKey(storedKey);
			storedKey.setPermanentlyValid(true);
			pdClient.saveApiKey(storedKey);
			return storedKey;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Keys UnsuscribeKeys(String key)throws Exception {
		try{
			Keys storedKey = getKeyByKey(key).getEntity();
			pdClient.deleteKey(storedKey);
			storedKey.setPermanentlyValid(false);
			pdClient.saveApiKey(storedKey);
			return storedKey;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Keys nextKey() throws PlayerDataDBException {
		try {
			return pdClient.getValidKey();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public GenericResponse<Keys> getKeyByMail(String mail) throws Exception{
		GenericResponse <Keys> response = new GenericResponse<>();
		try {
			List<Keys> userKeys = pdClient.getKeysByMail(mail);
			response.setCODE(Constants.CODE_OK);
			response.setDescription("OK");
			response.setEntityList(userKeys);
			return response;
		} catch (Exception e) {
			throw new ApiKeyManagementException("Error al buscar las api keys guardadas por el usuario");
		}
	}
	
	public GenericResponse<Keys> getKeyByKey(String key) throws Exception {
		String uncryptedKey;
		String keyHash;
		Keys storedKey;
		GenericResponse <Keys> response = new GenericResponse<>();
		try {
			crypt.setEnv(this.env);
			keyHash = crypt.hashKey(key);
		} catch (Exception e) {
			throw new ApiKeyManagementException("Error al encriptar la clave de conexion con la api aportada");
		}
		try {
			storedKey = pdClient.getKeyByHash(keyHash);
			if (storedKey==null)
				throw new ApiKeyManagementException("Api Key no encontrada");
			else {
				uncryptedKey = crypt.decrypt(storedKey.getValor());
				if (!uncryptedKey.equals(key)) {
					throw new ApiKeyManagementException("Error al desencriptar la contraseña ya que no coincide con apiKey");
				}
				storedKey.setValor(uncryptedKey);
			}
		} catch (Exception e) {
			throw e;
		}
		response.setCODE(Constants.CODE_OK);
		response.setDescription("OK");
		response.setEntity(storedKey);
		return response;
	}

	public GenericResponse storeKey(String apiKey, String mail, String plan, int id_service) throws Exception {
		GenericResponse response = new GenericResponse();
		Keys newKey = new Keys();
		boolean isPlanValid = true;
		String cryptedKey;
		try {
			crypt.setEnv(this.env);
			cryptedKey = crypt.encrypt(apiKey);
		} catch (Exception e) {
			throw new ApiKeyManagementException("Error al encriptar la clave de conexion con la api aportada");
		}
		newKey.setValor(cryptedKey);
		newKey.setMail(mail);
		newKey.setIdService(id_service);
		newKey.setHashKey(crypt.hashKey(apiKey));
		switch (plan) {
			case Constants.APISPORTS_FREE:
				newKey.setTotalUses(Constants.USAGES_APISPORTS_FREE);
				break;
			case Constants.APISPORTS_MEGA:
				newKey.setTotalUses(Constants.USAGES_APISPORTS_MEGA);
				break;
			case Constants.APISPORTS_PRO:
				newKey.setTotalUses(Constants.USAGES_APISPORTS_PRO);
				break;
			case Constants.APISPORTS_ULTRA:
				newKey.setTotalUses(Constants.USAGES_APISPORTS_ULTRA);
				break;
			default:
				newKey.setTotalUses(Constants.USAGES_APISPORTS_FREE);
				isPlanValid= false;
		}
		newKey.setTodayUses(0);
		newKey.setValid(true);
		newKey.setPermanentlyValid(true);
		try {
			pdClient.saveApiKey(newKey);
		} catch (Exception e) {
			throw e;
		}
		response.setCODE(Constants.CODE_OK);
		if (isPlanValid)
			response.setDescription("OK");
		else
			response.setDescription("Key guardada, pero el plan indicado no esta registrado, el sistema lo utilizara como una key gratuita (100 llamadas al dia disponibles)");
		
		return response;	
			
	}
	
	public List<Keys> getAllKeys() throws PlayerDataDBException{
		try {
			List<Keys> keys = pdClient.getAllKeys();
			if (keys==null)
				return new ArrayList<Keys>();
			return keys;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Keys checkReadiness(Keys key) throws PlayerDataDBException {
		try {
			Date actualDate = new Date();
			boolean changed = false;
			pdClient.deleteKey(key);
			if (key.getLastUsed()==null || !key.getLastUsed().equals(actualDate.toString())) {
				key.setTodayUses(0);
				changed = true;
			}
			pdClient.saveApiKey(key);
			return key;
		} catch (Exception e) {
			throw e;
		}
	}
	
		
}
	

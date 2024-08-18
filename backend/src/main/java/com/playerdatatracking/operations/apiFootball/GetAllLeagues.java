package com.playerdatatracking.operations.apiFootball;


import java.io.File;
import java.io.FileReader;

import org.springframework.core.env.Environment;

import com.playerdatatracking.clients.ApiFootballClient;
import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.IndexalData.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.entities.keys.Keys;
import com.playerdatatracking.exceptions.apikeys.ApiKeyManagementException;
import com.playerdatatracking.exceptions.file.NotCreatedJsonFileResponse;
import com.playerdatatracking.exceptions.file.NotFilledJsonFileResponse;
import com.playerdatatracking.operations.apikeys.KeysManagement;
import com.playerdatatracking.requests.GenericRequest;
import com.playerdatatracking.responses.GenericResponse;

public class GetAllLeagues {

	
	private ApiFootballClient restClient;
	private GenericResponse response;
	String filePath = "src/main/resources/json/apiFotball/leagues/leagues.json";
	private PlayerDataClient pdClient;
	private KeysManagement keyMethods = new KeysManagement();
	private Environment env;

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}
	
	public GenericResponse ejecutar (GenericRequest request) throws Exception {
		try {
			response = new GenericResponse();
			restClient = new ApiFootballClient();
			keyMethods.setEnv(env);
			keyMethods.setPdClient(pdClient);
			Keys apiKey = keyMethods.nextKey();
			if (apiKey==null)
				throw new ApiKeyManagementException("no hay almacenada ninguna key valida");
			if (keyMethods.checkReadiness(apiKey)) {
				Keys unctyptedKey = keyMethods.uncryptKey(apiKey);
				restClient.getLeaguesInfo(unctyptedKey.getValor());
				keyMethods.useKey(apiKey);
			}
			else {
				throw new ApiKeyManagementException("error al intentar usar una key no disponible");
			}
	        File file = new File(filePath);

	        if (!file.exists())
	            throw new NotCreatedJsonFileResponse("error al crear un json de respuesta, el archivo no ha sido creado o no se ha guardado correctamente");
	        
	        if (file.length() == 0) 
	            throw new NotFilledJsonFileResponse("el archivo de respuesta creado esta vacio");

	        FileReader fileReader = new FileReader(file);
	            int ch;
	            if ((ch = fileReader.read()) == -1) {
	            	fileReader.close();
	            	throw new NotFilledJsonFileResponse("el archivo de respuesta creado esta vacio");
	            }
	            fileReader.close();
	            
	        if (request.getUpdate().equalsIgnoreCase("true"))
	        	updateLeagues();
	        		
	        response.setCODE(Constants.CODE_OK);
	        response.setDescription("OK");
	        return response;
	       
	    } catch (Exception e) {
	    	throw e;
		}
	}
	
	public void updateLeagues() {
		
	}
}

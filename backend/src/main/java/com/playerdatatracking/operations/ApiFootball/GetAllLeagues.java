package com.playerdatatracking.operations.ApiFootball;


import java.io.File;
import java.io.FileReader;

import com.playerdatatracking.clients.ApiFootballClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.players.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.NotCreatedJsonFileResponse;
import com.playerdatatracking.exceptions.NotFilledJsonFileResponse;
import com.playerdatatracking.responses.GenericResponse;

public class GetAllLeagues {

	
	private ApiFootballClient restClient;
	private GenericResponse response;
	String filePath = "src/main/resources/json/apiFotball/leagues/response.json";
	
	
	public GenericResponse ejecutar () throws Exception {
		try {
			response = new GenericResponse();
			restClient = new ApiFootballClient();
			restClient.getThings();
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
	            
	        
	        response.setCODE(Constants.CODE_OK);
	        response.setDescription("OK");
	        return response;
	       
	    } catch (Exception e) {
	    	throw e;
		}
	}
}

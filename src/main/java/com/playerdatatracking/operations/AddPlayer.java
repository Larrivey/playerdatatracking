package com.playerdatatracking.operations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.MalformedRequestException;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.responses.GenericResponse;

public class AddPlayer implements GenericOperation {

	
	
	private PlayerDataClient pdClient;
	
	private GenericResponse response;
	


	
	public GenericResponse ejecutar (MANUAL_TRACKED_PLAYER player) throws MalformedRequestException, PlayerDataDBException {
		ArrayList<String> errors = Methods.playerWrongValues(player);
		response = new GenericResponse();
		if(errors.size()==0) {
			boolean operationDone = pdClient.savePlayer(player);
			if (operationDone) {
				response.setCODE(Constants.CODE_OK);
				response.setDescription("OK");
				return response;
			}
			else
				throw new PlayerDataDBException("Error found while saving player in MANUAL_TRACKED_PLAYER table, check method in clients package");
		}
		else {
			throw new MalformedRequestException("Values not present or malformed: " + errors.toString());
		}
		
	}




	public PlayerDataClient getPdClient() {
		return pdClient;
	}

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}

	
	
	
	
}

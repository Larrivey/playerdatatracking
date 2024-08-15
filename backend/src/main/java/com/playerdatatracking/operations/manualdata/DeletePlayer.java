package com.playerdatatracking.operations.manualdata;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.exceptions.operations.PlayerInputException;
import com.playerdatatracking.responses.GenericResponse;

public class DeletePlayer {
	
private PlayerDataClient pdClient;
	
	private GenericResponse response;
	

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}

	public GenericResponse ejecutar(String name) throws Exception {
		try {
			GenericResponse response = new GenericResponse();
			boolean deleted = pdClient.deletePlayer(name);
			if (deleted) {
				response.setCODE(Constants.CODE_OK);
				response.setDescription("OK");
				return response;
			}
			else
				throw new PlayerInputException("player not in database");
		} catch(Exception e) {
			throw e;
		}
		
	}

}

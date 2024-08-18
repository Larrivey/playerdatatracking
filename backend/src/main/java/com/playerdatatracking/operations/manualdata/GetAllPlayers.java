package com.playerdatatracking.operations.manualdata;

import java.util.ArrayList;
import java.util.List;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.indexaldata.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.db.PlayerDataDBException;
import com.playerdatatracking.responses.GenericResponse;

public class GetAllPlayers {

	
	private PlayerDataClient pdClient;
	
	private GenericResponse<MANUAL_TRACKED_PLAYER> response = new GenericResponse();
	
	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}
	
	
	public GenericResponse<MANUAL_TRACKED_PLAYER> ejecutar() throws PlayerDataDBException {
		List<MANUAL_TRACKED_PLAYER> playersList = pdClient.getAllPlayers();
		response.setEntityList(playersList);
		response.setCODE(Constants.CODE_OK);
		if (playersList==null)
			response.setDescription("No Player Found");
		else
			response.setDescription("OK");
		return response;
	}
}

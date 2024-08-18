package com.playerdatatracking.operations.manualdata;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.IndexalData.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.db.PlayerDataDBException;
import com.playerdatatracking.exceptions.operations.PlayerInputException;
import com.playerdatatracking.responses.GenericResponse;

public class GetPlayer {

	
	private PlayerDataClient pdClient;
	
	private GenericResponse<MANUAL_TRACKED_PLAYER> response = new GenericResponse();
	
	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}
	public GenericResponse<MANUAL_TRACKED_PLAYER> ejecutar(Long id) throws PlayerDataDBException, PlayerInputException{
		MANUAL_TRACKED_PLAYER player = pdClient.getPlayer(id);
		if (player==null)
			throw new PlayerInputException("no player has been found with that name");
		response.setCODE(Constants.CODE_OK);
		response.setDescription("OK");
		response.setEntity(player);
		return response;
	}
}

package com.playerdatatracking.operations;

import java.util.ArrayList;
import java.util.List;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.responses.GenericResponse;

public class GetAllPlayers {

	
private PlayerDataClient pdClient;
	
	private GenericResponse response = new GenericResponse();
	
	
	
	
	public PlayerDataClient getPdClient() {
		return pdClient;
	}

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}
	
	
	public GenericResponse ejecutar() throws PlayerDataDBException {
		List<MANUAL_TRACKED_PLAYER> playersList = pdClient.getAllPlayers();
		response.setCODE(Constants.CODE_OK);
		String description = "[";
		for (MANUAL_TRACKED_PLAYER p : playersList) {
			List<String> qualities = pdClient.getAllQualities(p.getId());
			if (qualities==null)
				qualities = new ArrayList<>();
			description = description + p.getNombre() + ": ";
			description = description + "Club Actual - " + p.getClub() + " ";
			description = description + "Poscion - " + p.getPosicion() + " ";
			description = description + "Nota - " + p.getNota() + " ";
			description = description + "Cualidades - " + qualities.toString() + " ";
			description = description + "Futuro Club - " + p.getMostLikeDestination() + " ";
			description = description + "Jugador Parecido - " + p.getLikeable() + " ";
			description = description + "Fecha de Scouting - " + p.getDate().toString() + ". \r\n ";
		}
		description = description + "]";
		response.setDescription(description);
		
		
		
		return response;
	}
}

package com.playerdatatracking.operations.manualdata;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.IndexalData.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.db.PlayerDataDBException;
import com.playerdatatracking.exceptions.operations.MalformedRequestException;
import com.playerdatatracking.exceptions.operations.PlayerInputException;
import com.playerdatatracking.repositories.players.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.responses.GenericResponse;

public class AddPlayer{

	
	
	private PlayerDataClient pdClient;
	
	private GenericResponse response;
	


	
	public GenericResponse ejecutar (MANUAL_TRACKED_PLAYER player) throws MalformedRequestException, PlayerDataDBException, PlayerInputException {
		
		response = new GenericResponse();
		MANUAL_TRACKED_PLAYER dummy = pdClient.getPlayerbyName(player.getNombre());
		if (dummy!=null) {
			throw new PlayerInputException("Player already registered in MANUAL_TRACKED_PLAYER");
		}
		if ((Object)player.getAge()==null) {
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    LocalDate fechaHoy = LocalDate.now();
		    LocalDate birthDate = convertToLocalDateViaInstant(player.getBirth());
		    player.setAge(Period.between(birthDate, fechaHoy).getYears());
		}
		boolean operationDone = pdClient.savePlayer(player);
		if (operationDone) {
			response.setCODE(Constants.CODE_OK);
			response.setDescription("OK");
			return response;
		}
		else
			throw new PlayerDataDBException("Error found while saving player in MANUAL_TRACKED_PLAYER table, check method in clients package");
	
	}




	public PlayerDataClient getPdClient() {
		return pdClient;
	}

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}

	
	 private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	        return dateToConvert.toInstant()
	          .atZone(ZoneId.systemDefault())
	          .toLocalDate();
	    }
	
	
	
	
}

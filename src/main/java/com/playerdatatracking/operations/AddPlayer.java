package com.playerdatatracking.operations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.MalformedRequestException;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.responses.ManualDataResponse;

public class AddPlayer {

	
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;
	
	private ManualDataResponse response;
	


	
	public ManualDataResponse ejecutar (MANUAL_TRACKED_PLAYER player) throws MalformedRequestException {
		ArrayList<String> errors = Methods.playerWrongValues(player);
		if(errors.size()==0) {
			repository.save(player);
			response.setCODE(Constants.CODE_OK);
			response.setDescription("OK");
			return response;
		}
		else {
			throw new MalformedRequestException("Values not present or malformed: " + errors.toString());
		}
		
	}
	
	
	
}

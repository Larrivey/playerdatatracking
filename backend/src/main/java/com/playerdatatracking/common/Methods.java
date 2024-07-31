package com.playerdatatracking.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.playerdatatracking.entities.players.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.MalformedRequestException;
import com.playerdatatracking.requests.GenericRequest;

public class Methods {

	public static ArrayList<String> playerWrongValues(GenericRequest player) {
		ArrayList<String> errors = new ArrayList<String>();
		if (player.getNombre()==null || player.getNombre().equals(""))
			errors.add("Nombre");
		if ((Float)player.getNota()==null)
			errors.add("Nota");
		if (player.getClub()==null || player.getClub().equals(""))
			errors.add("Club");
		if (player.getBirth()==null)
			errors.add("Birth");
		return errors;
	}
	
	
	
	public static int exceptionCodeManagement(Exception e) {
		if (e==null)
			return -1;
		String name = e.getClass().getSimpleName();
		
		switch (name) {
			case "MalformedRequestException":
				return Constants.CODE_ERR_MALFORMED_PARAMS;
			case "PlayerDataDBException":
				return Constants.CODE_ERR_PDDB;
			case "PlayerInputException":
				return Constants.CODE_ERR_INPUT_EXCEPTION;
			case "IOException":
				return Constants.CODE_ERR_XSL_READING_EXCEPTION;
			case "ParseException":
				return Constants.CODE_ERR_PARSE_EXCEPTION;
			case "NotFilledJsonFileResponse":
				return Constants.CODE_ERR_NOT_FILLED_JSON_RESPONSE;
			case "NotCreatedJsonFileResponse":
				return Constants.CODE_ERR_NOT_CREATED_JSON_RESPONSE;
			default:
				return -1;
				
		}
	}



	public static MANUAL_TRACKED_PLAYER bindRequestAsPlayer(GenericRequest request) throws ParseException, MalformedRequestException {
		ArrayList<String> errors = playerWrongValues(request);
		if (errors.size()==0) {
			MANUAL_TRACKED_PLAYER player = new MANUAL_TRACKED_PLAYER();
			player.setNombre(request.getNombre());
			player.setNota(request.getNota());
			if (request.getAge()==null || request.getAge().equals(""))
				player.setAge(null);
			else
				player.setAge(Integer.parseInt(request.getAge()));
			player.setClub(request.getClub());
			player.setLikeable(request.getLikeable());
			player.setMostLikeDestination(request.getMostLikeDestination());
			player.setQualities(request.getQualities());
			player.setPosicion(request.getPosicion());
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (request.getDate()!=null)
				player.setDate(sdf.parse(request.getDate()));
			else
				player.setDate(new Date());
			player.setBirth(sdf.parse(request.getBirth()));
			return player;
		} else
			throw new MalformedRequestException("Error found while checking input parameters, the following ones are not present or whit a wrong type: " + errors.toString());
	}
	
}

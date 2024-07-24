package com.playerdatatracking.common;

import java.util.ArrayList;

import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;

public class Methods {

	public static ArrayList<String> playerWrongValues(MANUAL_TRACKED_PLAYER player) {
		ArrayList<String> errors = new ArrayList<String>();
		if (player.getNombre()==null || player.getNombre().equals(""))
			errors.add("Nombre");
		if ((Float)player.getNota()==null)
			errors.add("Nota");
		if (player.getClub()==null || player.getClub().equals(""))
			errors.add("Club");
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
			default:
				return -1;
				
		}
	}
	
}

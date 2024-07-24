package com.playerdatatracking.operations;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.MalformedRequestException;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.responses.GenericResponse;

@Service
@FunctionalInterface
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface GenericOperation {
	
	GenericResponse ejecutar(MANUAL_TRACKED_PLAYER player) throws PlayerDataDBException, MalformedRequestException;
}

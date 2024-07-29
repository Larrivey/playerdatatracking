package com.playerdatatracking.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.exceptions.PlayerInputException;
import com.playerdatatracking.operations.ApiFootball.GetAllLeagues;
import com.playerdatatracking.operations.manualdata.AddPlayer;
import com.playerdatatracking.operations.manualdata.GetAllPlayers;
import com.playerdatatracking.operations.manualdata.XslImport;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.requests.GenericRequest;
import com.playerdatatracking.responses.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {
	
//	---------ENVIRONMENT
	@Autowired
	private Environment env;
	@Autowired
	private ResourceLoader resourceLoader;
//	---------CLIENTS---------
	@Autowired
	private PlayerDataClient pdClient;
	
	
//	---------RFEPOSITORIES---------
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;
//  ---------OPERATIONS---------
	private AddPlayer operationAddPlayer = new AddPlayer();
	private GetAllPlayers operationGetAllPlayers = new GetAllPlayers();
	private XslImport operationXslImport = new XslImport();
	private GetAllLeagues operationGetAllLeagues = new GetAllLeagues();
	
	
// 	---------RESPONSES---------	
	GenericResponse response = new GenericResponse();
	
	
	
    @GetMapping("/players")
    public GenericResponse listPlayers() {
    	operationGetAllPlayers.setPdClient(pdClient);
    	try {
    		response = operationGetAllPlayers.ejecutar();
    	} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/xslBackendImport")
    public GenericResponse xslImport() {
    	String path = env.getProperty("xsl.path");
    	operationXslImport.setPdClient(pdClient);
    	operationXslImport.setResourceLoader(resourceLoader);
    	try {
    		response = operationXslImport.ejecutar(path);
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    }
    
    @GetMapping("/leagues")
    public GenericResponse getAllLeagues() {
    	try {
    		response = operationGetAllLeagues.ejecutar();
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    }
    
    @PostMapping("/player")
    public GenericResponse addPlayer(@RequestBody GenericRequest request) throws PlayerDataDBException, PlayerInputException, ParseException {
    	operationAddPlayer.setPdClient(pdClient);
        try {
        	MANUAL_TRACKED_PLAYER player = Methods.bindRequestAsPlayer(request);
        	response = operationAddPlayer.ejecutar(player);
        } catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }
    
    
    
    
}
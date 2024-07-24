package com.playerdatatracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.exceptions.PlayerInputException;
import com.playerdatatracking.operations.AddPlayer;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.responses.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {
//	---------CLIENTS---------
	@Autowired
	private PlayerDataClient pdClient;
	
	
//	---------RFEPOSITORIES---------
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;
//  ---------OPERATIONS---------
	private AddPlayer operation = new AddPlayer();
	
	
// 	---------RESPONSES---------	
	GenericResponse response = new GenericResponse();
	
	
	
    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", repository.findAll());
        return model.toString();
    }

    @PostMapping("/players")
    public GenericResponse addPlayer(@RequestBody MANUAL_TRACKED_PLAYER request) throws PlayerDataDBException, PlayerInputException {
    	operation.setPdClient(pdClient);
        try {
        	response = operation.ejecutar(request);
        } catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }
    
    
    
    
}
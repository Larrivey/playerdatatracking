package com.playerdatatracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.MalformedRequestException;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.operations.AddPlayer;
import com.playerdatatracking.operations.GenericOperation;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.responses.GenericResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/")
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
	GenericResponse response;
	
	
	
    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", repository.findAll());
        return model.toString();
    }

    @PostMapping("/players")
    public GenericResponse addPlayer(@RequestBody MANUAL_TRACKED_PLAYER request) throws PlayerDataDBException {
    	operation.setPdClient(pdClient);
        try {
        	response = operation.ejecutar(request);
        } catch (MalformedRequestException e) {
        	response.setCODE(Constants.CODE_ERR_MALFORMED_PARAMS);
        	response.setDescription(e.getMessage());
        }
        return response;
    }
    
    
    
    
}
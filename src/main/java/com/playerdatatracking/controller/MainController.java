package com.playerdatatracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.MalformedRequestException;
import com.playerdatatracking.operations.AddPlayer;
import com.playerdatatracking.repositories.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.responses.ManualDataResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MainController {

	
//	---------RFEPOSITORIES---------
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;

	
//  ---------OPERATIONS---------
	private AddPlayer operation;
	
	
// 	---------RESPONSES---------	
	ManualDataResponse response;
	
	
	
    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", repository.findAll());
        return model.toString();
    }

    @PostMapping("/players")
    public ManualDataResponse addPlayer(@RequestBody MANUAL_TRACKED_PLAYER request) {
        try {
        	response = operation.ejecutar(request);
        } catch (MalformedRequestException e) {
        	response.setCODE(Constants.CODE_ERR_MALFORMED_PARAMS);
        	response.setDescription(e.getMessage());
        }
        return response;
    }
}
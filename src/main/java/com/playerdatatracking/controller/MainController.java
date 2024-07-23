package com.playerdatatracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.playerdatatracking.entities.MANUAL_TRACKED_DATA;
import com.playerdatatracking.repositories.MANUAL_TRACKED_DATARepository;

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

	 @Autowired
	 private MANUAL_TRACKED_DATARepository repository;

    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", repository.findAll());
        return model.toString();
    }

    @PostMapping("/players")
    public String addPlayer(@RequestBody MANUAL_TRACKED_DATA request) {
        MANUAL_TRACKED_DATA player = new MANUAL_TRACKED_DATA();
        player.setNombre(request.getNombre());
        player.setNota(request.getNota());
        player.setClub(request.getClub());
        player.setPosicion(request.getPosicion());
        List<String> qualities = request.getQualities();
        player.setQualities(qualities != null ? qualities : new ArrayList<>());
        player.setMostLikeDestination(request.getMostLikeDestination());
        player.setLikeable(request.getLikeable());
        player.setDate(request.getDate());
        repository.save(player);
        return "redirect:/players";
    }
}
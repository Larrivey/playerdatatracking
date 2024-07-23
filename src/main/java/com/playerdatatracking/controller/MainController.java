package com.playerdatatracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.playerdatatracking.entities.MANUAL_TRACKED_DATA;
import com.playerdatatracking.repositories.MANUAL_TRACKED_DATARepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

	 @Autowired
	 private MANUAL_TRACKED_DATARepository repository;

    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", repository.findAll());
        return "players";
    }

    @PostMapping("/players")
    public String addPlayer(@RequestParam String nombre,
                            @RequestParam float nota,
                            @RequestParam String club,
                            @RequestParam(required = false) String posicion,
                            @RequestParam(required = false) List<String> qualities,
                            @RequestParam(required = false) String mostLikeDestination,
                            @RequestParam(required = false) String likeable,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        MANUAL_TRACKED_DATA player = new MANUAL_TRACKED_DATA();
        player.setNombre(nombre);
        player.setNota(nota);
        player.setClub(club);
        player.setPosicion(posicion);
        player.setQualities(qualities != null ? qualities : new ArrayList<>());
        player.setMostLikeDestination(mostLikeDestination);
        player.setLikeable(likeable);
        player.setDate(date);
        repository.save(player);
        return "redirect:/players";
    }
}
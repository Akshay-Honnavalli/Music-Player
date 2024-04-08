package com.example.musicarchivebackend.controllers;
import com.example.musicarchivebackend.model.user;
import com.example.musicarchivebackend.services.StorageService;
//import com.example.musicarchivebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
//
@Controller
public class UserController {
//    @Autowired
//    private UserService userService;
    private final StorageService storageService;
    @Autowired
    private user User;
    @Autowired
    public UserController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/search")
    public String searchSong(@RequestParam("query") String query, Model model) {
        List<String> matchedSongFileNames = User.searchsong(query);
//        List<String> matchedSongFileNames = storageService.getSongFileNames().stream()
//                .filter(title -> title.contains(query))
//                .collect(Collectors.toList());
        System.out.println("matchedSongFileNames"+matchedSongFileNames);
        model.addAttribute("songFileNames", matchedSongFileNames);
        return "index"; // Assuming your view name is index
    }
}

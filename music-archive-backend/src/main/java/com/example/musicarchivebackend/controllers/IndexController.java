package com.example.musicarchivebackend.controllers;

import com.example.musicarchivebackend.repository.SongRepository;
import com.example.musicarchivebackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final StorageService storageService;
    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getHomepage(Model model){
        //MVC - Model, View, Controller
        model.addAttribute("songFileNames", storageService.getSongFileNames());
        return "index";
    }

//    @GetMapping("/search")
//    public String searchSong(@RequestParam("query") String query, Model model) {
//        System.out.println("in searchSong func in IndexController. Query is "+query);
//        List<String> matchedSongFileNames = storageService.getSongFileNames().stream()
//                .filter(title -> title.contains(query)) // Filter songs based on query
//                .collect(Collectors.toList());
//        System.out.println(matchedSongFileNames);
//        model.addAttribute("songFileNames", matchedSongFileNames);
//        return "index"; // Assuming your view name is index2
//    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("In handle file upload func");
        storageService.uploadSong(file);
//        SongRepository.insert(song);

        return "redirect:/";
    }
}

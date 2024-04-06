package com.example.musicarchivebackend.model;

import com.example.musicarchivebackend.controllers.UserController;
import com.example.musicarchivebackend.services.StorageService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Component
public class user {
    private String _id;
    private String password;
    private String name;

    @Autowired
    private StorageService storageService; // Assuming StorageService is a Spring Bean

    public List<String> searchsong(String query) {
        System.out.println("query = "+query);
        String title2 = "";
        String uri = "mongodb+srv://akshay2004prasad:oeqFwhbToWLiLYjN@cluster0.eqmb7nu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("in try in user");
            MongoDatabase database = mongoClient.getDatabase("musicarchive");
            MongoCollection<Document> collection = database.getCollection("Song");

            Document doc = collection.find(eq("title", query)).first();
            if (doc != null) {
                System.out.println("matching documents found");
                System.out.println(doc.toJson());
                title2 = doc.getString("filename");
                System.out.println("filename: " + title2);
            } else {
                System.out.println("No matching documents found.");
                return Collections.emptyList();
            }
        }
        final String finalfilename1 = title2;

        return storageService.getSongFileNames().stream()
                .filter(title -> title.contains(finalfilename1))
                .collect(Collectors.toList());
    }



    //    @GetMapping("/search")
//    public String searchSong(@RequestParam("query") String query, Model model) {
//        List<String> matchedSongFileNames = storageService.getSongFileNames().stream()
//                .filter(title -> title.contains(query))
//                .collect(Collectors.toList());
//        System.out.println(matchedSongFileNames);
//        model.addAttribute("songFileNames", matchedSongFileNames);
//        return "index"; // Assuming your view name is index
//        System.out.println("IN usercontroller in searchsong");
//        return UserController.searchSong(query,model);
//    }
}

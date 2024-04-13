package com.example.musicarchivebackend.controllers;

import com.example.musicarchivebackend.repository.SongRepository;
import com.example.musicarchivebackend.services.StorageService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Controller
public class IndexController {

    private final StorageService storageService;
    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping
    public String getLoginpage(Model model){
        //MVC - Model, View, Controller
//        model.addAttribute("songFileNames", storageService.getSongFileNames());
        return "login2";
    }


//    @GetMapping("/index")
//    public String getindex(Model model) {
//        // Perform login logic here (e.g., check credentials)
//        // If login is successful, redirect to index.html
//        model.addAttribute("songFileNames", storageService.getSongFileNames());
//        return "index";
//    }

    @PostMapping("/index")
    public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        // Perform login logic here (e.g., check credentials)
        // If login is successful, redirect to index.html
        System.out.println("IN post mapping /index");
        String emailid = "";
        String passwd = "";

        String uri = "mongodb+srv://akshay2004prasad:oeqFwhbToWLiLYjN@cluster0.eqmb7nu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("in try in user");
            MongoDatabase database = mongoClient.getDatabase("musicarchive");
            MongoCollection<Document> collection = database.getCollection("Login");

            Document doc = collection.find(eq("email", email)).first();
            if (doc != null) {
                System.out.println("matching documents found");
                System.out.println(doc.toJson());
                passwd = doc.getString("password");
                System.out.println("passwd: " + passwd);
                if(passwd.equals(password)){
//                    return "redirect:/index";
//                    session.setAttribute("loggedInUser", email);
                    model.addAttribute("songFileNames", storageService.getSongFileNames());
                    return "index";
                }
                else{
                    return "redirect:/";
                }
            } else {
                System.out.println("No matching documents found.");
                return "redirect:/";
            }
        }
//        return "redirect:/index";
    }


    @PostMapping("/signup")
    public String handleSignup(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirm") String confirm,@RequestParam("role") String role,Model model) {
        // Perform login logic here (e.g., check credentials)
        // If login is successful, redirect to index.html
        System.out.println("IN post mapping /signup");

        if(password.equals(confirm)){
            String uri = "mongodb+srv://akshay2004prasad:oeqFwhbToWLiLYjN@cluster0.eqmb7nu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            try (MongoClient mongoClient = MongoClients.create(uri)) {
                // Connect to the 'musicarchive' database
                MongoDatabase database = mongoClient.getDatabase("musicarchive");

                // Get the 'Login' collection
                MongoCollection<Document> collection = database.getCollection("Login");

                // Create a document to insert
                Document document = new Document("email", email)
                        .append("password", password)
                        .append("role",role);

                System.out.println(document);
                // Insert the document into the 'Login' collection
                collection.insertOne(document);

                System.out.println("Document inserted successfully");
//                return "redirect:/index";
                model.addAttribute("songFileNames", storageService.getSongFileNames());
                if(role.equals("artist")) {
//                    return "index";
                    return "redirect:/index";
                }
                else{
                    return "index2";
                }
            } catch (Exception e) {
                System.err.println("Unable to insert due to an error: " + e);
            }
        }
        else {
            return "redirect:/";
        }
//            return "redirect:/index";
            model.addAttribute("songFileNames", storageService.getSongFileNames());
            return "index";
        }







//    @PostMapping("/index")
//    public String getHomepage(Model model){
//        //MVC - Model, View, Controller
//        model.addAttribute("songFileNames", storageService.getSongFileNames());
//        return "index";
//    }



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

    @PostMapping("/main")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,Model model) throws IOException {
        System.out.println("In handle file upload func");
        storageService.uploadSong(file);
//        SongRepository.insert(song);

//        return "redirect:/index";

        model.addAttribute("songFileNames", storageService.getSongFileNames());
        return "index";
    }
}


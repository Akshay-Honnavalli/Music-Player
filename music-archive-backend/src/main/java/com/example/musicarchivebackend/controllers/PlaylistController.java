package com.example.musicarchivebackend.controllers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.example.musicarchivebackend.model.Playlist;
import com.example.musicarchivebackend.services.PlaylistService;
import com.mongodb.client.MongoClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
//import com.example.musicarchivebackend.model.Song;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class PlaylistController {

    private String Id; 
    @Autowired
    private PlaylistService playlistService;
    @GetMapping("/index/playlists")
    public String viewPlaylists(Model model) 
    {
        // this will be the view page for the Playlists
        //Now I need to add new functions to add a song in the repo to the playlist
        //playlistService.createPlaylist("Testwithsongs"); -> UNCOMMENT THIS LATER
        List<Playlist> playlists = playlistService.getAllPlaylists();
        model.addAttribute("playlists", playlists);
        return "playlist";
    }

    @GetMapping("/index/playlists/create")
    public String displayCreationPage()
    {
        System.out.println("Yep this is getting called and displaying your page");
        return "create";
    }

    @PostMapping("/index/playlists/create")
    public String handlePlayListCreation(@RequestParam("playlistName") String playlistName, @RequestParam("songNames") String songNames)
    {
                
        String[] songs = songNames.split(",");
        List<String> songList = new ArrayList<>();
        for(int i = 0;i < songs.length; i++)
        {   
            songList.add(songs[i]); //Lists are easier to work with
        }
        
        System.out.println("Yep this is getting called after form submission" + playlistName);
        playlistService.createPlaylist(playlistName, songList);
        return "redirect:/index/playlists";
    }

    /* @GetMapping("/index/playlists/addsong")
    public String addSongPage() 
    {
        System.out.println("page displayed?");
        return "addsong";
    }
    
    @PostMapping("/index/playlists/addsong")
    public String addSongToList(@RequestParam("songNames") String songNames, @RequestParam("playlistId") String playlistId) 
    {
        //RERUN
        System.out.println("HELOO PRINTING VALUE:::" + playlistId);
        return "redirect:/index/playlists";
    } */
    @GetMapping("/index/playlists/addsong")
    public String addSongToPlaylist(@RequestParam("playlistId") String playlistId, Model model) 
    {
        System.out.println("INSIDE GET!");
        System.out.println("HELOO PRINTING VALUE:::" + playlistId);
        Id = playlistId;
        return "addsong";
    }

    @PostMapping("/index/playlists/addsong")
    public String addSongToList(@RequestParam("songNames") String songNames) 
    {
        String playlistId = Id;
        System.out.println("HELOO PRINTING VALUE:::" + playlistId);
        String uri = "mongodb+srv://akshay2004prasad:oeqFwhbToWLiLYjN@cluster0.eqmb7nu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) 
        {
            // Connect to the 'musicarchive' database
            MongoDatabase database = mongoClient.getDatabase("musicarchive");

            // Get the 'Login' collection
            MongoCollection<Document> collection = database.getCollection("playlist");
            String[] songs = songNames.split(",");
            List<String> songList = new ArrayList<>();
            for(int i = 0;i < songs.length; i++)
            {   
                songList.add(songs[i]); //Lists are easier to work with
                collection.updateOne(Filters.eq("_id", new ObjectId(playlistId)), Updates.push("songs", songList.get(i)));
            }
        return "redirect:/index/playlists";
    }
}
}

package com.example.musicarchivebackend.controllers;

import com.example.musicarchivebackend.model.Song;
import com.example.musicarchivebackend.repository.SongRepository;
import com.example.musicarchivebackend.services.StorageService;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/songs")
public class SongController {

    private final StorageService storageService;
    private final SongRepository songRepository;

    public SongController(StorageService storageService, SongRepository songRepository) {
        this.storageService = storageService;
        this.songRepository = songRepository;
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Song>> searchsong() {
//        System.out.println("IM in get searchsng function");
//        System.out.println("songRepository.search = " + songRepository.findAll());
//        return ResponseEntity.ok(songRepository.findAll());
//    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Song>> searchSongs(@RequestParam("query") String query) {
//        List<Song> matchedSongs = songRepository.findByTitleContainingIgnoreCase(query);
//        System.out.println(matchedSongs);
//        return ResponseEntity.ok(matchedSongs);
//    }

    @GetMapping("/redirectToIndex2")
    public String redirectToIndex2() {
        return "index2";
//        return "redirect:/index2.html";
    }

    @GetMapping("/api/songs")
    public ResponseEntity<List<Song>> getSongs(){
        System.out.println("IM in get SOngs function");
        System.out.println("songRepository.findAll() = "+songRepository.findAll());
//        return ResponseEntity.ok(songRepository.findAll());
//        List<Song> songss = songRepository.findAll();

        return ResponseEntity.ok(songRepository.findAll());


//        String uri = "mongodb+srv://akshay2004prasad:oeqFwhbToWLiLYjN@cluster0.eqmb7nu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//            MongoDatabase database = mongoClient.getDatabase("musicarchive");
//            MongoCollection<Document> collection = database.getCollection("songs");
//
//            // Find all documents in the collection
//            FindIterable<Document> documents = collection.find();
//
//            // Create a list to store songs
//            List<Song> songs = new ArrayList<>();

            // Iterate over the documents and extract titles
//            for (Document doc : documents) {
//                Song song = new Song();
////                song.setId(doc.getObjectId("_id").toString());
//                song.setTitle(doc.getString("title"));
//                song.setArtist(doc.getString("artist"));
//                song.setArtist(doc.getString("artist"));
//                song.setIsFavorited(doc.getBoolean("isfavorited"));
//                // Set other fields as needed
//
//                songs.add(song);
//            }
//
//            // Return ResponseEntity with the list of songs
//            return ResponseEntity.ok(songs);
//        } catch (Exception e) {
//            // Handle exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }

//        return ResponseEntity.ok(songRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable String id){
        Optional<Song> song  = songRepository.findById(id);
        return song.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createSong(@RequestPart("song")Song song, @RequestPart("file") MultipartFile file) throws IOException {

        //See if there is already a song with that filename
        if (songRepository.existsSongByFileNameEquals(file.getOriginalFilename()) || songRepository.existsSongByTitleEquals(song.getTitle())){
            return ResponseEntity.badRequest().body("taken");
        }else{
            System.out.println("Uploading the file...");
            storageService.uploadSong(file);

            //Saving the song data into the database
            song.setFileName(file.getOriginalFilename());
            System.out.println("song = "+song);
            Song insertedSong = songRepository.insert(song);
            System.out.println("inserted song = "+insertedSong);

            return new ResponseEntity<>(insertedSong, HttpStatus.CREATED);
        }

    }

}

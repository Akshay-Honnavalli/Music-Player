package com.example.musicarchivebackend.controllers;

import com.example.musicarchivebackend.model.Song;
import com.example.musicarchivebackend.repository.SongRepository;
import com.example.musicarchivebackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final StorageService storageService;
    private final SongRepository songRepository;

    public SongController(StorageService storageService, SongRepository songRepository) {
        this.storageService = storageService;
        this.songRepository = songRepository;
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongs(){
        System.out.println(songRepository.findAll());
        return ResponseEntity.ok(songRepository.findAll());
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
            Song insertedSong = songRepository.insert(song);

            return new ResponseEntity<>(insertedSong, HttpStatus.CREATED);
        }

    }


}

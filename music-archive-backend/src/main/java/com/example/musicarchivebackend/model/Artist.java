package com.example.musicarchivebackend.model;

import com.example.musicarchivebackend.repository.SongRepository;
import com.example.musicarchivebackend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Component
public class Artist {
    private String _id;
    private String password;
    private String name;

    @Autowired
    private StorageService storageService;

    @Autowired
    SongRepository songRepository;

    public ResponseEntity<?> uploadSong(@RequestPart("song")Song song, @RequestPart("file") MultipartFile file) throws IOException {


        if (songRepository.existsSongByFileNameEquals(file.getOriginalFilename()) || songRepository.existsSongByTitleEquals(song.getTitle())) {
            return ResponseEntity.badRequest().body("taken");
        } else {
            System.out.println("Uploading the file...");
            storageService.uploadSong(file);

            //Saving the song data into the database
            song.setFileName(file.getOriginalFilename());
            System.out.println("song = " + song);
            Song insertedSong = songRepository.insert(song);
            System.out.println("inserted song = " + insertedSong);

            return new ResponseEntity<>(insertedSong, HttpStatus.CREATED);
        }
    }
}

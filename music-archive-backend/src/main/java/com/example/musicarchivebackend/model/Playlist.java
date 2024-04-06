package com.example.musicarchivebackend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "playlists")
public class Playlist {
    @Id
    private String id;
    @Setter
    private String userId; // Assuming each playlist is associated with a user ID
    @Getter
    private List<Song> songs; // List of Song objects

//    public Playlist(String userId) {
//        this.userId = userId;
//        this.songs = new ArrayList<>();
//    }

    public String getUserId() {
        return userId;
    }


    // Method to add a song to the playlist
//    public void addSong(Song song) {
//        songs.add(song);
//    }
//
//    // Method to remove a song from the playlist
//    public void removeSong(Song song) {
//        songs.remove(song);
//    }
//
//    // Method to get the list of songs in the playlist
//    public List<Song> getSongs() {
//        return songs;
//    }
//
//    // Method to get the user ID associated with the playlist
//    public String getUserId() {
//        return userId;
//    }

    // Method to set the user ID associated with the playlist

}

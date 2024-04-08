package com.example.musicarchivebackend.controllers;

import com.example.musicarchivebackend.model.Playlist;
import com.example.musicarchivebackend.model.Song;
import com.example.musicarchivebackend.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/create")
    public Playlist createPlaylist(@RequestParam String userId) {
        return playlistService.createPlaylist(userId);
    }

    @PostMapping("/addsong")
    public Playlist addSongToPlaylist(@RequestParam String playlistId, @RequestBody Song song) {
        return playlistService.addSongToPlaylist(playlistId, song);
    }

    @GetMapping("/view")
    public List<Song> viewSongsInPlaylist(@RequestParam String playlistId) {
        return playlistService.getSongsInPlaylist(playlistId);
    }
}


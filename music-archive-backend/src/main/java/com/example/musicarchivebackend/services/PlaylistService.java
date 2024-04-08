package com.example.musicarchivebackend.services;

import com.example.musicarchivebackend.model.Playlist;
import com.example.musicarchivebackend.model.Song;
import com.example.musicarchivebackend.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist createPlaylist(String userId) {
        Playlist playlist = new Playlist();
        playlist.setUserId(userId);
        return playlistRepository.save(playlist);
    }

    public Playlist addSongToPlaylist(String playlistId, Song song) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        playlist.getSongs().add(song);
        return playlistRepository.save(playlist);
    }

    public List<Song> getSongsInPlaylist(String playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        return playlist.getSongs();
    }
}

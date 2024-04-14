package com.example.musicarchivebackend.services;

import com.example.musicarchivebackend.controllers.PlaylistController;
import com.example.musicarchivebackend.model.Playlist;
import com.example.musicarchivebackend.model.Song;
import com.example.musicarchivebackend.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> getAllPlaylists(String userID)
    {
//        System.out.println(playlistRepository.findAll());
//        return playlistRepository.findAll();
        System.out.println(playlistRepository.findAllByUserID(userID));
        return playlistRepository.findAllByUserID(userID);
    }

    public Playlist createPlaylist(String name, List<String> songs,String userid)
    {
        //this will create the playlist constructor

        Playlist playlist = new Playlist(name,userid);

        //this will add the songs to the playlist
        for(int i = 0;i<songs.size();i++)
        {
            playlist.addSongToList(playlist.getSongList(), songs.get(i));
        }
        return playlistRepository.save(playlist);
    }

    public int TestAddDummySong(Playlist playlist)
    {
        playlist.setName("Test-playlist");
        playlist.setId("1");
        playlist.addSongToList(playlist.getSongList(),"test-sharma-2.mp3"); 
        // in the future this should come from the HTML form.
        return 0;
    }
}

package com.example.musicarchivebackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "playlist")
public class Playlist {

    @Id
    private String id;
    
    @Field(value = "playlist_name")
    private String name;

    @Field(value = "songs")
    private List<String> songList;

    // Constructors, getters, setters

    public Playlist() {
        //this constructor will be used to create a new playlist
        this.songList = new ArrayList<>();
    }

    public Playlist(String name) 
    {
        // this will create a playlist with a name
        //again an empty playlist
        this.name = name;
        this.songList = new ArrayList<>();
        //this.songList.add("Test song compulory in all playlists");
        //to be removed debug
    }

    // Add other fields as needed

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSongList() {
        return songList;
    }

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    public void addSongToList(List<String> songList, String songName)
    {
        this.songList.add(songName);
    }
}

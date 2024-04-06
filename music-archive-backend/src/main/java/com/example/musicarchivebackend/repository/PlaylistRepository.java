package com.example.musicarchivebackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.musicarchivebackend.model.Playlist;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    // Custom query methods can be defined here if needed
}

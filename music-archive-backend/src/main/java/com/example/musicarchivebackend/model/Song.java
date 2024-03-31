package com.example.musicarchivebackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Song {
    @Id
    private String id;

    private String filename;

    private String title;

    private String artist;
    private Boolean isFavorited;
    public void setFileName(String originalFilename) {
        // TODO Auto-generated method stub
        this.filename = originalFilename;
        throw new UnsupportedOperationException("Unimplemented method 'setFileName'");
    }

}

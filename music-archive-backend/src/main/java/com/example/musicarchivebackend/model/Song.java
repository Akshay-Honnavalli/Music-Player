package com.example.musicarchivebackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

//@Document
@Data
@Document(collection = "Song")
public class Song {
    @MongoId(FieldType.OBJECT_ID)
    private String _id;
    @Field(value = "filename")
    private String filename;

    @Field(value = "title")
    private String title;

    @Field(value = "artist")
    private String artist;

    @Field(value = "isFavorited")
    private Boolean isFavorited;
    public void setFileName(String originalFilename) {
        // TODO Auto-generated method stub
        this.filename = originalFilename;
        throw new UnsupportedOperationException("Unimplemented method 'setFileName'");
    }

}

package com.example.musicarchivebackend;

// import static org.mockito.ArgumentMatchers.eq;

import com.example.musicarchivebackend.services.StorageService;
import org.bson.Document;

// import com.mongodb.*;
// import org.bson.BsonDocument;
// import org.bson.BsonInt64;
// import org.bson.Document;
// import org.bson.conversions.Bson;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class MusicArchiveBackendApplication {

	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MusicArchiveBackendApplication.class, args);

        StorageService storageService = context.getBean(StorageService.class);

        System.out.println(storageService.getSongFileNames());

//		String uri = "mongodb+srv://akshay2004prasad:oeqFwhbToWLiLYjN@cluster0.eqmb7nu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
//
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//            System.out.println("HIIIIIIIII  !!!!!!!!!!!!!!!!!");
//            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
//            MongoCollection<Document> collection = database.getCollection("movies");
//
//            Document doc = collection.find(eq("title", "Back to the Future")).first();
//            if (doc != null) {
//                System.out.println("HELOOOOOOOO!!!!!!!!!!!!!!!!!");
//                System.out.println(doc.toJson());
//            } else {
//                System.out.println("No matching documents found.");
//            }
//        }

	}

}

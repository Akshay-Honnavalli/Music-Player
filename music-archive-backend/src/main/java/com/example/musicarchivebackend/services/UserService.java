//package com.example.musicarchivebackend.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class UserService {
//    @Autowired
//    private StorageService storageService;
//
//    public List<String> searchSongsByUser(String query) {
//        System.out.println("in searchSongByUser method in UserService. Query is " + query);
//        // Assuming getSongFileNamesForUser is a method in StorageService that returns songs for a user
//        List<String> matchedSongFileNames = storageService.getSongFileNames().stream()
//                .filter(title -> title.contains(query))
//                .collect(Collectors.toList());
//        System.out.println(matchedSongFileNames);
//        return matchedSongFileNames;
//    }
//}

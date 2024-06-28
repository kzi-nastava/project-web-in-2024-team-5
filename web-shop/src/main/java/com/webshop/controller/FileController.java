package com.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/uploads")
public class FileController {

    private static final String UPLOAD_DIR = "./src/main/resources/superfrontend/public/assets/";

    @PostMapping("")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Generate a unique filename to prevent overwriting existing files
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // Create directories if they don't exist
            Files.createDirectories(path.getParent());

            // Save the file bytes to the specified path
            Files.write(path, file.getBytes());

            // Return the URL or path where the file can be accessed
            String fileUrl = "/uploads/" + fileName; // Adjust as per your server setup
            return ResponseEntity.ok().body(Collections.singletonMap("imageUrl", fileUrl));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }
}

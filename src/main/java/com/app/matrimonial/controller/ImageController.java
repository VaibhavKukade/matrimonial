package com.app.matrimonial.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/api/v1/images")
@RestController
public class ImageController {

    private static final String IMAGE_DIRECTORY = "C:\\NgoProject\\Images\\";

    @GetMapping("/getImage/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        try {
            String imagePath = IMAGE_DIRECTORY + imageName; // Full path to the image
            File imageFile = new File(imagePath);

            if (imageFile.exists() && imageFile.isFile()) {
                // Read the image file and convert it to byte[]
                byte[] imageData = new byte[(int) imageFile.length()];
                try (InputStream in = new FileInputStream(imageFile)) {
                    in.read(imageData);
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(imageData);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageName") String imageName, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please upload a file");
            }

            // Check if the directory exists, create it if not
            File directory = new File(IMAGE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the uploaded file to the specified location
            String filePath = IMAGE_DIRECTORY + imageName;
            file.transferTo(new File(filePath));
            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}

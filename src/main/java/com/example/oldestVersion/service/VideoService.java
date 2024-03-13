package com.example.oldestVersion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class VideoService {
    public String uploadVideo(@RequestParam("video") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                String extension = "";
                if (originalFileName != null && originalFileName.lastIndexOf(".") != -1) {
                    extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                }

                String filename = UUID.randomUUID().toString() + extension;
                String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/videos/";
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(filename);
                file.transferTo(filePath.toFile());

                // URL 인코딩
                String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
                return encodedFilename;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}

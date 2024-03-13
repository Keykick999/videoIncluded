package com.example.oldestVersion.controller;

import com.example.oldestVersion.entity.Comment2;
import com.example.oldestVersion.entity.Content2;
import com.example.oldestVersion.repository.CommentRepository;
import com.example.oldestVersion.repository.ContentRepository;
import com.example.oldestVersion.service.ContentService;
import com.example.oldestVersion.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class VideoController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContentService contentService;

    @Autowired
    VideoService videoService;



    //<refactor>
    //DB -> server -> model -> view
    //매번 댓글 불러옴 -> 속도 느려질 수 o
    @GetMapping("/video/{id}")
    public String videoPage(Model model,
                            @PathVariable("id") Long contentId) {
        //저장 가정(html쪽에서 contentId값 가져와야)

        Optional<Content2> findContent = contentRepository.findById(contentId);
        List<Comment2> commentList = commentRepository.findByContentId(contentId);

        //접속 -> 조회수+=1;
        Content2 targetContent = findContent.get();
        targetContent.plusView();
        System.out.println(targetContent.getViews());
        contentService.update(targetContent);

        model.addAttribute("content",findContent.get());
        model.addAttribute("comments",commentList);
        return "video";
    }


    //댓글 저장
    @PostMapping("/comment")
    public String addComment(@RequestParam("contentId") Long contentId,
                             @RequestParam("comment") String comment) {
            System.out.println(contentId);
            System.out.println(comment);
            commentRepository.save(new Comment2(comment,"brian1205",contentId));
        return "redirect:/video/" + contentId;
    }



    //게시글 작성(영상 포함)
    @GetMapping("/addContent")
    public String addContent() {
        return "content";
    }


    //게시글 저장
    @PostMapping("/addContent")
    public String addContentToDB(@RequestParam("title") String title,
                                 @RequestParam("context") String context,
                                 @RequestParam("author") String author,
                                 @RequestParam("video") MultipartFile file) {
        String encodedFilename = videoService.uploadVideo(file);
        contentRepository.save(new Content2(title,author,context,encodedFilename,0));
        if(encodedFilename != null){
            return "redirect:/showVideo/" + encodedFilename;

        } else{
            return "redirect:/uploadFailure.html";
        }
    }




    //전체 목록 페이지(조회만)
    @GetMapping("/index")
    public String list(Model model) {
        //게시글 전체 조회
        List<Content2> contentList = contentRepository.findAll();
        //전체 게시글 뷰에 등록
        model.addAttribute("contents",contentList);
        return "index";
    }



    //파일 업로드
    @GetMapping("/upload/file")
    public String uploadFile(){
        return "upload.html";
    }


    // 파일 저장 경로 변경: 외부 디렉토리를 사용하는 예
    @PostMapping("/uploadVideo")
    public String uploadVideo(@RequestParam("video") MultipartFile file, RedirectAttributes redirectAttributes) {
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
                return "redirect:/showVideo/" + encodedFilename;
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/uploadFailure.html";
            }
        } else {
            return "redirect:/uploadFailure.html";
        }
    }






    @GetMapping("/showVideo/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/videos/";

        try {
            Path file = Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                String mimeType = "application/octet-stream";
                try {
                    mimeType = Files.probeContentType(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(mimeType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping("/view/video/{filename}")
    public String showVideoPage(@PathVariable String filename, Model model) {
        model.addAttribute("videoFilename", filename);
        return "showVideo"; // 비디오를 보여주는 Thymeleaf 템플릿 페이지
    }




    @GetMapping("/show/video")
    public String showVideo(Model model) {
        String url = "/static/videos/myVideo.mp4";
        model.addAttribute("videoUrl",url);
        return "showVideo";
    }
}

package com.example.oldestVersion.service;

import com.example.oldestVersion.entity.Content2;
import com.example.oldestVersion.repository.ContentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentService {
    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

//    @Transactional
//    public void updateContent(Long id) {
//        Content2 findContent = contentRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
//        findContent.plusView();
//        contentRepository.save(findContent);
//    }

    @Transactional
    public void update(Content2 content) {
        contentRepository.save(content);
    }
}

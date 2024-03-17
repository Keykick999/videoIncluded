package com.example.oldestVersion.repository;

import com.example.oldestVersion.entity.Comment2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment2, Long> {
    List<Comment2> findByContentId(Long contentId);
}

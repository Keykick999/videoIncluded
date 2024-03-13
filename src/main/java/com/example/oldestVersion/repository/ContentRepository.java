package com.example.oldestVersion.repository;

import com.example.oldestVersion.entity.Content2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content2,Long> {
}

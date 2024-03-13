package com.example.oldestVersion.repository;

import com.example.oldestVersion.entity.Comment2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testCRUD() {
    }
}

//package com.example.oldestVersion.service;
//
//import com.example.oldestVersion.entity.Content2;
//import com.example.oldestVersion.repository.ContentRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ContentServiceTest {
//
//    @Mock
//    private ContentRepository contentRepository;
//
//    @InjectMocks
//    private ContentService contentService;
//
//    private Content2 content2;
//
//    @BeforeEach
//    void setUp() {
//        // Sample content entity setup
//        content2 = new Content2();
//        content2.setId(1L);
//        content2.setView(0); // Assuming Content2 entity has a views field
//    }
//
//    @Test
//    public void updateComment_IncrementsViewCount() {
//        // Given
//        when(contentRepository.findById(anyLong())).thenReturn(Optional.of(content2));
//
//        // When
//        contentService.updateContent(1L);
//
//        // Then
//        verify(contentRepository).findById(1L);
//        assertEquals(1, content2.getView()); // Assuming Content2 entity has a getViews() method
//        verify(contentRepository).save(content2);
//    }
//
//    @Test
//    public void updateComment_ThrowsEntityNotFoundException_IfContentNotFound() {
//        // Given
//        when(contentRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Then
//        assertThrows(EntityNotFoundException.class, () -> {
//            // When
//            contentService.updateContent(1L);
//        });
//    }
//}

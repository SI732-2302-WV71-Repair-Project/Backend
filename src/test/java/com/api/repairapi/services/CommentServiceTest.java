package com.api.repairapi.services;

import com.api.repairapi.models.CommentModel;
import com.api.repairapi.repositories.ICommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

public class CommentServiceTest {

    @Mock
    private ICommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetComments() {
        when(commentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        var comments = commentService.getComments();

        // Assert
        Assertions.assertNotNull(comments);
    }

    @Test
    public void testGetCommentById() {
        var mockComment = new CommentModel();
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(mockComment));

        // Act
        var commentResult = commentService.getCommentById(1L);

        // Assert
        Assertions.assertTrue(commentResult.isPresent());
    }

    @Test
    public void testSaveComment() {
        var mockComment = new CommentModel();
        when(commentRepository.save(any(CommentModel.class))).thenReturn(mockComment);

        // Act
        var savedComment = commentService.saveComment(mockComment);

        // Assert
        Assertions.assertNotNull(savedComment);
    }

    @Test
    public void testUpdateComment() {
        var mockComment = new CommentModel();
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(mockComment));

        when(commentRepository.save(any(CommentModel.class))).thenReturn(mockComment);

        // Act
        var updatedComment = commentService.updateComment(mockComment, 1L);

        // Assert
        Assertions.assertNotNull(updatedComment);
    }

    @Test
    public void testDeleteComment() {
        doNothing().when(commentRepository).deleteById(anyLong());

        // Act & Assert
        commentService.deleteComment(1L);
    }

    @Test
    public void testDeleteCommentWithException() {
        doThrow(new RuntimeException("Database error")).when(commentRepository).deleteById(anyLong());

        commentService.deleteComment(1L);
    }
}

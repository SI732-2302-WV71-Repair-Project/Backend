package com.api.repairapi.controllers;
import com.api.repairapi.models.CommentModel;
import com.api.repairapi.services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void testGetComments() throws Exception {
        // Simular la respuesta del servicio
        ArrayList<CommentModel> comments = new ArrayList<>();
        comments.add(new CommentModel());
        when(commentService.getComments()).thenReturn(comments);

        mockMvc.perform(get("/comment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(commentService, times(1)).getComments();
    }

    @Test
    public void testGetCommentById() throws Exception {
        Long commentId = 1L;
        CommentModel comment = new CommentModel();
        comment.setId(commentId);

        // Simular la respuesta del servicio
        when(commentService.getCommentById(commentId)).thenReturn(Optional.of(comment));

        mockMvc.perform(get("/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(comment.getId()));

        verify(commentService, times(1)).getCommentById(commentId);
    }

    @Test
    public void testSaveComment() throws Exception {
        CommentModel comment = new CommentModel();
        comment.setId(1L);

        // Simular la respuesta del servicio
        when(commentService.saveComment(any(CommentModel.class))).thenReturn(comment);

        mockMvc.perform(post("/comment")
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(comment.getId()));

        verify(commentService, times(1)).saveComment(any(CommentModel.class));
    }

    @Test
    public void testUpdateComment() throws Exception {
        Long commentId = 1L;
        CommentModel comment = new CommentModel();
        comment.setId(commentId);

        // Simular la respuesta del servicio
        when(commentService.updateComment(any(CommentModel.class), eq(commentId))).thenReturn(comment);

        mockMvc.perform(put("/comment/{id}", commentId)
                        .content(asJsonString(comment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(comment.getId()));

        verify(commentService, times(1)).updateComment(any(CommentModel.class), eq(commentId));
    }

    @Test
    public void testDeleteComment() throws Exception {
        Long commentId = 1L;

        mockMvc.perform(delete("/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(commentService, times(1)).deleteComment(commentId);
    }

    // Utilidad para convertir objetos a JSON (puede ser la misma que usaste en otras pruebas)
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

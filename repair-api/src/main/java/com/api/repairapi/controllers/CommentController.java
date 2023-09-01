package com.api.repairapi.controllers;

import com.api.repairapi.models.CommentModel;
import com.api.repairapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ArrayList<CommentModel> getComments(){
        return this.commentService.getComments();
    }

    @GetMapping(path = "/{id}")
    public Optional<CommentModel> getCommentById(@PathVariable Long id){
        return this.commentService.getCommentById(id);
    }

    @PostMapping
    public CommentModel saveComment(@RequestBody CommentModel comment){
        return this.commentService.saveComment(comment);
    }

    @PutMapping(path = "/{id}")
    public CommentModel updateComment(@RequestBody CommentModel request, @PathVariable Long id){
        return this.commentService.updateComment(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComment(@PathVariable Long id){
        this.commentService.deleteComment(id);
    }
}

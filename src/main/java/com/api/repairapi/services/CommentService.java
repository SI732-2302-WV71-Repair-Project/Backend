package com.api.repairapi.services;

import com.api.repairapi.models.CommentModel;
import com.api.repairapi.repositories.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private ICommentRepository commentRepository;

    public ArrayList<CommentModel> getComments()
    {
        return (ArrayList<CommentModel>) this.commentRepository.findAll();
    }

    public Optional<CommentModel> getCommentById(Long id){
        return this.commentRepository.findById(id);
    }

    public CommentModel saveComment(CommentModel comment){
        return this.commentRepository.save(comment);
    }

    public CommentModel updateComment(CommentModel request, Long id){
        CommentModel commentModel = this.commentRepository.findById(id).get();

        commentModel.setDescription(request.getDescription());
        commentModel.setPublishDate(request.getPublishDate());
        commentModel.setRating(request.getRating());
        commentModel.setClient(request.getClient());
        commentModel.setTechnician(request.getTechnician());

        return this.commentRepository.save(commentModel);
    }

    public void deleteComment(Long id){
        try{
            this.commentRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

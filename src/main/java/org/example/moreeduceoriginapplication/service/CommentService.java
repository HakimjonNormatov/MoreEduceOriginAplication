package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.dto.CommentDto;
import org.example.moreeduceoriginapplication.model.Comment;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public List<Comment> getAll() {
        return commentRepo.findAll();
    }
    public Comment getById(Long id) {
        return commentRepo.findById(id).get();
    }
    public Result create(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setComment(commentDto.getComment());
        comment.setLike_id(commentDto.getLike_id());
        commentRepo.save(comment);
    return new Result(true, "Comment created");
    }
    public Result update(Long id , CommentDto commentDto) {
        Comment comment = commentRepo.findById(id).get();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setComment(commentDto.getComment());
        comment.setLike_id(commentDto.getLike_id());
        commentRepo.save(comment);
        return new Result(true, "Comment updated");
    }
    public Result delete(Long id) {
        Comment comment = commentRepo.findById(id).get();
        commentRepo.delete(comment);
        return new Result(true, "Comment deleted");
    }
}

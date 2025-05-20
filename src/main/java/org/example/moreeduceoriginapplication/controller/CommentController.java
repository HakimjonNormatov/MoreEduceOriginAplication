package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.CommentDto;
import org.example.moreeduceoriginapplication.model.Comment;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/{id}")
    public Comment getById(Long id){
        return commentService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CommentDto commentDto){
        Result comment= commentService.create(commentDto);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> update(@RequestBody CommentDto commentDto, @PathVariable Long id){
        Result comment= commentService.update(id , commentDto);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        Result comment= commentService.delete(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}

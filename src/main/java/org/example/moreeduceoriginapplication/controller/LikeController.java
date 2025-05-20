package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.LikeDto;
import org.example.moreeduceoriginapplication.model.Like;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping
    public List<Like> getAll() {
        return likeService.getAll();
    }

    @GetMapping("/{id}")
    public Like getById(@PathVariable Long id) {
        return likeService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> createLike(@RequestBody LikeDto likeDto) {
        Result result = likeService.create(likeDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteLike(@PathVariable Long id) {
        Result result = likeService.deleteLike(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

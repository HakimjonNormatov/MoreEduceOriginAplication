package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.VideoDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Video;
import org.example.moreeduceoriginapplication.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/{id}")
    public Video getById(Long id){
        return videoService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody VideoDto videoDto){
        Result video= videoService.create(videoDto);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> update(@RequestBody VideoDto videoDto, @PathVariable Long id){
        Result video= videoService.update(videoDto,id);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        Result video= videoService.delete(id);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
}

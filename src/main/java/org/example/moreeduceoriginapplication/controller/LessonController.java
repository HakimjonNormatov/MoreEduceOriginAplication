package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.LessonDto;
import org.example.moreeduceoriginapplication.model.Lesson;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @GetMapping
    public List<Lesson> getALl() {
        return lessonService.getAll();
    }

    @GetMapping("/{id}")
    public Lesson getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody LessonDto lessonDto) {
        Result student = lessonService.create(lessonDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        Result result = lessonService.update(id , lessonDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        Result result = lessonService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

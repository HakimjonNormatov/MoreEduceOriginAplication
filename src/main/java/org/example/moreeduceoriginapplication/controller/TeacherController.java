package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.TeacherDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Teacher;
import org.example.moreeduceoriginapplication.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;


    @GetMapping
    public List<Teacher>getAll(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id){
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public HttpEntity<?>add(@RequestBody TeacherDto teacherDto){
        Result result = teacherService.addTeacher(teacherDto);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<?>put(@RequestBody TeacherDto teacherDto , @PathVariable Long id){
        Result result = teacherService.updateTeacher(id , teacherDto);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?>delete(@PathVariable Long id){
        Result result = teacherService.deleteTeacher(id);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

}

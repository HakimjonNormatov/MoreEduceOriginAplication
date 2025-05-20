package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.StudentDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Students;
import org.example.moreeduceoriginapplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Students> getALl() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Students getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody StudentDto studentDto) {
        Result student = studentService.createStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Result result = studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        Result result = studentService.deleteStudent(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

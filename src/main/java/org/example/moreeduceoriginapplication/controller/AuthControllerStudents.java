package org.example.moreeduceoriginapplication.controller;

import jakarta.validation.Valid;
import org.example.moreeduceoriginapplication.dto.StudentDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Student/auth")
public class AuthControllerStudents {

    @Autowired
    StudentService studentService;

    @PostMapping(value = "/Student/registration")
    public ResponseEntity<Result>registration(@RequestBody @Valid StudentDto dto){
        Result result = studentService.createStudent(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/Student/verify")
    public ResponseEntity<Result>verify2(@RequestParam String token){
        Result verify = studentService.verify(token);
        return ResponseEntity.ok(verify);
    }


}

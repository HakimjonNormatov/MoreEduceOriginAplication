package org.example.moreeduceoriginapplication.controller;

import jakarta.validation.Valid;
import org.example.moreeduceoriginapplication.dto.AuthResponse;
import org.example.moreeduceoriginapplication.dto.LoginRequest;
import org.example.moreeduceoriginapplication.dto.StudentDto;
import org.example.moreeduceoriginapplication.dto.TeacherDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.StudentService;
import org.example.moreeduceoriginapplication.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    TeacherService teacherService;
//    @Autowired
//    StudentService studentService;


    @PostMapping(value = "/registration")
    public ResponseEntity<Result>registration(@RequestBody @Valid TeacherDto dto){
        Result result = teacherService.addTeacher(dto);
        return ResponseEntity.ok(result);
    }


    @PostMapping(value = "/Teacher/verify")
    public ResponseEntity<AuthResponse>verify1(@RequestParam String token){
        AuthResponse verify = teacherService.verify(token);
        return ResponseEntity.ok(verify);
    }



    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse>login(@RequestBody LoginRequest request){
        AuthResponse response=teacherService.login(request);

        return ResponseEntity.ok(response);


    }


}

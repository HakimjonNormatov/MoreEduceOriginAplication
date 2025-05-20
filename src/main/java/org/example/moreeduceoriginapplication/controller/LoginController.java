package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.LoginDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public HttpEntity<?> create(@RequestBody LoginDto loginDto) {
        Result result = loginService.CreateLogin(loginDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

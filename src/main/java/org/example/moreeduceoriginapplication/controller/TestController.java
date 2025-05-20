package org.example.moreeduceoriginapplication.controller;

import org.example.moreeduceoriginapplication.dto.TestDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Test;
import org.example.moreeduceoriginapplication.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/{id}")
    public Test getById(Long id){
        return testService.getById(id);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody TestDto testDto){
        Result student = testService.create(testDto);
        return new ResponseEntity<>(student , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<?>update(@PathVariable Long id ,@RequestBody TestDto testDto){
        Result result = testService.update(id , testDto);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?>delete(@PathVariable Long id) {
        Result result = testService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

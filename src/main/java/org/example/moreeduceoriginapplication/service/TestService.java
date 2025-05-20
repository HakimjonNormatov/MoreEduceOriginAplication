package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.dto.TestDto;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Test;
import org.example.moreeduceoriginapplication.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TestRepo testRepo;

    public List<Test> getAll(){
        return testRepo.findAll();
    }

    public Test getById(Long id){
        return testRepo.findById(id).get();
    }

    public Result create(TestDto testDto){
        Test test = new Test();
        test.setName(testDto.getName());
        test.setStatus(testDto.getStatus());
        test.setDescription(testDto.getDescription());
        testRepo.save(test);
        return  new Result( true , "Success");
    }

    public Result update(Long id , TestDto testDto){
        Optional<Test> byId = testRepo.findById(id);
        Test test = byId.get();
        test.setName(testDto.getName());
        test.setStatus(testDto.getStatus());
        test.setDescription(testDto.getDescription());
        testRepo.save(test);
        return  new Result( true , "Success");
    }

    public Result delete(Long id){
        testRepo.deleteById(id);
        return  new Result( true , "Success");
    }

}

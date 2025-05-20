package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.dto.LoginDto;
import org.example.moreeduceoriginapplication.model.Login;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepo loginRepo;

    public Result CreateLogin(LoginDto loginDto){
        boolean exists = loginRepo.existsByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (exists){
            Login login = new Login();
            login.setEmail(loginDto.getEmail());
            login.setPassword(loginDto.getPassword());
            loginRepo.save(login);
            return new Result(true , "Qaytganingizdan hursandmiz ! ");
        }
        return new Result(false, "Topilmadi");
    }

}

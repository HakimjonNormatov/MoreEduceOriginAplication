package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.configuration.EmailService;
import org.example.moreeduceoriginapplication.dto.TeacherDto;
import org.example.moreeduceoriginapplication.model.Address;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Teacher;
import org.example.moreeduceoriginapplication.model.VerifieToken;
import org.example.moreeduceoriginapplication.repository.AddressRepo;
import org.example.moreeduceoriginapplication.repository.TeacherRepo;
import org.example.moreeduceoriginapplication.repository.VerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private VerifyTokenRepository verifyTokenRepository;

    public TeacherService(EmailService emailService) {
        this.emailService = emailService;
    }

    public List<Teacher>getAllTeachers(){
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(Long id){
        return teacherRepo.findById(id).get();
    }

    public Result addTeacher(TeacherDto teacherDto){
        Optional<Teacher> byEmailAndUsername = teacherRepo.findByEmailAndUsernameAndPhonenumber(teacherDto.getEmail(), teacherDto.getUsername() , teacherDto.getPhonenumber() );
        if(byEmailAndUsername.isPresent()){
            return new Result(false , "This Accaunt already exists");
        }
        Teacher teacher = new Teacher();
        teacher.setOriginal_full_name(teacherDto.getOriginal_full_name());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhonenumber(teacherDto.getPhonenumber());
        teacher.setUsername(teacherDto.getUsername());
        teacher.setAge(teacherDto.getAge());
        Address address = new Address();
        address.setCity(teacherDto.getCity());
        address.setRegion(teacherDto.getRegion());
        Address save = addressRepo.save(address);
        teacher.setAddress_Id(save);
        teacher.setPassword(teacherDto.getPassword());
        teacher.setRepassword(teacherDto.getRepassword());
        teacher.setFanlar(teacherDto.getFanlar());
        teacher.setEnable(false);
        teacherRepo.save(teacher);
        UUID uuid = UUID.randomUUID();
        String s = "http://localhost:8080/auth/verify/";
                s+=String.valueOf(uuid);
        VerifieToken token=new VerifieToken();
        token.setToken(s);
        token.setEmail(teacher.getEmail());
        token.setDate(LocalDateTime.now());
        verifyTokenRepository.save(token);
        emailService.sendEmail(teacher.getEmail(), teacher.getUsername(), s);
        return new Result(true , "Created");
    }

    public Result updateTeacher(Long id , TeacherDto teacherDto){
        Optional<Teacher> byId = teacherRepo.findById(id);
        if (byId.isPresent()) {
            Teacher teacher = byId.get();
            teacher.setOriginal_full_name(teacherDto.getOriginal_full_name());
            teacher.setEmail(teacherDto.getEmail());
            teacher.setPhonenumber(teacherDto.getPhonenumber());
            teacher.setUsername(teacherDto.getUsername());
            teacher.setAge(teacherDto.getAge());
            Optional<Address> byId1 = addressRepo.findById(teacherDto.getAddress_Id());
            Address address = byId1.get();
            address.setCity(teacherDto.getCity());
            address.setRegion(teacherDto.getRegion());
            Address save = addressRepo.save(address);
            teacher.setAddress_Id(save);
            teacher.setPassword(teacherDto.getPassword());
            teacher.setRepassword(teacherDto.getRepassword());
            teacher.setFanlar(teacherDto.getFanlar());
            teacherRepo.save(teacher);
            return new Result(true , "Updated");
        }
            return new Result(false , "Not found");
    }

    public Result verify(String token){
        Optional<VerifieToken> byToken = verifyTokenRepository.findByToken(token);
        if(byToken.isPresent()){
            VerifieToken token1 = byToken.get();
            LocalDateTime date = token1.getDate();
            LocalDateTime now = LocalDateTime.now();
            if(date.isBefore(now)){
                return new Result(false , "Expired");
            }
            {
                Optional<VerifieToken> byToken1 = verifyTokenRepository.findByToken(token);

                return new Result(true, "OK");
            }        }
        return new Result(false , "Expired");
    }

    public Result deleteTeacher(Long id){
        teacherRepo.deleteById(id);
        return new Result(true , "Deleted");
    }

}

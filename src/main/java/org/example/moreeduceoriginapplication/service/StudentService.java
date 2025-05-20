package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.configuration.EmailService;
import org.example.moreeduceoriginapplication.dto.StudentDto;
import org.example.moreeduceoriginapplication.model.Address;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Students;
import org.example.moreeduceoriginapplication.model.VerifieToken;
import org.example.moreeduceoriginapplication.repository.AddressRepo;
import org.example.moreeduceoriginapplication.repository.StudentRepo;
import org.example.moreeduceoriginapplication.repository.VerifyTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerifyTokenRepository verifyTokenRepository;



    public List<Students>getAllStudents(){
        return studentRepo.findAll();
    }

    public Students getById(Long id){
        return studentRepo.findById(id).get();
    }

    public Result createStudent(StudentDto studentDto){

        boolean exists = studentRepo.existsByEmailAndUsernameAndPhonenumber(studentDto.getEmail(), studentDto.getUsername(),studentDto.getPhonenumber() );
        if (exists){
            return new Result(false , "This email or username or phone number is already existed");
        }
        Students students = new Students();
        students.setEmail(studentDto.getEmail());
        students.setUsername(studentDto.getUsername());
        students.setAge(studentDto.getAge());
        students.setPhonenumber(studentDto.getPhonenumber());
        students.setPassword(studentDto.getPassword());
        students.setRepassword(studentDto.getRepassword());
        Address address = new Address();
        address.setCity(studentDto.getCity());
        address.setRegion(studentDto.getRegion());
        Address save = addressRepo.save(address);
        students.setAddress_id(save);
        studentRepo.save(students);
        return new Result(true , "Created");
    }

    public Result updateStudent(Long id , StudentDto studentDto){
        Optional<Students> byId = studentRepo.findById(id);
        if (byId.isPresent()){
            Students students = new Students();
            students.setEmail(studentDto.getEmail());
            students.setUsername(studentDto.getUsername());
            students.setAge(studentDto.getAge());
            students.setPhonenumber(studentDto.getPhonenumber());
            students.setPassword(studentDto.getPassword());
            students.setRepassword(studentDto.getRepassword());
            Optional<Address> byId1 = addressRepo.findById(studentDto.getAddress_id());
            Address address = byId1.get();
            address.setCity(studentDto.getCity());
            address.setRegion(studentDto.getRegion());
            Address save = addressRepo.save(address);
            students.setAddress_id(save);
            studentRepo.save(students);
            UUID uuid = UUID.randomUUID();
            String s = "http://localhost:8080/auth/verify/";
            s+=String.valueOf(uuid);
            VerifieToken token=new VerifieToken();
            token.setToken(s);
            token.setEmail(students.getEmail());
            token.setDate(LocalDateTime.now());
            verifyTokenRepository.save(token);
            emailService.sendEmail(students.getEmail(), students.getUsername(), "");
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
            if(date.isAfter(now)){
                return new Result(false , "Expired");
            }
            return new Result(true , "OK");
        }
        return new Result(false , "Expired");
    }


    public Result deleteStudent(Long id){
        studentRepo.deleteById(id);
        return new Result(true , "Deleted");
    }

}

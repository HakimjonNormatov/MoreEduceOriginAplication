package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.configuration.EmailService;
import org.example.moreeduceoriginapplication.dto.AuthResponse;
import org.example.moreeduceoriginapplication.dto.LoginRequest;
import org.example.moreeduceoriginapplication.dto.TeacherDto;
//import org.example.moreeduceoriginapplication.model.Address;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.model.Teacher;
import org.example.moreeduceoriginapplication.model.VerifieToken;
//import org.example.moreeduceoriginapplication.repository.AddressRepo;
import org.example.moreeduceoriginapplication.repository.TeacherRepo;
import org.example.moreeduceoriginapplication.repository.VerifyTokenRepository;
import org.example.moreeduceoriginapplication.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final EmailService emailService;
    @Autowired
    private VerifyTokenRepository verifyTokenRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    public TeacherService(EmailService emailService, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
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
        teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        teacher.setRepassword(teacherDto.getRepassword());
        teacher.setFanlar(teacherDto.getFanlar());
        teacher.setCity(teacherDto.getCity());
        teacher.setRegion(teacherDto.getRegion());
        teacher.setEnable(false);
        teacherRepo.save(teacher);
        UUID uuid = UUID.randomUUID();
        VerifieToken token=new VerifieToken();
        token.setToken(uuid.toString());
        token.setEmail(teacher.getEmail());
        token.setDate(LocalDateTime.now());
        verifyTokenRepository.save(token);
        String s = "http://localhost:8080/teacher/verify?token=";
                s+=String.valueOf(uuid);

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
            teacher.setCity(teacherDto.getCity());
            teacher.setRegion(teacherDto.getRegion());
            teacher.setPassword(teacherDto.getPassword());
            teacher.setRepassword(teacherDto.getRepassword());
            teacher.setFanlar(teacherDto.getFanlar());
            teacherRepo.save(teacher);
            return new Result(true , "Updated");
        }
            return new Result(false , "Not found");
    }

    public AuthResponse verify(String token){
        Optional<VerifieToken> byToken = verifyTokenRepository.findByToken(token);
        if(byToken.isPresent()){
            VerifieToken token1 = byToken.get();
            LocalDateTime date = token1.getDate();
            LocalDateTime now = LocalDateTime.now();
            if(date.isAfter(now)){
                System.out.println("expired");
                return null;
            }
            else {
                System.out.println("success");
                Optional<VerifieToken> byToken1 = verifyTokenRepository.findByToken(token);
                VerifieToken verifie = byToken1.get();
                String email = verifie.getEmail();
                Optional<Teacher> byEmail = teacherRepo.findByEmail(email);
                if (byEmail.isPresent()) {
                    Teacher teacher = byEmail.get();
                    teacher.setEnable(true);
                    teacherRepo.save(teacher);
                    String accessToken=jwtProvider.createToken(teacher.getEmail());
                    return AuthResponse.builder()
                            .token(accessToken)
                            .build();
                }



            }
        }
        return null;
    }

    public Result deleteTeacher(Long id){
        teacherRepo.deleteById(id);
        return new Result(true , "Deleted");
    }

    public AuthResponse login(LoginRequest request) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtProvider.createToken(request.getEmail());
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}

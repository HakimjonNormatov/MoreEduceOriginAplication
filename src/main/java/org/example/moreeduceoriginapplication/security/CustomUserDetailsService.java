package org.example.moreeduceoriginapplication.security;

import org.example.moreeduceoriginapplication.model.Teacher;
import org.example.moreeduceoriginapplication.repository.TeacherRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final TeacherRepo teacherRepo;

    public CustomUserDetailsService(TeacherRepo teacherRepo, TeacherRepo teacherRepo1) {
        this.teacherRepo = teacherRepo1;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Teacher> byEmail = teacherRepo.findByEmail(email);
        if (byEmail.isPresent()) {
            Teacher teacher = byEmail.get();

            return new User(teacher.getEmail(),teacher.getPassword(), Collections.singleton(new SimpleGrantedAuthority("TEACHER")));
        }
        return null;
    }
}

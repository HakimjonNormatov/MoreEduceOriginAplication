package org.example.moreeduceoriginapplication.repository;

import jakarta.validation.constraints.Email;
import org.example.moreeduceoriginapplication.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher , Long> {

     Optional<Teacher> findByEmailAndUsernameAndPhonenumber(@Email String email, String username, String phonenumber);

}

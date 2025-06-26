package org.example.moreeduceoriginapplication.repository;

import jakarta.validation.constraints.Email;
import org.example.moreeduceoriginapplication.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Students , Long> {
    boolean existsByEmailAndUsernameAndPhonenumber(@Email String email, String username, String phonenumber);
    Optional<Students> existsByEmail(String email);
}

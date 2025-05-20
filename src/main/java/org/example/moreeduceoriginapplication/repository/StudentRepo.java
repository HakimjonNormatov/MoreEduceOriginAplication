package org.example.moreeduceoriginapplication.repository;

import jakarta.validation.constraints.Email;
import org.example.moreeduceoriginapplication.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Students , Long> {
    boolean existsByEmailAndUsernameAndPhonenumber(@Email String email, String username, String phonenumber);
}

package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login , Long> {
    boolean existsByEmailAndPassword(String email, String password);
}

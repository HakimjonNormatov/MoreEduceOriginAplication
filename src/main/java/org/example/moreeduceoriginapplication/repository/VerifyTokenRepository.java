package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.VerifieToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerifieToken,Long> {
    Optional<VerifieToken> findByToken(String token);
}

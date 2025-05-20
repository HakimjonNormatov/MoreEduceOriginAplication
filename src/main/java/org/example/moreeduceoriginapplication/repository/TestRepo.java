package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test , Long> {
}

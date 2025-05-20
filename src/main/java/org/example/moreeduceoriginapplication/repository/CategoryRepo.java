package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Long> {
}

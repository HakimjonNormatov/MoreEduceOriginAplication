package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lesson , Long> {
    boolean existsByName(String name);
}

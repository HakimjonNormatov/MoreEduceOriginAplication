package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}

package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepo extends JpaRepository<Video, Long> {
}

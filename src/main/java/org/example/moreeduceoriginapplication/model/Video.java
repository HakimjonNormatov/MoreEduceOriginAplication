package org.example.moreeduceoriginapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String path;
    @CreatedDate
    private LocalDateTime created_date = LocalDateTime.now();
}

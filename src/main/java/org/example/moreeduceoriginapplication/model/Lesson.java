package org.example.moreeduceoriginapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.moreeduceoriginapplication.model.temp.LEVEL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String duration;
    @ManyToOne
    private Teacher teacher_id;
    @ManyToOne
    private Students all_student;
    @Enumerated(EnumType.STRING)
    private LEVEL level_id;
    @ManyToOne
    private Test test_id;
    @Column(nullable = false , unique = true)
    private String count;
}

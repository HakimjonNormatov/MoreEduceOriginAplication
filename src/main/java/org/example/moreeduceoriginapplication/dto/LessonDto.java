package org.example.moreeduceoriginapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.moreeduceoriginapplication.model.Students;
import org.example.moreeduceoriginapplication.model.Teacher;
import org.example.moreeduceoriginapplication.model.Test;
import org.example.moreeduceoriginapplication.model.temp.LEVEL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private String name;
    private String description;
    private String duration;
    private Teacher teacher_id;
    private Students all_student;
    private LEVEL level_id;
    private Test test_id;
    private String count;
}

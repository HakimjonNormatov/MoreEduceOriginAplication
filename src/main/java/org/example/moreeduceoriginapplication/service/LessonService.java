package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.dto.LessonDto;
import org.example.moreeduceoriginapplication.model.Lesson;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.repository.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    LessonRepo lessonRepo;

    public List<Lesson> getAll() {
        return lessonRepo.findAll();
    }

    public Lesson getById(Long id) {
        return lessonRepo.findById(id).get();
    }

    public Result create(LessonDto lessonDto) {
        boolean exists = lessonRepo.existsByName(lessonDto.getName());
        if (exists) {
            return new Result(false, "This name already exists");
        }
        Lesson lesson = new Lesson();
        lesson.setName(lessonDto.getName());
        lesson.setDescription(lessonDto.getDescription());
        lesson.setCount(lessonDto.getCount());
        lesson.setDuration(lessonDto.getDuration());
        lesson.setLevel_id(lessonDto.getLevel_id());
        lesson.setTeacher_id(lessonDto.getTeacher_id());
        lesson.setTest_id(lessonDto.getTest_id());
        lessonRepo.save(lesson);
        return new Result(true, "Success");
    }

    public Result update(Long id , LessonDto lessonDto) {
        Optional<Lesson> byId = lessonRepo.findById(id);
        Lesson lesson = byId.get();
        lesson.setName(lessonDto.getName());
        lesson.setDescription(lessonDto.getDescription());
        lesson.setCount(lessonDto.getCount());
        lesson.setDuration(lessonDto.getDuration());
        lesson.setLevel_id(lessonDto.getLevel_id());
        lesson.setTeacher_id(lessonDto.getTeacher_id());
        lesson.setTest_id(lessonDto.getTest_id());
        lessonRepo.save(lesson);
        return new Result(true, "Success");
    }

    public Result delete(Long id) {
        lessonRepo.deleteById(id);
        return new Result(true, "Success");
    }
}

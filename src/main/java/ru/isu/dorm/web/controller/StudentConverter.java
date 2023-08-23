package ru.isu.dorm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.repository.StudentRepository;

public class StudentConverter implements Converter<Long, Student>{
    
    @Autowired
    private StudentRepository studentService;
    
    @Override
    public Student convert(Long id){
        Student student = studentService.findOne(id);
        return student;
    }
}

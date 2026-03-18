package org.spring.tdspringboot.controller;

import org.spring.tdspringboot.entity.Student;
import org.spring.tdspringboot.service.StudentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping("/students")
    public List<Student> createStudents(@RequestBody List<Student> students) {
        return studentsService.addStudents(students);
    }

    @GetMapping("/students")
    public  List<Student> findAllStudents() {
        return studentsService.findAllStudents();
    }
}

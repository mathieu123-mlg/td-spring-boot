package org.spring.tdspringboot.controller;

import jdk.jfr.ContentType;
import org.spring.tdspringboot.entity.Student;
import org.spring.tdspringboot.service.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> createStudents(@RequestBody List<Student> students) {
        if (students == null || students.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentsService.addStudents(students));
    }

    @GetMapping("/students")
    public ResponseEntity<String> findAllStudents(
            @RequestHeader(value = "Accept", defaultValue = MediaType.TEXT_PLAIN_VALUE) String acceptHeader
    ) {
        if (acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE)) {
            List<Student> students = studentsService.findAllStudents();
            if (students.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(students.toString());
        }
        return ResponseEntity.badRequest().body("Format non supporter");
    }
}

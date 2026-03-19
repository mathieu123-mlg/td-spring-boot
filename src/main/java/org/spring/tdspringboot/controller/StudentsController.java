package org.spring.tdspringboot.controller;

import org.spring.tdspringboot.entity.Student;
import org.spring.tdspringboot.service.StudentsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> students) {
        if (students == null || students.isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .header("Content-Type", "application/json")
                    .body("{\"message\":\"List of student is required\"}");
        }
        try {
            return ResponseEntity
                    .status(201)
                    .header("Content-Type", "application/json")
                    .body(studentsService.addStudents(students));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(500)
                    .header("Content-Type", "application/json")
                    .body("{\"message\":\"%s\"}".formatted(e.getMessage()));
        }
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

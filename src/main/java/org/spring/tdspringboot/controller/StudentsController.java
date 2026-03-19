package org.spring.tdspringboot.controller;

import org.spring.tdspringboot.entity.Student;
import org.spring.tdspringboot.service.StudentsService;
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
    public ResponseEntity<?> findAllStudents(
            @RequestHeader(value = "Accept", defaultValue = MediaType.TEXT_PLAIN_VALUE) String acceptHeader
    ) {
        if (acceptHeader == null) {
            return ResponseEntity
                    .status(400)
                    .body("{\"message\":\"Accept header is required\"}");
        }
        try {
            if (acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE) ||
                acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {

                List<Student> students = studentsService.findAllStudents();
                if (students.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
                return ResponseEntity
                        .status(200)
                        .header("Content-Type", acceptHeader)
                        .body(
                                acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE)
                                        ? students.toString()
                                        : students
                        );
            } else {
                return ResponseEntity
                        .status(501)
                        .body("{\"message\":\"Format not supported\"}");
            }
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(500)
                    .header("Content-Type", acceptHeader)
                    .body("{\"message\":\"%s\"}".formatted(e.getMessage()));
        }
    }
}

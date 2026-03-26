package org.spring.tdspringboot.controller;

import org.spring.tdspringboot.entity.Student;
import org.spring.tdspringboot.service.StudentsService;
import org.spring.tdspringboot.util.ResponseBuilder;
import org.spring.tdspringboot.validator.StudentValidator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {
    private final StudentsService studentsService;
    private final StudentValidator studentValidator;
    private final ResponseBuilder responseBuilder;

    public StudentsController(StudentsService studentsService, StudentValidator studentValidator, ResponseBuilder responseBuilder) {
        this.studentsService = studentsService;
        this.studentValidator = studentValidator;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody List<Student> newStudents) {
        try {
            studentValidator.validateCreateRequest(newStudents);
            List<Student> students = studentsService.addStudents(newStudents);
            return responseBuilder.created(students);
        } catch (IllegalArgumentException e) {
            return responseBuilder.badRequest(e.getMessage());
        } catch (RuntimeException e) {
            return responseBuilder.internalError(e.getMessage());
        }
    }

    @GetMapping("/students")
    public ResponseEntity<Object> findAllStudents(
            @RequestHeader(value = "Accept", defaultValue = MediaType.TEXT_PLAIN_VALUE) String acceptHeader) {

        try {
            MediaType mediaType = parseAcceptHeader(acceptHeader);
            List<Student> students = studentsService.findAllStudents();
            if (students.isEmpty()) {
                return responseBuilder.noContent();
            }
            Object responseBody = mediaType.equals(MediaType.TEXT_PLAIN)
                    ? students.toString()
                    : students;

            return ResponseEntity
                    .status(200)
                    .contentType(mediaType)
                    .body(responseBody);
        } catch (IllegalArgumentException e) {
            return responseBuilder.notSupported();
        } catch (RuntimeException e) {
            return responseBuilder.internalError(e.getMessage());
        }
    }

    private MediaType parseAcceptHeader(String acceptHeader) {
        if (MediaType.TEXT_PLAIN_VALUE.equals(acceptHeader)) {
            return MediaType.TEXT_PLAIN;
        }
        if (MediaType.APPLICATION_JSON_VALUE.equals(acceptHeader)) {
            return MediaType.APPLICATION_JSON;
        }
        throw new IllegalArgumentException("Format not supported");
    }
}

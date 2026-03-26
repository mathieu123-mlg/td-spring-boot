package org.spring.tdspringboot.validator;

import org.spring.tdspringboot.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {

    public void validateCreateRequest(List<Student> newStudents) {
        if (newStudents == null || newStudents.isEmpty()) {
            throw new IllegalArgumentException("List of students is required");
        }

        for (Student student : newStudents) {
            if (student.getReference() == null || student.getReference().trim().isEmpty()) {
                throw new IllegalArgumentException("Reference cannot be null or empty");
            }
            if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
                throw new IllegalArgumentException("First name cannot be null or empty");
            }
            if (student.getAge() <= 0) {
                throw new IllegalArgumentException("Age must be positive");
            }
        }
    }
}
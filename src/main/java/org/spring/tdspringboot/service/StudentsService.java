package org.spring.tdspringboot.service;

import org.spring.tdspringboot.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private final List<Student> students;

    public StudentsService(List<Student> students) {
        this.students = students;
    }

    public List<Student> addStudents(List<Student> studentList) {
        students.addAll(studentList);
        return studentList;
    }

    public List<Student> findAllStudents() {
        return students;
    }
}

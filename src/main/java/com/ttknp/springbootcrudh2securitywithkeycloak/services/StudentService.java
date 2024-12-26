package com.ttknp.springbootcrudh2securitywithkeycloak.services;

import com.ttknp.springbootcrudh2securitywithkeycloak.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(long id);
}

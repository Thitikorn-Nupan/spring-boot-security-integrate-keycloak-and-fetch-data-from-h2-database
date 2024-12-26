package com.ttknp.springbootcrudh2securitywithkeycloak.controller;

import com.ttknp.springbootcrudh2securitywithkeycloak.entities.Student;
import com.ttknp.springbootcrudh2securitywithkeycloak.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping(value = "/")
    private ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity
                .status(200)
                .body(studentService.getAllStudents());
    }

    @GetMapping
    private ResponseEntity<Student> getStudent(@RequestParam long id) {
        return ResponseEntity
                .status(200)
                .body(studentService.getStudentById(id));
    }

    @GetMapping(value = "/whoami")
    private ResponseEntity<Principal> getPrincipal(Principal principal) {
        return ResponseEntity
                .status(200)
                .body(principal);
    }
}

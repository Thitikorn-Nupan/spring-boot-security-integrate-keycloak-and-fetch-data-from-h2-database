package com.ttknp.springbootcrudh2securitywithkeycloak.dto;

import com.ttknp.springbootcrudh2securitywithkeycloak.entities.Student;
import com.ttknp.springbootcrudh2securitywithkeycloak.repositories.StudentRepo;
import com.ttknp.springbootcrudh2securitywithkeycloak.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentDTO implements StudentService  {

    private StudentRepo studentRepo;

    @Autowired
    public StudentDTO(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepo.findAll();
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepo.findById(id).orElse(null);
    }
}

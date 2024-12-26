package com.ttknp.springbootcrudh2securitywithkeycloak.repositories;

import com.ttknp.springbootcrudh2securitywithkeycloak.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo  extends CrudRepository<Student, Long> { }

package com.school.system.service;

import com.school.system.model.Student;
import com.school.system.repository.RoleRepository;
import com.school.system.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("studentService")

public class StudentService {
    private StudentsRepository studentRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentService(StudentsRepository studentsRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentsRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Student findBystudentid(int student_id) {
        return studentRepository.findBystudentid(student_id);
    }
}
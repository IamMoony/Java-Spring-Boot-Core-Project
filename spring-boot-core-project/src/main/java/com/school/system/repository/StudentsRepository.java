package com.school.system.repository;

import com.school.system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentsRepository")
public interface StudentsRepository extends JpaRepository<Student, Long> {
    Student findBystudentid(int student_id);
}

package com.sharad.studentapp.repository;

import com.sharad.studentapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Find students by course
    List<Student> findByCourse(String course);

    // Find students with cgpa greater than given value
    List<Student> findByCgpaGreaterThan(double cgpa);
}

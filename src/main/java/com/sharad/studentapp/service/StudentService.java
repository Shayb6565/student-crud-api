package com.sharad.studentapp.service;

import com.sharad.studentapp.model.Student;
import com.sharad.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new RuntimeException("Student not found with id: " + id);
    }

    // Add new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update student
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);
        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setCourse(updatedStudent.getCourse());
        existing.setCgpa(updatedStudent.getCgpa());
        return studentRepository.save(existing);
    }

    // Delete student
    public String deleteStudent(Long id) {
        getStudentById(id); // will throw error if not found
        studentRepository.deleteById(id);
        return "Student with id " + id + " deleted successfully";
    }

    // Get students by course
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    // Get top students (cgpa > 8)
    public List<Student> getTopStudents() {
        return studentRepository.findByCgpaGreaterThan(8.0);
    }
}

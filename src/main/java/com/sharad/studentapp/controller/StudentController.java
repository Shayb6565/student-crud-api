package com.sharad.studentapp.controller;

import com.sharad.studentapp.model.Student;
import com.sharad.studentapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET all students
    // URL: http://localhost:8080/api/students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // GET student by ID
    // URL: http://localhost:8080/api/students/1
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // POST - Add new student
    // URL: http://localhost:8080/api/students
    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student saved = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT - Update student
    // URL: http://localhost:8080/api/students/1
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                  @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    // DELETE - Remove student
    // URL: http://localhost:8080/api/students/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    // GET students by course
    // URL: http://localhost:8080/api/students/course/CSE
    @GetMapping("/course/{course}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable String course) {
        return ResponseEntity.ok(studentService.getStudentsByCourse(course));
    }

    // GET top students (cgpa > 8)
    // URL: http://localhost:8080/api/students/top
    @GetMapping("/top")
    public ResponseEntity<List<Student>> getTopStudents() {
        return ResponseEntity.ok(studentService.getTopStudents());
    }
}

package com.example.springbootmysqldocker.controller;

import com.example.springbootmysqldocker.entity.Student;
import com.example.springbootmysqldocker.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/findAll")
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @PostMapping("/insert")
    public Student insert(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    // Get Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentRepo.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(student.getName());
                    existingStudent.setAge(student.getAge());
                    return ResponseEntity.ok(studentRepo.save(existingStudent));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

package com.example.springbootmysqldocker.service;

import com.example.springbootmysqldocker.entity.Student;
import com.example.springbootmysqldocker.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Get a student by ID
    public Optional<Student> getStudentById(Integer id) {
        return studentRepo.findById(id);
    }

    // Add a new student
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    // Update an existing student
    public Optional<Student> updateStudent(Integer id, Student updatedStudent) {
        return studentRepo.findById(id).map(existingStudent -> {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setAge(updatedStudent.getAge());
            return studentRepo.save(existingStudent);
        });
    }

    // Delete a student by ID
    public boolean deleteStudent(Integer id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

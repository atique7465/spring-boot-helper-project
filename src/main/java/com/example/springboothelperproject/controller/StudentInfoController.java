package com.example.springboothelperproject.controller;

import com.example.springboothelperproject.dto.Student;
import com.example.springboothelperproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 2:25 PM
 */

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentInfoController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student saveStudent(@RequestBody @Validated Student request) {
        return studentService.save(request);
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getList();
    }

    @GetMapping(value = "/{studentId}")
    public Student getStudent(@PathVariable String studentId) {
        return studentService.get(studentId);
    }

    @PutMapping(value = "/{studentId}")
    public Student updateStudent(@PathVariable String studentId, @RequestBody @Validated Student request) {
        return studentService.update(studentId, request);
    }

    @DeleteMapping(value = "/{studentId}")
    public void deleteStudent(@PathVariable String studentId) {
        studentService.delete(studentId);
    }
}

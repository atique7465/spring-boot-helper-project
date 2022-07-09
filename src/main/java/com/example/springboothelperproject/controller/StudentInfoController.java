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

    @GetMapping(value = "/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PutMapping(value = "/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody @Validated Student request) {
        return studentService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }
}

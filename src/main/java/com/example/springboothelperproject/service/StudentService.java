package com.example.springboothelperproject.service;

import com.example.springboothelperproject.dto.Student;

import java.util.List;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 2:25 PM
 */

public interface StudentService {

    /**
     * @param request student DTO
     * @return saved student DTO
     */
    Student save(Student request);

    /**
     * @param studentId unique for every student, given by the organization
     * @return student DTO
     */
    Student get(String studentId);

    /**
     * @return list of all student DTO
     */
    List<Student> getList();

    /**
     * @param studentId of the student to update info
     * @param request   info to update
     * @return updated student DTO
     */
    Student update(String studentId, Student request);

    /**
     * @param studentId of the student to delete
     */
    void delete(String studentId);
}

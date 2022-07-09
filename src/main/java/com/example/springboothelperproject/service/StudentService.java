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
     * @param id of the student
     * @return student DTO
     */
    Student get(Long id);

    /**
     * @return list of all student DTO
     */
    List<Student> getList();

    /**
     * @param id      of the student to update info
     * @param request info to update
     * @return updated student DTO
     */
    Student update(Long id, Student request);

    /**
     * @param id of the student to delete
     */
    void delete(Long id);
}

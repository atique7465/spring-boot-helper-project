package com.example.springboothelperproject.service;

import com.example.springboothelperproject.dto.Book;
import com.example.springboothelperproject.dto.Student;
import com.example.springboothelperproject.entity.BookEntity;
import com.example.springboothelperproject.entity.StudentEntity;

import java.util.List;

/**
 * @author atiQue
 * @since 17'Jul 2022 at 12:08 AM
 */

public interface StudentHelperService {

    /**
     * Student DTO to Entity Mapper using StudentEntity builder
     */
    StudentEntity getStudentEntityFromStudent(Student request);

    /**
     * Book DTO to Entity Mapper using BookEntity builder
     */
    List<BookEntity> getBookEntitiesFromBooks(StudentEntity studentEntity, List<Book> books);

    /**
     * Student Entity to DTO Mapper using Student builder
     */
    Student getStudentFromEntity(StudentEntity entity);
}

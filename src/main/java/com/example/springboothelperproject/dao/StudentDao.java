package com.example.springboothelperproject.dao;

import com.example.springboothelperproject.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 5:47 PM
 */

public interface StudentDao {

    /**
     * @param entity for the student
     * @return saved student entity
     */
    StudentEntity save(StudentEntity entity);

    /**
     * @param studentId of the entity
     * @return student entity
     */
    Optional<StudentEntity> getByStudentId(String studentId);

    /**
     * @return list of all student entities
     */
    List<StudentEntity> getList();

    /**
     * @param id of the entity to be deleted
     */
    void deleteById(Long id);
}

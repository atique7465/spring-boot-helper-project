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
     * @param id of the student entity
     * @return student entity
     */
    Optional<StudentEntity> get(Long id);

    /**
     * @return list of all student entities
     */
    List<StudentEntity> getList();

    /**
     * @param id of the student entity to be deleted
     */
    void delete(Long id);
}

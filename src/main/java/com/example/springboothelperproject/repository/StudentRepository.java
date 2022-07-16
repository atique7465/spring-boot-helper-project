package com.example.springboothelperproject.repository;

import com.example.springboothelperproject.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 5:45 PM
 */

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    //This is a Derived Query Method, ref: https://www.baeldung.com/spring-data-derived-queries

    Optional<StudentEntity> findByStudentId(String StudentEntity);
}

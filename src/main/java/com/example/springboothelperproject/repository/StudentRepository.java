package com.example.springboothelperproject.repository;

import com.example.springboothelperproject.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 5:45 PM
 */

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}

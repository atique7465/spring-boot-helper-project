package com.example.springboothelperproject.entity;

import com.example.springboothelperproject.enums.Department;
import com.example.springboothelperproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 5:36 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "STUDENT")
public class StudentEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTMENT", nullable = false)
    private Department department;
}

package com.example.springboothelperproject.dto;

import com.example.springboothelperproject.enums.Department;
import com.example.springboothelperproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 2:25 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @NotNull(message = "Student ID can't ne null.")
    private Long id;

    @NotNull(message = "Student name can't be null.")
    @NotEmpty(message = "Student name can't be empty.")
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private Department department;
}

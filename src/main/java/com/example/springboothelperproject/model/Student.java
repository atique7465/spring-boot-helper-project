package com.example.springboothelperproject.model;

import com.example.springboothelperproject.enums.Department;
import com.example.springboothelperproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 2:25 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Gender gender;

    @NonNull
    private Department department;
}

package com.example.springboothelperproject.entity;

import com.example.springboothelperproject.enums.Department;
import com.example.springboothelperproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 5:36 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "STUDENT_V2")
public class StudentEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STUDENT_ID", nullable = false)
    private String studentId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTMENT", nullable = false)
    private Department department;

    @Column(name = "CREATED", nullable = false)
    private Date created;

    @Column(name = "UPDATED")
    private Date updated;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @PrePersist
    public void onCreate() {
        this.created = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = new Date();
    }
}

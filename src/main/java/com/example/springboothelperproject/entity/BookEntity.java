package com.example.springboothelperproject.entity;

import com.example.springboothelperproject.enums.BookType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 10:16 PM
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "BOOK_NAME", nullable = false)
    private String bookName;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_TYPE", nullable = false)
    private BookType bookType;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "STUDENT_TABLE_ID", nullable = false)
    @JsonBackReference
    private StudentEntity studentEntity;

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



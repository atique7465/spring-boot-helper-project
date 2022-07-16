package com.example.springboothelperproject.dto;

import com.example.springboothelperproject.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 10:16 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String isbn;

    private String bookName;

    private BookType bookType;

    private String author;
}

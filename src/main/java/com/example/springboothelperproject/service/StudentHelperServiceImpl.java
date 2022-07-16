package com.example.springboothelperproject.service;

import com.example.springboothelperproject.dto.Book;
import com.example.springboothelperproject.dto.Student;
import com.example.springboothelperproject.entity.BookEntity;
import com.example.springboothelperproject.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atiQue
 * @since 17'Jul 2022 at 12:10 AM
 */

@Service
public class StudentHelperServiceImpl implements StudentHelperService {

    @Override
    public StudentEntity getStudentEntityFromStudent(Student request) {

        StudentEntity studentEntity = StudentEntity.builder()
                .studentId(request.getStudentId())
                .name(request.getName())
                .gender(request.getGender())
                .department(request.getDepartment())
                .build();

        List<BookEntity> bookEntities = getBookEntitiesFromBooks(studentEntity, request.getBooks());

        studentEntity.setBookEntities(bookEntities);

        return studentEntity;
    }

    @Override
    public List<BookEntity> getBookEntitiesFromBooks(StudentEntity studentEntity, List<Book> books) {

        List<BookEntity> bookEntities = new ArrayList<>();

        for (Book book : books) {
            BookEntity bookEntity = getBookEntityFromBook(studentEntity, book);
            bookEntities.add(bookEntity);
        }

        return bookEntities;
    }

    private BookEntity getBookEntityFromBook(StudentEntity studentEntity, Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .bookName(book.getBookName())
                .bookType(book.getBookType())
                .author(book.getAuthor())
                .studentEntity(studentEntity)
                .build();
    }

    @Override
    public Student getStudentFromEntity(StudentEntity entity) {

        Student student = Student.builder()
                .studentId(entity.getStudentId())
                .name(entity.getName())
                .gender(entity.getGender())
                .department(entity.getDepartment())
                .build();

        List<Book> books = getBooksFromBookEntities(entity.getBookEntities());

        student.setBooks(books);

        return student;
    }

    private List<Book> getBooksFromBookEntities(List<BookEntity> bookEntities) {

        List<Book> books = new ArrayList<>();

        for (BookEntity bookEntity : bookEntities) {
            Book book = getBookFromEntity(bookEntity);
            books.add(book);
        }

        return books;
    }

    private Book getBookFromEntity(BookEntity bookEntity) {
        return Book.builder()
                .isbn(bookEntity.getIsbn())
                .bookName(bookEntity.getBookName())
                .bookType(bookEntity.getBookType())
                .author(bookEntity.getAuthor())
                .build();
    }
}

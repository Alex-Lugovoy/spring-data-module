package ru.edu.springdata.service;

import ru.edu.springdata.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    void saveBook(Book book);

    void updateBook(Long id, Book book);

    void deleteBook(Long id);

    List<Book> findAllBooksByCategory(String category);

    List<Book> findAllBooksByLanguage(String language);
}
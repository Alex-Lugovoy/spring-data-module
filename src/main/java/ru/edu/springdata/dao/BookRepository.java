package ru.edu.springdata.dao;

import ru.edu.springdata.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    List<Book> findAllByCategory(String category);
    List<Book> findAllByLanguage(String language);
    void save(Book book);
    void update(Long id, Book book);
    void delete(Long id);
}

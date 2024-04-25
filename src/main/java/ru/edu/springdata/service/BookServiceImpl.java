package ru.edu.springdata.service;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.edu.springdata.dao.BookRepository;
import ru.edu.springdata.model.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, Book book) {
        bookRepository.update(id, book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public List<Book> findAllBooksByCategory(String category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public List<Book> findAllBooksByLanguage(String language) {
        return bookRepository.findAllByLanguage(language);
    }
}

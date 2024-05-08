package ru.edu.springdata.service;

import org.springframework.stereotype.Service;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.repository.BookRepository;

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
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null) {
            bookRepository.delete(book);
        }
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
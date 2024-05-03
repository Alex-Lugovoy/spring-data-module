package ru.edu.springdata.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }

    @PostMapping
    public void saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/category/{category}")
    public List<Book> getAllBooksByCategory(@PathVariable String category){
        return bookService.findAllBooksByCategory(category);
    }

    @GetMapping("/language/{language}")
    public List<Book> getAllBooksByLanguage(@PathVariable String language){
        return bookService.findAllBooksByLanguage(language);
    }
}
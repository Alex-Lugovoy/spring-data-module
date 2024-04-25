package ru.edu.springdata.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.edu.springdata.model.Book;

import java.util.List;

@Repository
public class BookJdbcTemplateDao implements BookRepository {

    private JdbcTemplate jdbcTemplate;

    public BookJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper(Book.class));
    }

    @Override
    public List<Book> findAllByCategory(String category) {
        return jdbcTemplate.query("SELECT * FROM book WHERE category = ?", new BeanPropertyRowMapper(Book.class), category);
    }

    @Override
    public List<Book> findAllByLanguage(String language) {
        return jdbcTemplate.query("SELECT * FROM book WHERE language = ?", new BeanPropertyRowMapper(Book.class), language);
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, category, language) VALUES (?, ?, ?)", book.getName(), book.getCategory(), book.getLanguage());
    }
    @Override
    public void update(Long id, Book book) {
        jdbcTemplate.update("UPDATE book SET name = ?, category = ?, language = ? WHERE id = ?", book.getName(), book.getCategory(), book.getLanguage(), id);
    }
    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }
}

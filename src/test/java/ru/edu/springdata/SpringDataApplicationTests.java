package ru.edu.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.service.BookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringDataApplicationTests {
	private final String NAME = "nameBook";
	private final String CATEGORY = "horror";
	private final String LANGUAGE = "france";
	@Autowired
	BookService service;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void createBook() {
		Book book = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book);

		List<Book> books = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
		Book resultBook = books.get(0);
		assertNotNull(resultBook);
		assertEquals(NAME, resultBook.getName());
		assertEquals(LANGUAGE, resultBook.getLanguage());
		assertEquals(CATEGORY, resultBook.getCategory());

		deleteData();
	}

	@Test
	public void updateBook() {
		Book book = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book);

		List<Book> books = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));

		Book beforeUpdate = books.get(0);

		service.updateBook(beforeUpdate.getId(), Book.builder()
				.id(beforeUpdate.getId())
				.category("update")
				.language("update")
				.name("update")
				.build());

		Book afterUpdate = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class)).get(0);

		assertNotNull(afterUpdate);
		assertEquals("update", afterUpdate.getName());
		assertEquals("update", afterUpdate.getLanguage());
		assertEquals("update", afterUpdate.getCategory());

		deleteData();
	}

	@Test
	public void deleteBook() {
		Book book = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book);

		Book resultBook = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class)).get(0);

		service.deleteBook(resultBook.getId());

		List<Book> afterDelete = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
		assertEquals(0, afterDelete.size());

		deleteData();
	}

	@Test
	public void findAll() {
		Book book = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book);

		Book book1 = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book1);

		Book book2 = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book2);

		Book book3 = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book3);

		List<Book> books = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
		assertEquals(4, books.size());

		deleteData();
	}

	@Test
	public void findByCategory() {
		Book book = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book);

		List<Book> books = service.findAllBooksByCategory(CATEGORY);

		Book resultBook = books.get(0);
		assertNotNull(resultBook);
		assertEquals(NAME, resultBook.getName());
		assertEquals(LANGUAGE, resultBook.getLanguage());
		assertEquals(CATEGORY, resultBook.getCategory());

		deleteData();
	}

	@Test
	public void findByLanguage() {
		Book book = Book.builder()
				.category(CATEGORY)
				.language(LANGUAGE)
				.name(NAME)
				.build();
		service.saveBook(book);

		List<Book> books = service.findAllBooksByLanguage(LANGUAGE);

		Book resultBook = books.get(0);
		assertNotNull(resultBook);
		assertEquals(NAME, resultBook.getName());
		assertEquals(LANGUAGE, resultBook.getLanguage());
		assertEquals(CATEGORY, resultBook.getCategory());

		deleteData();
	}

	public void deleteData() {
		jdbcTemplate.update("delete from book");
	}

}
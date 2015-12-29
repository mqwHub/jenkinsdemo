package com.stduy;

import java.util.List;

import org.junit.Test;

import com.study.Book;
import com.study.BookParserService;

import junit.framework.Assert;

public class BookParserServiceTest {

	@Test
	public void testcase01() throws Exception {
		BookParserService service = new BookParserService();
		List<Book> books = service.getBooks(this.getClass().getClassLoader().getResourceAsStream("book.xml"));
		Assert.assertNotNull(books);
		Assert.assertEquals(2, books.size());
		Assert.assertNotNull(books.get(0));
		Assert.assertEquals("thinking in java", books.get(0).getName());
		Assert.assertEquals("85.5", books.get(0).getPrice());
	}
}

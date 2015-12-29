package com.study;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class BookParserService {

	public List<Book> getBooks(InputStream is) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		BookParser bookParser = new BookParser();
		parser.parse(is, bookParser);
		return bookParser.getBooks();
	}
}

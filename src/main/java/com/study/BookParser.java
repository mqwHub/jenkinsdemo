package com.study;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookParser extends DefaultHandler {
	
	private List<Book> books;
	
	private String tag;
	
	private Book book;

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		books = new ArrayList<Book>();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		tag = qName;
		if (qName.equalsIgnoreCase("book")) {
			book = new Book();
			book.setId(Integer.parseInt(attributes.getValue(0)));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		tag = "/" + qName;
		if (qName.equalsIgnoreCase("book")) {
			books.add(book);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String content = new String(ch, start, length);
		if (tag.equalsIgnoreCase("name") || tag.equalsIgnoreCase("price")) {
			try {
				Method method = Book.class.getMethod("set" + StringUtils.capitalize(tag), String.class);
				method.invoke(book, content);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public List<Book> getBooks() {
		return books;
	}
}

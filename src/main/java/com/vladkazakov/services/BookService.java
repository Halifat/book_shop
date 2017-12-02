package com.vladkazakov.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladkazakov.entities.Book;
import com.vladkazakov.repositories.BookRepository;

@Service("bookService")
public class BookService {
	@Autowired
	BookRepository bookRepository;
	public BookService() {
	}

	public Book getById(int id) {
		Book product = bookRepository.findOne(id);
		return product;
	}


	public List<Book> getByKeys(List<Integer> books) {

		return (List<Book>) bookRepository.findAll(books);
	}

	public List<Book> getAll() {
		return bookRepository.findAll();
	}
}

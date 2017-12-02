package com.vladkazakov.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
public class Book extends Product {
	private String author;
	private String genre;
	private int countPage;

	public Book() {
	}

	public String getAuthor() {
		return author;
	}



	public String getGenre() {
		return genre;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public String toString() {
		return (id + ";" + name + ";" + genre + ";" + cartCount);
	}

	@Override
	public String getDescription() {
		String result = "Автор книги: " + author + ", жанр: " + genre + ", количество страниц " + countPage + ".";
		return result;
	}
}


package com.epam.vladkazakov.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Audiobook")
public class Audiobook extends Product {
	private String author;
	private String actor;
	private String genre;
	private int sizeMin;

	public Audiobook() {
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

	public String toString() {
		return (id + ";" + name + ";" + genre + ";" + actor + ";" + cartCount);
	}

	public int getSizeMin() {
		return sizeMin;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setSizeMin(int sizeMinute) {
		this.sizeMin = sizeMinute;
	}

	@Override
	public String getDescription() {
		String result = "Автор книги: " + author + ", актер озвучки: " + actor + ", жанр: " + genre + ", длительность(min): " + sizeMin + ".";
		return result;
	}
}

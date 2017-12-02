package com.vladkazakov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladkazakov.entities.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Integer> {
}

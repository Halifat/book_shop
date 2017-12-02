package com.epam.vladkazakov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.vladkazakov.entities.EBook;

@Repository("eBookRepository")
public interface EBookRepository extends JpaRepository<EBook, Integer> {

}

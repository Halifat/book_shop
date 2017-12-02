package com.vladkazakov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladkazakov.entities.Audiobook;

@Repository("audiobookRepository")
public interface AudiobookRepository extends JpaRepository<Audiobook, Integer> {

}

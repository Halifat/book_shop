package com.epam.vladkazakov.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.vladkazakov.entities.EBook;
import com.epam.vladkazakov.repositories.EBookRepository;

@Service("eBookService")
public class EBookService {
	@Autowired
	private EBookRepository eBookRepository;

	public EBookService() {
	}

	public EBook getById(int id) {
		
		return eBookRepository.findOne(id);
	}

	public List<EBook> getByKeys(List<Integer> ebooks) {
	
		return (List<EBook>) eBookRepository.findAll(ebooks);
	}

	public List<EBook> getAll() {
		return (List<EBook>) eBookRepository.findAll();
	}

}

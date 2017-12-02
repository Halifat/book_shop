package com.epam.vladkazakov.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.vladkazakov.entities.Audiobook;
import com.epam.vladkazakov.entities.Product;
import com.epam.vladkazakov.repositories.AudiobookRepository;

@Service("audiobookService")
public class AudiobookService {
	@Autowired
	private AudiobookRepository audiobookRepository;

	public AudiobookService() {

	}
	public Product getById(int id) {
		Product product = (Product) audiobookRepository.findOne(id);
		return product;
	}

	public List<Audiobook> getByKeys(List<Integer> audiobooks) {

		return (List<Audiobook>) audiobookRepository.findAll(audiobooks);
	}

	public List<Audiobook> getAll() {
		return (List<Audiobook>) audiobookRepository.findAll();
	}
}

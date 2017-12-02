package com.vladkazakov.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladkazakov.entities.Audiobook;
import com.vladkazakov.entities.Product;
import com.vladkazakov.repositories.AudiobookRepository;

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

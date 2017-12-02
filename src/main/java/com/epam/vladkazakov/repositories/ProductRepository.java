package com.epam.vladkazakov.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.vladkazakov.entities.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByCategory(String category, Pageable pageable);

	List<Product> findByNameContaining(String reg);

	List<Product> findFirst4ByOrderByIdDesc();

	Integer countByCategory(String category);
}

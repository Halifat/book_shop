package com.vladkazakov.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladkazakov.entities.Cart;

@Repository("cartRepository")
public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Cart> findByIdUser(int idUser);

	Cart findByIdUserAndIdProduct(int idUser, int idProduct);
}

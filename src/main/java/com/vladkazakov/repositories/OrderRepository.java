package com.vladkazakov.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladkazakov.entities.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByIdUserOrder(int id);

}

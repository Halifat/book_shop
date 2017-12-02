package com.vladkazakov.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vladkazakov.entities.Raiting;

@Repository("raitingRepository")
public interface RaitingRepository extends JpaRepository<Raiting, Integer> {
	List<Raiting> findByIdProduct(int id);

	Raiting findByIdProductAndIdUser(int idProduct, int idUser);

	@Query(value = "SELECT *" + " FROM `raiting` " + "group by `idProduct` " + "order by avg(`value`)  desc " + "limit 4", nativeQuery = true)
	List<Raiting> findSum();
}

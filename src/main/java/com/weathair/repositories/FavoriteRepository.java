package com.weathair.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weathair.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

//	@Query("select f from Favorite f join User u where u.id = :id")
	List<Favorite> findByUserId(int id);
}

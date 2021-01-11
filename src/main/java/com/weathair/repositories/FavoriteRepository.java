package com.weathair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weathair.entities.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}

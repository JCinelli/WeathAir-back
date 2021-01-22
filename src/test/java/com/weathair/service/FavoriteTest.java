package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weathair.entities.Favorite;
import com.weathair.exceptions.FavoriteException;
import com.weathair.services.FavoriteService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FavoriteTest {

	
	@Autowired
	FavoriteService favoriteService;
	
	@Test
	public void testFindAllFavorites() throws FavoriteException {
		List<Favorite> favorites = favoriteService.findAllFavorites();
		assertThat(!favorites.isEmpty());
	}
	
	@Test
	public void testFindFavoritesById() throws FavoriteException {
		Favorite favorite = favoriteService.findFavoriteById(1);
		
		assertThat(favorite.getDuration()).isEqualTo("eeeh");
	}
}

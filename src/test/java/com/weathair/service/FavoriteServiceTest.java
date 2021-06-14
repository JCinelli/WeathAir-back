package com.weathair.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.weathair.dto.FavoriteDto;
import com.weathair.dto.NotificationDto;
import com.weathair.entities.Favorite;
import com.weathair.entities.Notification;
import com.weathair.entities.User;
import com.weathair.exceptions.FavoriteException;
import com.weathair.exceptions.NotificationException;
import com.weathair.exceptions.TownshipException;
import com.weathair.exceptions.UserException;
import com.weathair.services.FavoriteService;
import com.weathair.services.NotificationService;
import com.weathair.services.TownshipService;
import com.weathair.services.UserService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class FavoriteServiceTest {

	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private TownshipService townshipService;
	@Autowired
	private UserService userService;
	
	
	@Test
	@Order(1)
	public void testFindAllFavorites() throws FavoriteException {
		List<Favorite> favorites = favoriteService.findAllFavorites();
		assertThat(!favorites.isEmpty());
	}
	
	@Test
	@Order(2)
	public void testFindFavoriteByUserId() throws FavoriteException {
		List<Favorite> favorites = favoriteService.findFavoriteByUserId(6);
		assertThat(favorites.get(0).getId()).isEqualTo(7);
	}
	
	@Test
	@Order(3)
	public void testFindFavoriteById() throws FavoriteException {
		Favorite favorite = favoriteService.findFavoriteById(8);
		assertThat(favorite.getDuration()).isEqualTo("day");
	}
	
	@Test 
	@Order(4)
	public void testCreateFavorite() throws FavoriteException, TownshipException, UserException {
		FavoriteDto favoriteDto = new FavoriteDto("day", "temperature", townshipService.findByInseeCode("09005"), userService.findUserById(6));
		
		this.favoriteService.createFavorite(favoriteDto);
		
		List<Favorite> favorites = this.favoriteService.findAllFavorites();
		
		assertThat(favorites.get(favorites.size() - 1).getLabelIndicator()).isEqualTo("temperature");
	}
	
	@Test
	@Order(5)
	public void testUpdateFavorite() throws FavoriteException, TownshipException, UserException {
		FavoriteDto favoriteDtoForUpdate = new FavoriteDto("week", "temperature", townshipService.findByInseeCode("09005"), userService.findUserById(6));
		
		List<Favorite> favorites = this.favoriteService.findAllFavorites();
		
		this.favoriteService.updateFavorite(favorites.get(favorites.size() - 1).getId(), favoriteDtoForUpdate);
		
		favorites = this.favoriteService.findAllFavorites();
		
		assertThat(favorites.get(favorites.size() - 1).getDuration()).isEqualTo("week");
	}
	
	@Test
	@Order(6)
	public void testDeleteNotification() throws FavoriteException {
		
		List<Favorite> favorites = this.favoriteService.findAllFavorites();
		int favoritessSizeBefore = favorites.size();
		int lastIndex = favorites.get(favorites.size() - 1).getId();
		
		this.favoriteService.deleteFavorite(lastIndex);
		
		favorites = this.favoriteService.findAllFavorites();
		int favoritessSizeAfter = favorites.size();
		
		assertThat(favoritessSizeAfter).isEqualTo(favoritessSizeBefore - 1);
	}

}

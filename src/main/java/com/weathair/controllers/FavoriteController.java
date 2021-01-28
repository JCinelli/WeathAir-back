package com.weathair.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weathair.dto.FavoriteDto;
import com.weathair.exceptions.FavoriteException;
import com.weathair.services.FavoriteService;

@RestController
@CrossOrigin
@RequestMapping("/favorites")
public class FavoriteController {
	
	private FavoriteService favoriteService;
	
	public FavoriteController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
	@GetMapping
	public ResponseEntity<?> getAllFavorites () throws FavoriteException {
		return ResponseEntity.ok().body(favoriteService.findAllFavorites());
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER') || hasAuthority('ROLE_USER_BAN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getFavoriteById (@RequestParam Integer id) throws FavoriteException {
		return ResponseEntity.ok().body(favoriteService.findFavoriteById(id));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER') || hasAuthority('ROLE_USER_BAN')")
	@GetMapping("user{id}")
	public ResponseEntity<?> getFavoriteByUserId (@RequestParam Integer id) throws FavoriteException {
		return ResponseEntity.ok().body(favoriteService.findFavoriteByUserId(id));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER')")
	@PostMapping
	public ResponseEntity<?> postFavorite (@Validated @RequestBody FavoriteDto favoriteDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(favoriteService.createFavorite(favoriteDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER')")
	@PutMapping("/{id}")
	public ResponseEntity<?> putFavorite (@RequestParam Integer id, @RequestBody FavoriteDto favoriteDto) throws FavoriteException {
		favoriteService.updateFavorite(id, favoriteDto);
		return ResponseEntity.ok("The favorite with id " + id + " has been successfully updated");
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR') || hasAuthority('ROLE_USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFavorite (@RequestParam Integer id) throws FavoriteException {
		favoriteService.deleteFavorite(id);
		return ResponseEntity.ok("The favorite with id " + id + " has been successfully deleted");
	}

}

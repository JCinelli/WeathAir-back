package com.weathair.controllers;

import org.springframework.http.ResponseEntity;
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

	@GetMapping
	public ResponseEntity<?> getAllFavorites () throws FavoriteException {
		return ResponseEntity.ok().body(favoriteService.findAllFavorites());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getFavoriteById (Integer id) throws FavoriteException {
		return ResponseEntity.ok().body(favoriteService.findFavoriteById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> postFavorite (@Validated @RequestBody FavoriteDto favoriteDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(favoriteService.createFavorite(favoriteDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putFavorite (@RequestParam Integer id, @RequestBody FavoriteDto favoriteDto) throws FavoriteException {
		favoriteService.updateFavorite(id, favoriteDto);
		return ResponseEntity.ok("The favorite with id " + id + " has been successfully updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFavorite (@RequestParam Integer id) throws FavoriteException {
		favoriteService.deleteFavorite(id);
		return ResponseEntity.ok("The favorite with id " + id + " has been successfully deleted");
	}

}

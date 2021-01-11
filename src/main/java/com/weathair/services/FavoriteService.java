package com.weathair.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.weathair.dto.FavoriteDto;
import com.weathair.entities.Favorite;
import com.weathair.exceptions.FavoriteException;
import com.weathair.repositories.FavoriteRepository;

/**
 * @author MIACHELL
 * 
 * Class FavoriteService for CRUD into Favorite table
 *
 */
@Service
public class FavoriteService {
	
	private FavoriteRepository favoriteRepository;

	public FavoriteService(FavoriteRepository favoriteRepository) {
		super();
		this.favoriteRepository = favoriteRepository;
	}
	
	/**
	 * This method finds all the Favorite in the DB
	 * 
	 * @return			List of Favorite
	 * @throws 			FavoriteException
	 */
	public List<Favorite> findAllFavorites() throws FavoriteException{
		List<Favorite> listFavorites = favoriteRepository.findAll();
		if (!listFavorites.isEmpty()) {
			return listFavorites;
		} else {
			throw new FavoriteException("There is no Favorite in the DB");
		}
	}
	
	/**
	 * This method finds a Favorite in the DB using an id
	 * 
	 * @return			Opt of Favorite
	 * @throws 			FavoriteException 
	 */
	public Favorite findFavoriteById(Integer id) throws FavoriteException{
		Optional<Favorite> favoriteOptional = favoriteRepository.findById(id);
		if (favoriteOptional.isPresent()) {
			return favoriteOptional.get();
		} else {
			throw new FavoriteException("No Favorite with id " + id + " was found in the DB");
		}
	}
	
	/**
	 * This method creates a new Favorite in the DB
	 * 
	 * @param 			FavoriteDto
	 * @return			The saved Favorite
	 */
	public Favorite createFavorite(FavoriteDto favoriteDto) {
		Favorite favorite = new Favorite();
		favorite.setDuration(favoriteDto.getDuration());
		favorite.setLabelIndicator(favoriteDto.getLabelIndicator());
		favorite.setTownship(favoriteDto.getTownship());
		return favoriteRepository.save(favorite);
	}
	
	/**
	 * This method updates a Favorite
	 * 
	 * @param 			id the id of the Favorite to update
	 * @param 			notificationDto the Favorite
	 * @return			The saved Favorite
	 * @throws 			FavoriteException 
	 */
	public Favorite updateFavorite(Integer id, FavoriteDto favoriteDto) throws FavoriteException {
		Favorite favoriteToUpdate = findFavoriteById(id);
		favoriteToUpdate.setDuration(favoriteDto.getDuration());
		favoriteToUpdate.setLabelIndicator(favoriteDto.getLabelIndicator());
		favoriteToUpdate.setTownship(favoriteDto.getTownship());
		return favoriteRepository.save(favoriteToUpdate);
	}
	
	/**
	 * This method deletes a Favorite
	 * 
	 * @param 			id the id of the Favorite to delete
	 * @throws 			FavoriteException
	 */
	public void deleteFavorite(Integer id) throws FavoriteException {
		Favorite favoriteToDelete = findFavoriteById(id);
		favoriteRepository.delete(favoriteToDelete);
	}

}

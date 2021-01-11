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

import com.weathair.dto.AdministratorDto;
import com.weathair.exceptions.UserException;
import com.weathair.services.AdministratorService;

@RestController
@CrossOrigin
@RequestMapping("/administrators")
public class AdministratorController {
	
	private AdministratorService administratorService;

	public AdministratorController(AdministratorService administratorService) {
		super();
		this.administratorService = administratorService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUsers () throws UserException {
		return ResponseEntity.ok().body(administratorService.findAllAdministrators());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById (Integer id) throws UserException {
		return ResponseEntity.ok().body(administratorService.findAdministratorById(id));
	}
	
	@PostMapping
	public ResponseEntity<?> postUser (@Validated @RequestBody AdministratorDto administratorDto, BindingResult resVal){
		if (!resVal.hasErrors()) {
			return ResponseEntity.ok().body(administratorService.createAdministrator(administratorDto));
		} else {
			return ResponseEntity.badRequest().body(resVal.getAllErrors());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putUser (@RequestParam Integer id, @RequestBody AdministratorDto administratorDto) throws UserException{
		administratorService.updateAdministrator(id, administratorDto);
		return ResponseEntity.ok("The administrator with id " + id + " has been successfully updated");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser (@RequestParam Integer id) throws UserException {
		administratorService.deleteAdministrator(id);
		return ResponseEntity.ok("The administrator with id " + id + " has been successfully deleted");
	}

}

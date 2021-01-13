package com.weathair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
public class UtilsController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String encoderPassword (@RequestParam String passToEncode) {
		return passwordEncoder.encode(passToEncode);
	}
}

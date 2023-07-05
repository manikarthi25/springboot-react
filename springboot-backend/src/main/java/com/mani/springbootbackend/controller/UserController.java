package com.mani.springbootbackend.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mani.springbootbackend.enitity.User;
import com.mani.springbootbackend.repo.UserRepo;
import com.mani.springbootbackend.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@PostMapping(value = "/users", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {

		for (MultipartFile file : files) {
			userService.saveUsers(file);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping(value = "/users", produces = "application/json")
	public CompletableFuture<ResponseEntity> getUsers(@RequestParam(value = "files") MultipartFile[] files)
			throws Exception {

		return userService.getUsers().thenApply(ResponseEntity::ok);

	}

	@GetMapping(value = "/getUsersByThread", produces = "application/json")
	public ResponseEntity getUsersByThread(@RequestParam(value = "files") MultipartFile[] files)
			throws Exception {

		CompletableFuture<List<User>> users1 = userService.getUsers();
		CompletableFuture<List<User>> users2 = userService.getUsers();
		CompletableFuture<List<User>> users3 = userService.getUsers();
		
		CompletableFuture.allOf(users1, users2, users3).join();
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

//https://mockaroo.com/ -> generate dummy records

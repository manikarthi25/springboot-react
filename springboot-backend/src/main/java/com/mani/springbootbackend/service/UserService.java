package com.mani.springbootbackend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mani.springbootbackend.enitity.User;
import com.mani.springbootbackend.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Async
	public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {
		long startTime = System.currentTimeMillis();
		List<User> userList = parseCSVFile(file);
		userList = userRepo.saveAll(userList);
		long endTime = System.currentTimeMillis();
		logger.info("Number Records : {}", userList.size() + " " + Thread.currentThread().getName());
		logger.info("Total time to save all records : {}", (endTime - startTime));
		return CompletableFuture.completedFuture(userList);
	}

	@Async
	public CompletableFuture<List<User>> getUsers() {
		logger.info("Get all users {}", Thread.currentThread().getName());
		return CompletableFuture.completedFuture(userRepo.findAll());
	}

	private List<User> parseCSVFile(MultipartFile file) throws Exception {
		List<User> userList = new ArrayList<>();
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] record = line.split(",");
				User user = new User();
				user.setFirstName(record[0]);
				user.setLastName(record[1]);
				user.setEmailId(record[2]);
				userList.add(user);
			}
		} catch (IOException e) {
			logger.error("exception dution parse CSE file : {}", e);
			throw new Exception("exception dution parse CSE file : {}", e);
		}
		return userList;
	}

}

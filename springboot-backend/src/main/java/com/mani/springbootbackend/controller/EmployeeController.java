package com.mani.springbootbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.springbootbackend.enitity.Employee;
import com.mani.springbootbackend.repo.EmployeeRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

}

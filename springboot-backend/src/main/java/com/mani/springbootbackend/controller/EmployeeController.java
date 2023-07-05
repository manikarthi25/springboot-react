package com.mani.springbootbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.springbootbackend.enitity.Employee;
import com.mani.springbootbackend.exception.ResourceNotFoundException;
import com.mani.springbootbackend.repo.EmployeeRepo;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping("/status")
	public String getAppStatus() {
		return "success";
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> searchEmployee(@PathVariable Long id) {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found " + id));
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found " + id));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());

		Employee employeeUpdate = employeeRepo.save(employee);
		return ResponseEntity.ok(employeeUpdate);
	}

}

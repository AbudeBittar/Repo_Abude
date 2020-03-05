package com.grokonez.spring.restapi.mongodb.controller;

/**
 * needed imports
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.spring.restapi.mongodb.model.Customer;
import com.grokonez.spring.restapi.mongodb.repo.CustomerRepository;

/**
 * 
 * @author Abudé Bittar
 * 
 * Controller Class for RESTful API
 *
 */


@CrossOrigin(origins = "http://localhost:4200")

/**
 * will register this class as the rest controller and will be able to receive Http requests.
 */
@RestController
/**
 * -- this controller will be responsible for all operations on URL.
 * -- http://locaalhost:8080/api
 */
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository repository;
	
	

	/**
	 * 
	 * @return
	 */
	@GetMapping("/customers") // to read data
	public List<Customer> getAllCustomers() {
		System.out.println("Get all Customers...");

		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(customers::add);

		return customers;
	}
	


	/**
	 * 
	 * @param customer
	 * @return
	 * 
	 */
	@PostMapping("/customer") // to create data
	public Customer postCustomer(@RequestBody Customer customer) {

		Customer _customer = repository.save(new Customer(customer.getName(), customer.getStreetName(), customer.getStreetNumber(), 
				customer.getPhoneNumber(), customer.getEmail()));
		return _customer;
	}

	

	/**
	 * 
	 * -- @PathVariable: to read the value of the variable "id" from the given path in the Http method
	 * @param id is from data type "String" and is annotated as @PathVariable
	 * @return
	 */
	@DeleteMapping(path = "/customer/{id}") // to delete data
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) // id is from data type "String" and is annotated as @PathVariable  
	{
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@GetMapping("customers/phoneNumber/{phoneNumber}")
	public List<Customer> findByPhoneNumber(@PathVariable int phoneNumber) {

		List<Customer> customers = repository.findByPhoneNumber(phoneNumber);
		return customers;
	}

	@PutMapping("/customer/{id}") // to update data
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<Customer> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			Customer _customer = customerData.get();
			_customer.setName(customer.getName());
			_customer.setStreetName(customer.getStreetName());
			_customer.setStreetNumber(customer.getStreetNumber());
			_customer.setPhoneNumber(customer.getPhoneNumber());
			_customer.setEmail(customer.getEmail());
			return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

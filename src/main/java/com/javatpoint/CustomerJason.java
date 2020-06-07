package com.javatpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerJason {
	@Autowired
	private CustomerService customerService;
	@GetMapping("/jsonCustomer")
	public List<Customer> getJasonCustomer() {
		
	
		List<Customer> theCustomers = customerService.getCustomers();
		
		
		return theCustomers ;
	}
	
	@GetMapping("/jsonCustomer/{customerId}")
	public Customer smallList(@PathVariable int customerId )
	{
		if(customerId>customerService.maxId()||    customerId <0 ) {
			throw new CustomwrNotFoundException("Customer id not found - " + customerId);
			
		}
		Customer theCustomer =customerService.getCustomer(customerId);
		
		return theCustomer;
		
	}
	
	@GetMapping("/jsonCustomer/{customerId}/{customerId2}")
	public List<Customer> smallList(@PathVariable int customerId,@PathVariable int customerId2  )
	{
		if(customerId>customerService.maxId()||    customerId <0 ) {
			throw new CustomwrNotFoundException("Customer id not found - " + customerId);
			
		}
		List<Customer> theCustomer = new ArrayList<>();
		theCustomer.add(customerService.getCustomer(customerId));
		theCustomer.add(customerService.getCustomer(customerId2));
		
		return theCustomer;
		
	}
	
	@PostMapping("/jsonCustomer")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		
		return theCustomer ;
	}
	
	@PutMapping ("/jsonCustomer")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return  theCustomer;
	}
	
	@DeleteMapping ("/jsonCustomer/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted Customer id  - "+customerId;
	}
	// this exception handler as a local 
	/*
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse>handleException(CustomwrNotFoundException exc){
		
		CustomerErrorResponse error =new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
	return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse>handleException(Exception exc){
		
		CustomerErrorResponse error =new CustomerErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
*/
}










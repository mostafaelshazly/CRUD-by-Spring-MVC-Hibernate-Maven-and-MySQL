package com.javatpoint;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;



@Controller
@RequestMapping("/customer")
public class HelloController {
@RequestMapping("/")
	public String display()
	{
		return "index";
	}	

@Autowired
private CustomerService customerService;

@RequestMapping("/list")
public String listCustomers(Model theModel) {
	
	// get customers from the service
	List<Customer> theCustomers = customerService.getCustomers();
			
	// add the customers to the model
	theModel.addAttribute("customers", theCustomers);
	
	return "list-customers";
}

@GetMapping("/showFormForAdd")
public String showFormForAdd(Model theModel) {
	
	Customer theCustomer =new Customer () ;
	theModel.addAttribute("customer",theCustomer);
	
	return "customer-form";
}

@PostMapping("/saveCustomer")
public String saveCustomer(@ModelAttribute("customer")   Customer theCustomer) {
	
	customerService.saveCustomer(theCustomer);
	
	return "redirect:/customer/list";
}

@GetMapping("/showFormForUpdate")
public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel) {
	
	Customer theCustomer =customerService.getCustomer(theId);
	theModel.addAttribute("customer",theCustomer);
	
	return "customer-form";
}

@GetMapping("/deleteCustomer")
public String deleteCustomer(@RequestParam("customerId")int theId) {
	
	customerService.deleteCustomer(theId);
	
	return "redirect:/customer/list";
}


	

}




package com.china.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.china.logindao.LoginDAO;
import com.china.model.CustomerModel;
import com.china.service.CustomerService;
import com.china.service.LoginMessage;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/register")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
//	@GetMapping("/name")
//	public String name() {
//		return "chinna";
//	}
	
	@GetMapping("/viewAllCustomer")
	public List<CustomerModel> customer() {
		return service.getAll();
	}
	
//================================================================================================================
	
	/****************************
	 * Method: addCustomers Description: It is used to add into the customers table
	 * 
	 * @returns customers It returns String type message
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given
	 *               URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain
	 *               object in method parameter or return type. 
	 *               
	 * Created By-ChinaRaju
	 * 
	 ****************************/
	
	@PostMapping("/customer")
	public CustomerModel save(@RequestBody CustomerModel customer) {
		service.saveOrUpdate(customer);
		return customer;	
	}
	
//=============================================================================================================================
	
	/****************************
	 * Method: deleteCustomer
	 * Description: It is used to remove the items in the customer table
	 * @returns customer It returns String type message 
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By:ChinaRaju
	 * 
	 ****************************/
	
	@DeleteMapping("/deleteCustomer/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	//=====================================================================================================================================
	
	/****************************
	 * Method: viewCustomer by Id
	 * Description: It is used to get Customer items  by specifying Id in Customer table
	 * @returns Customer It returns Customer  with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: ChinaRaju
	 * 
	 ****************************/
	
	
	
	@GetMapping("/viewCustomer/{id}")
	public CustomerModel findById(@PathVariable Integer id) {
		
		return service.getCustomerById(id);
	}
	
// =========================================================================================================================================	
	
		/****************************
		 * Method: Update customers 
		 * Description: It is used to update customers in customers table
		 * @returns customers It returns String type message 
		 * @PutMapping: It is used to handle the HTTP POST requests matched with given URI expression.
		 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
		 * Created By-ChinaRaju
		 * 
		 ****************************/	
	
	@PutMapping("/updateCustom/{id}")
	public String update(@RequestBody CustomerModel order,@PathVariable Integer id) {
		
		Optional<CustomerModel> option =service.findById(id);
		CustomerModel bean=option.get();
		
//		bean.setCustomerName(order.getCustomerName());
//		bean.setEmailId(order.getEmailId());;
//		bean.setMobileNumber(order.getMobileNumber());
		LoginDAO dao = new LoginDAO();
		List<CustomerModel> cust =service.getAll();
		
		if(order.getEmailId().equals(dao.getEmailId())) {
		bean.setPassword(order.getPassword());
		 service.save(bean);
		 return "Password success";
		}
//		bean.setId(order.getId());
		else {
			return "email not matched ";
		}

}

//=================================================================================================================================================
	

	/****************************
	 * Method: validate Customer
	 * Description: It is used to validate into Customer table
	 * @returns Customer It returns Customer with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * Created By: ChinaRaju 
	 * 
	 ****************************/
	
	@PostMapping("/customer/login")
	public LoginMessage login(@RequestBody LoginDAO loginDTO) {
		
		LoginMessage loginResponse = service.loginCustomer(loginDTO);
		
		return loginResponse;
	}
	
	
//	@PutMapping("/customer")
//	public LoginMessage reset(@RequestBody LoginDAO loginDao ) {
//		LoginMessage msg = service.resetPassword(loginDao);
//		return msg;
//	}
//	
//	@EventListener(ApplicationReadyEvent.class)
	@PutMapping("/customer/reset")
	public LoginMessage resetPassword(@RequestBody LoginDAO loginDao ) {
		LoginMessage msg = service.resetPassword(loginDao);
		return msg;
	}
	
	@PutMapping("/password")
	public LoginMessage password(@RequestBody LoginDAO dao) {
		LoginMessage msg   = service.password(dao);
		return msg;
		
	}
	
	
	@GetMapping("/email/{emailId}")
	public CustomerModel viewCustomerbyEmail(@PathVariable String emailId)  {
		return service.getCustomerbyEmail(emailId);
	}
	
	@PutMapping("/update/{id}")
	public CustomerModel updateMedicine(@RequestBody CustomerModel model,@PathVariable   Integer id) {
		  return   service.updateCustomer(model, id); 
		
		
	}

}

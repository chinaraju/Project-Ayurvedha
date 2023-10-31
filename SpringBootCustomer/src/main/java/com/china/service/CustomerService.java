package com.china.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.china.Repository.CustomerRepository;
import com.china.logindao.LoginDAO;
import com.china.model.CustomerModel;



@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository reop;
	@Autowired
    private JavaMailSender mailService;
	
	String otp;
	int id;
	
	public List<CustomerModel> getAll(){
		ArrayList<CustomerModel> list = new ArrayList<CustomerModel>();
		    reop.findAll().forEach(p->list.add(p));
		    return list;
		
	}
	
	public CustomerModel getCustomerById(int id) {

		return reop.getCustomerById(id);
	}

	// saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(CustomerModel employeeDetails) {
		
		String password = employeeDetails.getPassword();
		 Encoder  encoder= Base64.getEncoder();
		String encodepassword=encoder.encodeToString(password.getBytes());
		employeeDetails.setPassword(encodepassword);

		reop.save(employeeDetails);
	}

	// deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(int id) {
		reop.deleteById(id);
	}

	// updating a record
	public void update(CustomerModel employeeDetails, int Employeeid) {
		reop.save(employeeDetails);
	}

	public Optional<CustomerModel> findById(Integer id) {
		// TODO Auto-generated method stub
		return reop.findById(id);
	}

	public  CustomerModel save(CustomerModel bean) {
		// TODO Auto-generated method stub
		return reop.save(bean);
	}
	
	
	  public LoginMessage  loginCustomer(LoginDAO loginDAO) {
		  
	        String mail = loginDAO.getEmailId();
	        String number=loginDAO.getField();
//	        CustomerModel employee1 = reop.findByEmailId(loginDAO.getEmailId());
	        CustomerModel employee1 = reop.findOneByEmailIdOrMobileNumber(number, number);
	        if (employee1 != null) {
	            String password = loginDAO.getPassword();
	            
	            Encoder  encoder= Base64.getEncoder();
	    		String getpassword=encoder.encodeToString(password.getBytes());
	            
	            String encodedPassword = employee1.getPassword();
	          //  Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
	            if (getpassword.equals(encodedPassword)) {
//	                Optional<CustomerModel> employee = reop.findOneByEmailIdAndPassword(loginDAO.getEmailId(), encodedPassword);
	            	  Optional<CustomerModel> employee = reop.findOneByEmailIdOrMobileNumberAndPassword(number, number, encodedPassword);
	                if (employee.isPresent()) {
	                    return new LoginMessage("Login Success", true);
	                } else {
	                    return new LoginMessage("Login Failed", false);
	                }
	            } else {
	                return new LoginMessage("password Not Match", false);
	            }
	        }else {
	            return new LoginMessage("Email not exits", false);
	        }
	    }
	
   
//  public LoginMessage resetPassword(LoginDAO  loginDao) {
//	  
//	  CustomerModel mail1 = reop.findByEmailId(loginDao.getEmailId());
//	  
//	   if(mail1 != null) {
//		   String mail = mail1.getEmailId();
//		   String m =loginDao.getEmailId();
//	  if(mail.equals(m)) {
//		  int id = mail1.getId();
//		  Optional<CustomerModel> model = reop.findById(id);
//		  CustomerModel models = model.get();
//		  models.setPassword(loginDao.getPassword());
//		   reop.save(models);
//		  return  new LoginMessage("Password rest Success full",true);
//	  }
//	  else {
//		  return new LoginMessage("email not matched ",false);
//	  }
//	   }
//	   else {
//		   return new LoginMessage("mail is not exists",false);
//	   }
//  }
	  
	  
	  public LoginMessage resetPassword(LoginDAO  loginDao) {
		  
		  CustomerModel mail1 = reop.findByEmailId(loginDao.getEmailId());
		  
		   if(mail1 != null) {
			   String mail = mail1.getEmailId();
			   String m =loginDao.getEmailId();
		  if(mail.equals(m)) {
			  
			  SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(mail);
				message.setSubject("Reset you are password OTP ");
				Random random =  new Random();
				  otp =(Integer.toString( random.nextInt(999999)));
				  String body= "Please enter "+otp+" you are One time OTP ";
				message.setText(body);
			   mailService.send(message);
			//   String otp =loginDao.getOtp();
//			  if(s.equals(otp)) {
				   id = mail1.getId();
//				  Optional<CustomerModel> model = reop.findById(id);
//				  CustomerModel models = model.get();
//				  models.setPassword(loginDao.getPassword());
//				   reop.save(models);
//				  return  new LoginMessage("Password rest Success full",true);
//				  
//			  }
//			  else {
//			  return  new LoginMessage("Incorrect OTP",false);
//			  }
			   
			   return new LoginMessage("Otp Send mail check it",true);
		  }
		  else {
			  return new LoginMessage("email not matched ",false);
		  }
		   }
		   else {
			   return new LoginMessage("mail is not exists",false);
		   }
	  }
  
  
  
  public LoginMessage password(LoginDAO dao) {
	  
	  if(dao.getOtp().equals(otp)) {
		  CustomerModel mail1 = reop.findByEmailId(dao.getEmailId());
		  
		  
		  Optional<CustomerModel> model = reop.findById(id);
		  CustomerModel models = model.get();
		  String mail2 =models.getEmailId();	
		  
		  Encoder  encoder= Base64.getEncoder();
			String encodepassword=encoder.encodeToString(dao.getPassword().getBytes());
		  models.setPassword(encodepassword);
		   reop.save(models);
		   
		   
			  SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(mail2);
				message.setSubject("Reset you are  new password  ");
				Random random =  new Random();
				int atIndex = mail2.indexOf('@');
				 String username = mail2.substring(0, atIndex);
				  String body= "Welcome " + username  +"\nyou are new  password "+dao.getPassword()+" please be login have a greate day ";
				message.setText(body);
			   mailService.send(message);
			   
		  return  new LoginMessage("Password reset Success full",true);
	  }
	  else {
		  return new LoginMessage("OTP is incorrect  ",false);
	  }
  }
  
  
  public CustomerModel getCustomerbyEmail(String emailId) {
		
//		CustomerModel findByEmailId =reop.findByEmailId(emailId);
		
		CustomerModel findByEmailId =reop.findOneByEmailIdOrMobileNumber(emailId, emailId);
		return findByEmailId;
	}
  
  
  public CustomerModel updateCustomer(CustomerModel model, Integer id) {
		// TODO Auto-generated method stub
		Optional<CustomerModel>  optional = reop.findById(id);
		CustomerModel models = optional.get();
		  models.setId(model.getId());
		  models.setCustomerName(model.getCustomerName());
//		  models.setEmailId(model.getEmailId());
		  models.setMobileNumber(model.getMobileNumber());
//		  models.setPassword(model.getPassword());
		  
		
		return reop.save(models);
	}
	
	
	
    
	
}

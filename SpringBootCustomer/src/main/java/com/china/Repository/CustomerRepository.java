package com.china.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.china.model.CustomerModel;
import java.util.List;


public interface CustomerRepository extends CrudRepository<CustomerModel, Integer>{
	
	
	@Query("SELECT u FROM CustomerModel  u WHERE id=:id")
	public CustomerModel getCustomerById(int id);
	
	 Optional<CustomerModel> findOneByEmailIdAndPassword(String emailId, String password);
	    CustomerModel findByEmailId(String emailId);
	   CustomerModel findOneByEmailIdOrMobileNumber(String emailId, String mobileNumber);

	   Optional<CustomerModel> findOneByEmailIdOrMobileNumberAndPassword(String emailId, String mobileNumber, String password);


}

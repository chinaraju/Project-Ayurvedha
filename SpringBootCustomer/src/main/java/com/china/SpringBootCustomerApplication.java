
package com.china;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringBootCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCustomerApplication.class, args);
	}

}
 
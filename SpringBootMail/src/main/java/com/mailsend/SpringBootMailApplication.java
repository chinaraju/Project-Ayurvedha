package com.mailsend;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.mailsend.service.MailSendService;



@SpringBootApplication
public class SpringBootMailApplication {
	
	@Autowired
	private MailSendService	mailService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		Random rr=  new Random();
		  String s =(Integer.toString( rr.nextInt(9999)));
		mailService.mainSend("chinaraju93@gmail.com", s, "otp");
	}
}

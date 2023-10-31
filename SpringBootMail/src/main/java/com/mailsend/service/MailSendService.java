package com.mailsend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	
	
	@Autowired
    private JavaMailSender mailService;
	public void mainSend(String to,String body,String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
	   mailService.send(message);
	}
    
}

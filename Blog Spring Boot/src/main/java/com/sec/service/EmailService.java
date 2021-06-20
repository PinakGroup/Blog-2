package com.sec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sec.entity.User;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(this.getClass());
    
    
    
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendActivationMessage(User user) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(user.getEmail());
			message.setSubject("Regisztráció aktiválás");
			message.setText("Kedves " + user.getFullName() + "! \n \nKöszönjük, hogy csatlakozol az oldalunkhoz! \n \nKérlek aktiváld a profilod a következó linken: "
					+ "http://localhost:8080/activation/" + user.getActivation());
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.debug("Hiba e-mail küldéskor az alábbi címre: " + user.getEmail() + "  " + e);
		}
		

	}
	
	public void sendConfirmationMessage(User user) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(user.getEmail());
			message.setSubject("Sikeres regisztráció");
			message.setText("Kedves " + user.getFullName() + "! \n \n"
					+ "Sikeres regisztráció. Köszönjük hogy csatlakoztál!");
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.debug("Hiba e-mail küldéskor az alábbi címre: " + user.getEmail() + "  " + e);
		}
		

	}
	
	
}

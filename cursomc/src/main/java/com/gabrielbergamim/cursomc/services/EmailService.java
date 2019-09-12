package com.gabrielbergamim.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.gabrielbergamim.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}

package com.gabrielbergamim.cursomc.services;

import com.gabrielbergamim.cursomc.domain.Cliente;
import org.springframework.mail.SimpleMailMessage;

import com.gabrielbergamim.cursomc.domain.Pedido;

import javax.mail.internet.MimeMessage;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
}

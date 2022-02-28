package com.projeto.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.projeto.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}

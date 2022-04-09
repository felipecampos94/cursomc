package com.projeto.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.projeto.cursomc.security.UserSS;

public class UserService {

	// Método que retorna o usuário logado no sistema
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}

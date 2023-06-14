package br.com.cambioservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.cambioservice.model.Cambio;

@Service
public interface CambioService {
	
	Cambio findByFromAndTo(String from, String to, BigDecimal amount);

}
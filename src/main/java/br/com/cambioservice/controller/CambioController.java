package br.com.cambioservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cambioservice.model.Cambio;
import br.com.cambioservice.service.CambioService;

@RestController
@RequestMapping("v1/cambio-service")
public class CambioController {
	
	@Autowired
	private CambioService cambioService;
	
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to) {		
		return cambioService.findByFromAndTo(from, to, amount);	
	}
}

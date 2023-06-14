package br.com.cambioservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.cambioservice.model.Cambio;
import br.com.cambioservice.repository.CambioRepository;

@Service
public class CambioServiceImpl implements CambioService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioRepository cambioRepository;
	
	public Cambio findByFromAndTo(String from, String to, BigDecimal amount) {
		var cambio = cambioRepository.findByFromAndTo(from, to);
		if(cambio == null) throw new RuntimeException();
			
		var port = environment.getProperty("local.server.port");
		BigDecimal convertedValue = cambio.getConversionFactor().multiply(amount);
		//Arredondando para 2 casas decimais
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		cambio.setEnvironment(port);
		return cambio;
	}

}
package br.com.cardboardbox.logistica;

import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.cardboardbox.logistica.beans.Transportadoras;

@Configuration
public class AplicationConfig {

	@Bean
	public Transportadoras transportadoras() {
		Transportadoras transp = new Transportadoras();
		transp.addTransportadoras(Arrays.asList(
				"http://localhost:8080/service/transportadora/2",
				"http://localhost:8080/service/transportadora/3",
				"http://localhost:8080/service/transportadora/4",
				"http://localhost:8080/service/transportadora/5"
		));
		return transp;
	}
	
	@Bean
	public RestTemplate restTemplate( RestTemplateBuilder builder) {
		return builder.build();
	}
}

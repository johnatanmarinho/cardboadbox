package br.com.cardboardbox.logistica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.cardboardbox.logistica.beans.Transportadora;
import br.com.cardboardbox.logistica.dao.TransRepository;
import br.com.cardboardbox.logistica.filtros.Filtro;
import br.com.cardboardbox.logistica.filtros.FiltroPreco;
import br.com.cardboardbox.logistica.filtros.FiltroTempo;

@RestController
@RequestMapping("transportadoras")
public class TransportadoraController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	TransRepository repository;
	
	String[] services = {
			"http://localhost:8080/service/transportadora/2",
			"http://localhost:8080/service/transportadora/3",
			"http://localhost:8080/service/transportadora/4"
	};
	
	@Bean
	public RestTemplate restTemplate( RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	private List<Transportadora> getTransportadoras(){
		List<Transportadora> lista = new ArrayList<>();
		
		for (String url : services ) {
			lista.add( restTemplate.getForObject(url, Transportadora.class) );
		}
		
		Transportadora transp1 = new Transportadora("transportadora 1", repository.findAll());
		lista.add(transp1);
		return lista;
	}
	
	@GetMapping("/all")
	public List<Transportadora> teste() {
		return getTransportadoras();
	}
	
	/**
	 * Calcula melhor Preco 
	 * @param distancia distancia em kilomentros
	 * @return
	 */
	@GetMapping("/melhorPreco/{distancia}")
	public List<Transportadora> getMelhorPreco(@PathVariable int distancia) {
		
		FiltroPreco filtro = new FiltroPreco();
		filtro.setProximoFiltro(new FiltroTempo());
		
		return filtro.filtrar(getTransportadoras(), distancia);
		
	}
	
	/**
	 * Calcula melhor Preco com base no tipo
	 * @param distancia distancia em kilomentros
	 * @param tipo 1 para Aereo 2 para Terrestre
	 * @return
	 */
	@GetMapping("/melhorPreco/{distancia}/{tipo}")
	public List<Transportadora> getMelhorPreco(
			@PathVariable int distancia,
			@PathVariable int tipo) {
		
		FiltroPreco filtro = new FiltroPreco();
		filtro.setProximoFiltro(new FiltroTempo());
		
		return filtro.filtrar(getTransportadoras(), distancia, tipo);
	}
	
	
	/**
	 * Calcula melhor Tempo de entrega
	 * @param distancia distancia em kilomentros
	 * @return
	 */
	@GetMapping("/melhorTempo/{distancia}")
	public List<Transportadora> getMelhorTempo(@PathVariable int distancia) {
		FiltroTempo filtro = new FiltroTempo();
		filtro.setProximoFiltro(new FiltroPreco());
		
		return filtro.filtrar(getTransportadoras(), distancia);
	}
	
	
	/**
	 * Calcula melhor Tempo de entrega com base no tipo
	 * @param distancia distancia em kilomentros
	 * @param tipo 1 para Aereo 2 para Terrestre
	 * @return
	 */
	@GetMapping("/melhorTempo/{distancia}/{tipo}")
	public List<Transportadora> getMelhorTempo(
			@PathVariable int distancia,
			@PathVariable int tipo) {

		FiltroTempo filtro = new FiltroTempo();
		filtro.setProximoFiltro(new FiltroPreco());
		
		return filtro.filtrar(getTransportadoras(), distancia, tipo);
	}
	
}

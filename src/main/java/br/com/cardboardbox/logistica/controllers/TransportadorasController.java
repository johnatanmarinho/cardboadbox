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
import br.com.cardboardbox.logistica.beans.Transportadoras;
import br.com.cardboardbox.logistica.dao.TransRepository;
import br.com.cardboardbox.logistica.filtros.FiltroPreco;
import br.com.cardboardbox.logistica.filtros.FiltroTempo;

@RestController
@RequestMapping("transportadoras")
public class TransportadorasController {

	
	@Autowired
	Transportadoras restRepo;
	
	
	@GetMapping("/all")
	public List<Transportadora> teste() {
		return restRepo.getTransportadoras();
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
		
		return filtro.filtrar(restRepo.getTransportadoras(), distancia);
		
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
		
		return filtro.filtrar(restRepo.getTransportadoras(), distancia, tipo);
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
		
		return filtro.filtrar(restRepo.getTransportadoras(), distancia);
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
		
		return filtro.filtrar(restRepo.getTransportadoras(), distancia, tipo);
	}
	
}

package br.com.cardboardbox.logistica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardboardbox.logistica.beans.Rota;
import br.com.cardboardbox.logistica.beans.Transportadora;
import br.com.cardboardbox.logistica.beans.Transportadoras;
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
	
	@PostMapping("/melhorPreco")
	public List<Transportadora> getMelhorPreco(@RequestBody Rota rota) {
		
		FiltroPreco filtro = new FiltroPreco();
		filtro.setProximoFiltro(new FiltroTempo());
		
		if(rota.getTipo() == 0)
			return filtro.filtrar(restRepo.getTransportadoras(), rota.getDistancia());
		
		return filtro.filtrar(restRepo.getTransportadoras(), rota.getDistancia(), rota.getTipo());
	}
	
	@PostMapping("/melhorTempo")
	public List<Transportadora> getMelhorTempo(@RequestBody Rota rota) {
		FiltroTempo filtro = new FiltroTempo();
		filtro.setProximoFiltro(new FiltroPreco());
		if(rota.getTipo() == 0)
			return filtro.filtrar(restRepo.getTransportadoras(), rota.getDistancia());
		
		return filtro.filtrar(restRepo.getTransportadoras(), rota.getDistancia(), rota.getTipo());
		
	}
	
	
}

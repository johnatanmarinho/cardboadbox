package br.com.cardboardbox.logistica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cardboardbox.logistica.beans.Frete;
import br.com.cardboardbox.logistica.beans.Transportadora;


@RestController
@RequestMapping("/service/")
public class ServicesController {
	
	
	
	@GetMapping("/transportadora/2")
	public Transportadora getTransportadora2(){
		Transportadora t2 = new Transportadora("transportadora 2", new ArrayList<Frete>());
		
		t2.addTransport(Frete.TERRESTRE, 75.0, 59);
		t2.addTransport(Frete.AEREO, 200.0, 30);
		
		return t2;
	}
	
	@GetMapping("/transportadora/3")
	public Transportadora getTransportadora3(){
		Transportadora t2 = new Transportadora("transportadora 3", new ArrayList<Frete>());
		
		t2.addTransport(Frete.TERRESTRE, 55.0, 65);
		t2.addTransport(Frete.AEREO, 180.0, 33);
		
		return t2;
	}
	
	@GetMapping("/transportadora/4")
	public Transportadora getTransportadora4(){
		Transportadora t2 = new Transportadora("transportadora 4", new ArrayList<Frete>());
		
		t2.addTransport(Frete.AEREO, 175.0, 30);
		
		return t2;
	}
}

package br.com.cardboardbox.logistica.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import br.com.cardboardbox.logistica.dao.TransRepository;

public class Transportadoras {

	private List<Transportadora> transportadoras = new ArrayList<Transportadora>();
	private List<String> services = new ArrayList<String>();
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	TransRepository repository;

	

	public List<Transportadora> getTransportadoras(){
		
		for (String url : services ) {
			transportadoras.add( restTemplate.getForObject(url, Transportadora.class) );
		}
		
		Transportadora transp1 = new Transportadora("transportadora 1", repository.findAll());
		transportadoras.add(transp1);
		return transportadoras;
	}
	

	public void addTransportadoras(List<String> service) {
		this.services.addAll(service);
	}
	
	public void addTransportadora(String service) {
		this.services.add(service);
	}
}

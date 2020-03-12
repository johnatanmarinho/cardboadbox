package br.com.cardboardbox.logistica.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Transportadora {
	
	private String nome;
	List<Frete> fretes;

	
	public Transportadora(String nome, List<Frete> fretes) {
		super();
		this.nome = nome;
		this.fretes = fretes;
	}
	
	public Transportadora(String nome) {
		this.nome = nome;
		this.fretes = new ArrayList<Frete>();
	}



	public void addTransport(int tipo, double valorKm, int tempoKm) {
		Frete t = new Frete(tipo, valorKm, tempoKm);
		this.fretes.add(t);
		
	}
	
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Frete getTransportByType(int tipo) {
		Frete result = fretes.stream()
				.filter(frete -> frete.getTipo() == tipo)
				.findAny()
				.orElse(null);
		return result;
	}
	
	public List<Frete> getFretes(){
		return this.fretes;
	}
	
	@Override
	public String toString() {
		String text = "";
		
		for (Frete frete : this.fretes) {
			text += "tipo frete: " + frete.getTipo() + 
					" valor por km: " + frete.getValorKm() +
					" tempo medio: " + frete.getTempoKm() + "\n";
		}
		return text;
	}

}

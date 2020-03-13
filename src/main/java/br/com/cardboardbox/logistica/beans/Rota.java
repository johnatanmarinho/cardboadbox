package br.com.cardboardbox.logistica.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Rota {

	private String origem;
	private String destino;
	private int tipo;
	
	private double distancia;
	
	public Rota() {
		
	}
	public Rota(String origem, String destino, int tipo) {
		this.origem = origem;
		this.destino = destino;
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	
	
}

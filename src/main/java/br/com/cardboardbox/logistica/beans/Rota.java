package br.com.cardboardbox.logistica.beans;

import java.util.OptionalInt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown= true)
public class Rota {

	private String origem;
	private String destino;
	private OptionalInt tipo = OptionalInt.empty();
	
	private double distancia;
	
	public Rota() {
		
	
	}
	
	public Rota(String origem, String destino) {
		this.origem = origem;
		this.destino = destino;
	}
	
	public Rota(String origem, String destino, double distancia) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}
	
	
	public Rota(String origem, String destino, double distancia, int tipo) {
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
		this.tipo = OptionalInt.of(tipo);
	}
	
	public Rota(String origem, String destino, int tipo) {
		this.origem = origem;
		this.destino = destino;
		this.tipo = OptionalInt.of(tipo);
	}
	
	public OptionalInt getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = OptionalInt.of(tipo);
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
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "origem: " + origem + " destino: " + destino + "distancia: " + this.distancia + " tipo tramsporte: " + tipo;
	}
}

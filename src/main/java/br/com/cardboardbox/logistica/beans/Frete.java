package br.com.cardboardbox.logistica.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Frete {
	
	@Transient
	public static final int AEREO = 1;

	@Transient
	public static final int TERRESTRE = 2;
	
	
	@Id
	protected int tipo;
	protected double valorKm;
	protected int tempoKm;
	
	public Frete() {
		
	}
	
	public Frete(int tipo, double valorKm, int tempoKm) {
		this.tipo = tipo;
		this.valorKm = valorKm;
		this.tempoKm = tempoKm;
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public double getValorKm() {
		return valorKm;
	}
	public void setValorKm(double valorKm) {
		this.valorKm = valorKm;
	}
	public int getTempoKm() {
		return tempoKm;
	}
	public void setTempoKm(int tempoKm) {
		this.tempoKm = tempoKm;
	}

	@Transient
	public double getPrecoFinal(double distancia) {
		return ( valorKm * distancia ) /10;
	}
	
	@Transient
	public double getTempoEntrega( double distancia ) {
		return (distancia * tempoKm)/ 60;
	}

}

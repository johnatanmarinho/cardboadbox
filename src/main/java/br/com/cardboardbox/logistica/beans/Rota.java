package br.com.cardboardbox.logistica.beans;

public class Rota {

	private String origem;
	private String destino;
	private double distancia;
	private double tempoEstimado;
	
	
	public Rota(String string, String string2) {
		// TODO Auto-generated constructor stub
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

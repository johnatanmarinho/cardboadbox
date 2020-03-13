package br.com.cardboardbox.logistica.filtros;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public abstract class Filtro {
	
	private Filtro proximoFiltro;
	
	public void setProximoFiltro(Filtro filtro) {
		this.proximoFiltro = filtro;
	}
	
	public boolean hasNext() {
		return proximoFiltro != null;
	}
	
	public Filtro getNext() {
		return proximoFiltro;
	}
	
	public List<Transportadora> filtraPeloTipo(List<Transportadora> transportadoras, int tipoFrete) {
		return transportadoras.stream()
				.filter(t -> t.getFreteByTipo(tipoFrete) != null)
				.collect(Collectors.toList());
	}
	
	public abstract List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia, int tipoFrete);

	public abstract List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia);
	
	
}

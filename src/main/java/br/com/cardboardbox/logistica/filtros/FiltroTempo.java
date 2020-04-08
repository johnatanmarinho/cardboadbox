package br.com.cardboardbox.logistica.filtros;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public class FiltroTempo extends Filtro{

	@Override
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia, int tipoFrete) {
		List<Transportadora> result = filtraPeloTipo(transportadoras, tipoFrete);

		double menorTempo = result.stream()
				.map( t -> t.getFreteByTipo(tipoFrete).getTempoEntrega(distancia))
				.min( Double::compare ).get();
				
		result.retainAll(
			result.stream()
				.filter( t -> t.getFreteByTipo(tipoFrete).getTempoEntrega(distancia) == menorTempo)
				.collect(Collectors.toList())
		);
		
		
		getProximoFiltro().ifPresent(
			filtro -> {
				result.retainAll(filtro.filtrar(result, distancia, tipoFrete));
			}
		);
		return result;
	}

	public List<Transportadora> filtrar(List<Transportadora> transportadoras,  double distancia) {
		List<Transportadora> result;
		
		double menorTempo = transportadoras.stream().map(
			t -> t.getFretes().stream().map(f -> f.getTempoKm()).min(Integer::compare).get()
		).min(Integer::compare).get();
		
		result = transportadoras.stream()
					.filter(
						t -> t.getFretes().stream()
							.map(f -> f.getTempoEntrega(distancia))
							.min(Double::compare).get() == menorTempo
					).collect(Collectors.toList());
		
		getProximoFiltro().ifPresent(
			filtro -> result.retainAll(filtro.filtrar(result, distancia))
		);
		return result;
	}

	

}

package br.com.cardboardbox.logistica.filtros;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public class FiltroTempo extends Filtro{

	@Override
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia, int tipoFrete) {
		List<Transportadora> result = filtraPeloTipo(transportadoras, tipoFrete);

		double menorTempo = result.stream()
				.mapToDouble( t -> t.getFreteByTipo(tipoFrete).getTempoEntrega(distancia))
				.min().getAsDouble();
				
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
		
		double menorTempo = transportadoras.stream()
				.mapToDouble( t -> {
					return t.getFretes().stream()
							.mapToDouble(f -> f.getTempoEntrega(distancia))
							.min().getAsDouble();
				})
				.min().getAsDouble();
		
		result = transportadoras.stream()
				.filter( t -> {
					return t.getFretes().stream()
							.mapToDouble(f -> f.getTempoEntrega(distancia))
							.min().getAsDouble() == menorTempo;
				})
				.collect(Collectors.toList());
		
		getProximoFiltro().ifPresent(
			filtro -> {
				result.retainAll(filtro.filtrar(result, distancia));
			}
		);
		return result;
	}

	

}

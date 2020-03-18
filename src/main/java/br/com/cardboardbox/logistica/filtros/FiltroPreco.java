package br.com.cardboardbox.logistica.filtros;

import java.util.List;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public class FiltroPreco extends Filtro{
	
	
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia, int tipoFrete) {
		List<Transportadora> result = filtraPeloTipo(transportadoras, tipoFrete);
		
		double minPreco = result.stream()
				.mapToDouble( t -> t.getFreteByTipo(tipoFrete).getPrecoFinal(distancia))
				.min().getAsDouble();
		
		result.retainAll(
			result.stream()
				.filter( t -> t.getFreteByTipo(tipoFrete).getPrecoFinal(distancia) == minPreco)
				.collect(Collectors.toList())
		);

		
		getProximoFiltro().ifPresent(
			filtro -> result.retainAll(filtro.filtrar(result, distancia, tipoFrete))
		);
		
		return result;
	}

	@Override
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia) {
		double menorPreco = transportadoras.stream()
				.mapToDouble( t -> {
					return t.getFretes().stream()
							.mapToDouble(f -> f.getPrecoFinal(distancia))
							.min().getAsDouble();
				})
				.min().getAsDouble();
		
		List<Transportadora> result = transportadoras.stream()
				.filter( t -> {
					return t.getFretes().stream()
							.mapToDouble(f -> f.getPrecoFinal(distancia))
							.min().getAsDouble() == menorPreco;
				})
				.collect(Collectors.toList());
		
		
		getProximoFiltro().ifPresent(
			filtro -> result.retainAll(filtro.filtrar(result, distancia))
		);
		
		return result;
	}
	
}

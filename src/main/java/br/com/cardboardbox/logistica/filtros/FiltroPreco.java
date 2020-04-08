package br.com.cardboardbox.logistica.filtros;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public class FiltroPreco extends Filtro{
	
	
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, double distancia, int tipoFrete) {
		List<Transportadora> result = filtraPeloTipo(transportadoras, tipoFrete);

		double minPreco = result.stream()
				.map( t -> t.getFreteByTipo(tipoFrete).getPrecoFinal(distancia))
				.min(Double::compare).get();
		
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
		Optional<Double> menorPreco = transportadoras.stream().map(
					t -> t.getFretes().stream().map(
						frete -> frete.getValorKm()
					).min(Double::compare).get()
				).min(Double::compare);

		if(menorPreco.isPresent()) {
			List<Transportadora> result = transportadoras.stream()
					.filter(
							t -> t.getFretes().stream()
									.map(f -> f.getPrecoFinal(distancia))
									.min(Double::compare).get() == menorPreco.get()
					).collect(Collectors.toList());


			getProximoFiltro().ifPresent(
					filtro -> result.retainAll(filtro.filtrar(result, distancia))
			);

			return result;
		}
		return null;
	}
	
}

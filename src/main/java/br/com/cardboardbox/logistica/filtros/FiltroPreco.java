package br.com.cardboardbox.logistica.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public class FiltroPreco extends Filtro{
	
	
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, int distancia, int tipoFrete) {
		List<Transportadora> result = filtraPeloTipo(transportadoras, tipoFrete);
		
		if(result == null)
			return new ArrayList<Transportadora>();
		
		double minPreco = result.stream()
				.mapToDouble( t -> t.getTransportByType(tipoFrete).getPrecoFinal(distancia))
				.min().getAsDouble();


		//menor preco
		result = result.stream()
				.filter( t -> t.getTransportByType(tipoFrete).getPrecoFinal(distancia) == minPreco)
				.collect(Collectors.toList());
		
		if(hasNext()) {
			result = getNext().filtrar(result, distancia, tipoFrete);
		}
		return result;
	}

	@Override
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, int distancia) {
List<Transportadora> result;
		
		double menorPreco = transportadoras.stream()
				.mapToDouble( t -> {
					return t.getFretes().stream()
							.mapToDouble(f -> f.getPrecoFinal(distancia))
							.min().getAsDouble();
				})
				.min().getAsDouble();
		
		result = transportadoras.stream()
				.filter( t -> {
					return t.getFretes().stream()
							.mapToDouble(f -> f.getPrecoFinal(distancia))
							.min().getAsDouble() == menorPreco;
				})
				.collect(Collectors.toList());
		
		if(hasNext()) {
			result = getNext().filtrar(result, distancia);
		}
		return result;
	}
	
}

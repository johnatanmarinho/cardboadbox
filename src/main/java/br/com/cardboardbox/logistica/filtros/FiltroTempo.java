package br.com.cardboardbox.logistica.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cardboardbox.logistica.beans.Transportadora;


public class FiltroTempo extends Filtro{

	@Override
	public List<Transportadora> filtrar(List<Transportadora> transportadoras, int distancia, int tipoFrete) {
		List<Transportadora> result = filtraPeloTipo(transportadoras, tipoFrete);

		if(result == null)
			return new ArrayList<Transportadora>();
		
		int menorTempo = result.stream()
				.mapToInt( t -> t.getTransportByType(tipoFrete).getTempoEntrega(distancia))
				.min().getAsInt();
				
		result = result.stream()
				.filter( t -> t.getTransportByType(tipoFrete).getTempoEntrega(distancia) == menorTempo)
				.collect(Collectors.toList());
		
		if(hasNext()) {
			result = getNext().filtrar(result, distancia, tipoFrete);
		}
		return result;
	}

	public List<Transportadora> filtrar(List<Transportadora> transportadoras,  int distancia) {
		List<Transportadora> result;
		
		int menorTempo = transportadoras.stream()
				.mapToInt( t -> {
					return t.getFretes().stream()
							.mapToInt(f -> f.getTempoEntrega(distancia))
							.min().getAsInt();
				})
				.min().getAsInt();
		
		result = transportadoras.stream()
				.filter( t -> {
					return t.getFretes().stream()
							.mapToInt(f -> f.getTempoEntrega(distancia))
							.min().getAsInt() == menorTempo;
				})
				.collect(Collectors.toList());
		
		if(hasNext()) {
			result = getNext().filtrar(result, distancia);
		}
		return result;
	}

	

}

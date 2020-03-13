package br.com.cardboardbox.logistica;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.cardboardbox.logistica.beans.Frete;
import br.com.cardboardbox.logistica.beans.Rota;
import br.com.cardboardbox.logistica.beans.Transportadora;
import br.com.cardboardbox.logistica.filtros.FiltroPreco;
import br.com.cardboardbox.logistica.filtros.FiltroTempo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TransportadoraTest {
	
	
	@Test
	public void testaFiltros() {

		List<Transportadora> tLista = new ArrayList<Transportadora>();
		
		Transportadora t1 = new Transportadora("transportadora 1");
		t1.addTransport( Frete.TERRESTRE , 50.0, 60);
		tLista.add(t1);
		
		Transportadora t2 = new Transportadora("transportadora 2");
		t2.addTransport( Frete.AEREO , 200, 30);
		t2.addTransport( Frete.TERRESTRE , 75, 59);
		tLista.add(t2);
		
		Transportadora t3 = new Transportadora("transportadora 3");
		t3.addTransport( Frete.AEREO , 180, 33);
		t3.addTransport( Frete.TERRESTRE , 55.0, 65);
		tLista.add(t3);
		
		Transportadora t4 = new Transportadora("transportadora 4");
		t4.addTransport( Frete.AEREO , 175.0, 30);
		tLista.add(t4);
		

		Transportadora t5 = new Transportadora("transportadora 5");
		t5.addTransport( Frete.AEREO , 175.0, 30);
		
		//rotas a serem testadas
		Rota rota1 = new Rota("sao paulo", "manaus");
		rota1.setDistancia(3875);
		
		Rota rota2 = new Rota("florianopolis", "campinas");
		rota2.setDistancia(762);
		
		Rota rota3 = new Rota("salvador", "belém");
		rota3.setDistancia(2018);
		
		Rota rota4 = new Rota("Sao paulo", "assuncao");
		rota4.setDistancia(1350);
		
		Rota rota5 = new Rota("salvador", "brasilia");
		rota5.setDistancia(1449);
		

		Transportadora[] menorPrecoTerrestreExpected = {
			t1
		};
		
		Transportadora[] menorPrecoAereoExpected = {
			t4
		};
		
		Transportadora[] menorTempoExpected = {
				t2
		};
		Transportadora[] menorTempoTerrestreExpected = {
				t2
		};
		
		Transportadora[] menorPrecoEmpate = {
				t4,
				t5
		};
		
		
		FiltroPreco filtro = new FiltroPreco();
		filtro.setProximoFiltro(new FiltroTempo());
		
		FiltroTempo filtroTempo = new FiltroTempo();
		filtroTempo.setProximoFiltro(new FiltroPreco());
		
		List<Transportadora> menorPreco = filtro.filtrar(tLista, rota1.getDistancia(), Frete.AEREO);
		assertArrayEquals(menorPrecoAereoExpected, menorPreco.toArray());
		
		menorPreco = filtro.filtrar(tLista, rota2.getDistancia(), Frete.TERRESTRE);
		assertArrayEquals(menorPrecoTerrestreExpected, menorPreco.toArray());
		
		List<Transportadora> menorTempo = filtroTempo.filtrar(tLista, rota3.getDistancia());
		assertArrayEquals(menorTempoExpected, menorTempo.toArray());
		
		menorTempo = filtroTempo.filtrar(tLista, rota4.getDistancia());
		assertArrayEquals(menorTempoExpected, menorTempo.toArray());
		
		menorTempo = filtroTempo.filtrar(tLista, rota5.getDistancia(), Frete.TERRESTRE);
		assertArrayEquals(menorTempoTerrestreExpected, menorTempo.toArray());
		
		
		//adicionando transportadora com valor igual
		tLista.add(t5);
		menorPreco = filtro.filtrar(tLista, rota1.getDistancia(), Frete.AEREO);
		
		
		assertArrayEquals(menorPrecoEmpate, menorPreco.toArray());
	}

}

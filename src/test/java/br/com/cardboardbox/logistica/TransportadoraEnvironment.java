package br.com.cardboardbox.logistica;

import br.com.cardboardbox.logistica.beans.Frete;
import br.com.cardboardbox.logistica.beans.Transportadora;

import java.util.ArrayList;
import java.util.List;

public class TransportadoraEnvironment {

    public static List<Transportadora> createListOfTransportadoras(){
        List<Transportadora> tLista = new ArrayList<>();

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

        return tLista;
    }
}

package br.com.cardboardbox.logistica.controllers;

import br.com.cardboardbox.logistica.TransportadoraEnvironment;
import br.com.cardboardbox.logistica.beans.Frete;
import br.com.cardboardbox.logistica.beans.Rota;
import br.com.cardboardbox.logistica.beans.Transportadora;
import br.com.cardboardbox.logistica.beans.Transportadoras;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;

class TransportadorasControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransportadorasController controller;

    @Mock
    private Transportadoras transportadoras;


    @BeforeEach
    public void setup(){
        System.out.println("before each");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }
    @Test
    void teste() {
        List<Transportadora> lista = TransportadoraEnvironment.createListOfTransportadoras();
        when(transportadoras.getTransportadoras()).thenReturn(lista);

        List<Transportadora> result = transportadoras.getTransportadoras();

        assertThat(result).isNotNull();
    }

    @Test
    void getMelhorPrecoAereo() {

        List<Transportadora> lista = TransportadoraEnvironment.createListOfTransportadoras();

        Rota rota1 = new Rota("sao paulo", "manaus", Frete.AEREO);
        rota1.setDistancia(3875);

        when(transportadoras.getTransportadoras()).thenReturn(lista);
        controller.getMelhorPreco(rota1);
        given()
                .standaloneSetup(controller)
                .contentType(ContentType.JSON)
                .body(rota1)
                .post("transportadoras/melhorPreco")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body("[0].nome", equalTo(lista.get(3).getNome()));

    }

    @Test
    void getMelhorPrecoTerrestre() {

        List<Transportadora> lista = TransportadoraEnvironment.createListOfTransportadoras();

        Rota rota1 = new Rota("sao paulo", "manaus", Frete.TERRESTRE);
        rota1.setDistancia(3875);

        when(transportadoras.getTransportadoras()).thenReturn(lista);
        controller.getMelhorPreco(rota1);
        given()
                .standaloneSetup(controller)
                .contentType(ContentType.JSON)
                .body(rota1)
                .post("transportadoras/melhorPreco")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body("[0].nome", equalTo(lista.get(0).getNome()));

    }

    @Test
    void getMelhorTempoAereo() {
        List<Transportadora> lista = TransportadoraEnvironment.createListOfTransportadoras();

        Rota rota1 = new Rota("sao paulo", "manaus", Frete.AEREO);
        rota1.setDistancia(3875);

        when(transportadoras.getTransportadoras()).thenReturn(lista);
        controller.getMelhorPreco(rota1);
        given()
                .standaloneSetup(controller)
                .contentType(ContentType.JSON)
                .body(rota1)
                .post("transportadoras/melhorTempo")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body("[0].nome", equalTo(lista.get(3).getNome()));
    }

    @Test
    void getMelhorTempoTerreste() {
        List<Transportadora> lista = TransportadoraEnvironment.createListOfTransportadoras();

        Rota rota1 = new Rota("sao paulo", "manaus", Frete.TERRESTRE);
        rota1.setDistancia(3875);

        when(transportadoras.getTransportadoras()).thenReturn(lista);
        controller.getMelhorPreco(rota1);
        given()
                .standaloneSetup(controller)
                .contentType(ContentType.JSON)
                .body(rota1)
                .post("transportadoras/melhorTempo")
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body("[0].nome", equalTo(lista.get(1).getNome()));
    }
}
package com.itau.operacaobancaria.steps;

import com.itau.operacaobancaria.config.StepsConfig;
import com.itau.operacaobancaria.dto.PostTransferenciaOut;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
@RunWith(Cucumber.class)
public class TransferirValorStepDefinitions extends StepsConfig {

    private Response response;

    @Dado("um id de um cliente {string}")
    public void um_id_de_um_cliente(String idCliente) {
        postTransferenciaIn.setIdCliente(idCliente);
    }

    @Dado("a chave pix destino  {string}")
    public void a_chave_pix_destino(String chavePix) {
        postTransferenciaIn.setCodigoChavePixDestino(chavePix);
    }

    @Dado("o id conta {string}")
    public void e_o_id_conta(String idConta) {
        postTransferenciaIn.setIdConta(idConta);

    }

    @Dado("o valor da transferencia {string}")
    public void e_o_valor_da_transferencia(String valor) {
        postTransferenciaIn.setValorTransferencia(valor);
    }

    @Quando("realizo a chamada para transferencia")
    public void realizo_a_chamada_para_transferencia() {
        RequestSpecification requestSpecification = given().body(postTransferenciaIn).relaxedHTTPSValidation();
        response = requestSpecification.body(requestSpecification)
                .when().post("localhost:8080/v1/transferencia/transferir").thenReturn();
    }

    @Entao("deve ser retornado código http {int}")
    public void deve_ser_retornado_código_http(Integer code) {
        PostTransferenciaOut postTransferenciaOut = response
                .then().extract().as(PostTransferenciaOut.class);

        Assert.assertNotNull(postTransferenciaOut);
    }
}

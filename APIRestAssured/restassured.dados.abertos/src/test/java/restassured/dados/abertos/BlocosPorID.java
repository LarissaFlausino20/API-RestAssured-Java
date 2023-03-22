package restassured.dados.abertos;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BlocosPorID {

	int code;// Criamos a variável global

        // Anotacão do JUNIT para executar o teste
	public void testPesquisareBlocos() {

		// Acessando a API atraves do metodo get da class RestAssured e guardando no
		// response da class Response
		Response response = RestAssured   .get("https://dadosabertos.camara.leg.br/api/v2/blocos?ordem=ASC&ordenarPor=nome");

		// Guardando a resposta do status code na variavel code
		int code = response.getStatusCode();

		// Imprimindo no console o valor da variavel code
		System.out.println("O status code retornado é " + code);

		// Comparando o retorno da API com status code informando
		assertEquals(200, code);
	}

	
	public void validarID() {
		Response response = RestAssured.get("https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome");

		String id = response.jsonPath().getString("dados.id[0]").toString();

		System.out.println(id);

	}

	public String consultarID() {
		Response response = RestAssured.get("https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome");

		String id = response.jsonPath().getString("dados.id[0]").toString();

		return id;

	}

	@Test
	public void validarDeputadoPorId() {

		String nomeEsperado = "ABILIO JACQUES BRUNINI MOUMER";
		String id = consultarID();

		Response response = RestAssured.get("https://dadosabertos.camara.leg.br/api/v2/deputados/" + id);

		String nomeCivil = response.jsonPath().getString("dados.nomeCivil").toString();
		assertEquals(nomeCivil, nomeEsperado);

	}

}

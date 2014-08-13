package br.com.mundialinformatica.emailmarketing.locaweb.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;

import br.com.mundialinformatica.emailmarketing.model.Contato;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ConectTest {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36";
	String hostname = "emailmkt15";
	String login = "mundialinformatica1";
	String idListas = "48253";// Cadastros recepção
	String chave = "19cc6d9766bae69a8c9e8cb64101afea";
	String status = "validos";

	@Test
	public void getContatos() throws IOException {
		// http://[HOSTNAME].locaweb.com.br/admin/api/[LOGIN]/contatos/[STATUS]/?chave=[CHAVE]&pagina=[PAGINA]&lista=[ID_LISTA]

		StringBuilder sbURL = new StringBuilder();
		sbURL.append("http://").append(hostname)
				.append(".locaweb.com.br/admin/api/").append(login)
				.append("/contatos/").append(status).append("/?chave=")
				.append(chave).append("&pagina=1");

		Connection cc = Jsoup.connect(sbURL.toString());
		Response response = cc.userAgent(USER_AGENT).timeout(1000)
				.method(Method.GET).ignoreContentType(true).execute();

		GsonBuilder gsonBuilder = new GsonBuilder();

		Gson gson = gsonBuilder.create();
		Type listType = new TypeToken<ArrayList<Contato>>() {
		}.getType();
		List<Contato> contatos = gson.fromJson(response.body(), listType);
		for(Contato contato : contatos){
			System.out.println(contato);
		}

	}

	@Test
	public void sendContatos() throws IOException {
		StringBuilder sbURL = new StringBuilder();
		sbURL.append("http://").append(hostname)
				.append(".locaweb.com.br/admin/api/").append(login)
				.append("/contatos/").append(status).append("/?chave=")
				.append(chave).append("&pagina=1&lista=").append(idListas);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Contato c = new Contato();
		c.setEmail("nome@mundialinfromk.com");
		c.setNome("Eu MEsmo");
		List<Contato> contatos = new ArrayList<>();
		contatos.add(c);
		
		String json = gson.toJson(contatos);
		
		System.out.println(json);
		
		
		Map<String, String> varsMap = new HashMap<String, String>();
		varsMap.put("", json);

		Connection cc = Jsoup.connect(sbURL.toString());
		cc.data(varsMap);
		cc.userAgent(USER_AGENT).timeout(1000).method(Method.POST)
				.ignoreContentType(true).execute();

	}
}

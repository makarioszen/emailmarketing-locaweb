package br.com.mundialinformatica.emailmarketing.locaweb.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.mkz.emailmarketing.api.EmailMktApi;
import br.com.mkz.emailmarketing.api.LocawebApi;
import br.com.mkz.emailmarketing.exceptions.RequestException;
import br.com.mkz.emailmarketing.exceptions.SendException;
import br.com.mkz.emailmarketing.model.Contato;

public class LocawebApiTest {
	private EmailMktApi api = new LocawebApi();

	@Test
	public void sendContato() throws SendException {
		Contato c = new Contato();
		c.setNome("Marco");
		c.setEmail("webmaster.mundial@gmail.com");
		Assert.assertEquals(200, api.sendContato(c));
	}

	@Test
	public void getContatos() throws RequestException {
		List<Contato> contatos = api.getContatosFromList(48253L);
		for (Contato c : contatos) {
			System.out.println(c);
		}
	}

	@Test
	public void corrigeDataCadastro() throws RequestException, SendException {
		List<Contato> contatos = api.getContatosFromList(48253L);
		List<Contato> contatoListSend = new ArrayList<>();
		for (Contato c : contatos) {
			Contato c2 = new Contato();
			c2.setEmail(c.getEmail());
			c2.setNome(c.getNome());
			contatoListSend.add(c2);
		}
		
		for (Contato c : contatoListSend) {
			api.sendContato(c);
		}
		

	}

}

package br.com.mkz.emailmarketing.api;

import java.util.List;

import br.com.mkz.emailmarketing.exceptions.RequestException;
import br.com.mkz.emailmarketing.exceptions.SendException;
import br.com.mkz.emailmarketing.model.Contato;

public interface EmailMktApi {
	public int sendContato(Contato contato) throws SendException;

	public List<Contato> getContatosFromList(long idList)
			throws RequestException;
}

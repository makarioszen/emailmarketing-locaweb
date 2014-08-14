package br.com.mkz.emailmarketing.controller;

import br.com.mkz.emailmarketing.api.EmailMktApi;
import br.com.mkz.emailmarketing.api.LocawebApi;

public class ApiProducer {

	public EmailMktApi getApi() {
		return new LocawebApi();
	}
}

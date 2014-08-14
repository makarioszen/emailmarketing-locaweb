package br.com.mkz.emailmarketing.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.mkz.emailmarketing.api.EmailMktApi;
import br.com.mkz.emailmarketing.exceptions.SendException;
import br.com.mkz.emailmarketing.model.Contato;

@Controller
public class IndexController {

	private final Result result;
	private final EmailMktApi api;

	/**
	 * @deprecated CDI eyes only
	 */
	public IndexController() {
		this(null, null);
	}

	@Inject
	public IndexController(Result result, EmailMktApi api) {
		this.result = result;
		this.api = api;
	}

	@Get("/")
	public void index() {
	}

	@Post("/")
	public void sendContato(Contato contato) {
		try {
			System.out.println(contato);
			int res = api.sendContato(contato);
			System.out.println(res);
			result.include("variables",res);
			result.forwardTo(this).index();
		} catch (SendException e) {
			e.printStackTrace();
			result.forwardTo(this).index();
		}

	}
}
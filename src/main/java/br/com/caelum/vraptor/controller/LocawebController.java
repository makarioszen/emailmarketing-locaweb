package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.mundialinformatica.emailmarketing.model.Contato;

@Controller
public class LocawebController {

	private final Result result;

	/**
	 * @deprecated CDI eyes only
	 */
	public LocawebController() {
		this(null);
	}

	@Inject
	public LocawebController(Result result) {
		this.result = result;
	}

	@Post("/include-contact")
	public void includeContact(Contato contato) {
		

		
		
		
		
		
		result.forwardTo(IndexController.class).index();
	}
}
package br.com.mkz.emailmarketing.model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Contato {
	private String nome;
	private String email;
	private String datacadastro;

	public Contato() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		datacadastro = sdf.format(GregorianCalendar.getInstance().getTime());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDatacadastro() {
		return datacadastro;
	}

	

	@Override
	public String toString() {
		return "Contato [" + (nome != null ? "nome=" + nome + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (datacadastro != null ? "datacadastro=" + datacadastro : "")
				+ "]";
	}

}

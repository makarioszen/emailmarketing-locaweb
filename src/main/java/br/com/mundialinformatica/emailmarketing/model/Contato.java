package br.com.mundialinformatica.emailmarketing.model;

public class Contato {
	private String nome;
	private String email;

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

	@Override
	public String toString() {
		return "Contato [" + (nome != null ? "nome=" + nome + ", " : "")
				+ (email != null ? "email=" + email : "") + "]";
	}

}

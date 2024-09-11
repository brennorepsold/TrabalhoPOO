package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	private long telefone;

	public Pessoa(String nome, String email, long telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public Pessoa(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public Pessoa(String nome, long telefone) {
		this.nome = nome;
		this.telefone = telefone;
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

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public abstract String getCadastroRF();

	@Override
	public String toString() {
		return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + "\n";
	}
}

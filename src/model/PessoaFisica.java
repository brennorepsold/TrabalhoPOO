package model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long cpf;

	public PessoaFisica(String nome, String email, long telefone, long cpf) {
		super(nome, email, telefone);
		this.cpf = cpf;
	}
	
	public PessoaFisica(String nome, String email, long cpf) {
		super(nome, email);
		this.cpf = cpf;
	}
	
	public PessoaFisica(String nome, long telefone, long cpf) {
		super(nome, telefone);
		this.cpf = cpf;
	}

	public long getCpf() {
		return cpf;
	}

	@Override
	public long getCadastroRF() {
		return getCpf();
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + ", CPF: " + getCpf() + "\n";
	}
	
	

}

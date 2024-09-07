package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long cnpj;
	private PessoaFisica preposto;

	public PessoaJuridica(String nome, String email, long telefone, long cnpj, PessoaFisica preposto) {
		super(nome, email, telefone);
		this.cnpj = cnpj;
		this.preposto = preposto;
	}

	public PessoaJuridica(String nome, String email, long cnpj, PessoaFisica preposto) {
		super(nome, email);
		this.cnpj = cnpj;
		this.preposto = preposto;
	}

	public PessoaJuridica(String nome, long telefone, long cnpj, PessoaFisica preposto) {
		super(nome, telefone);
		this.cnpj = cnpj;
		this.preposto = preposto;
	}

	public long getCnpj() {
		return cnpj;
	}

	public PessoaFisica getPreposto() {
		return preposto;
	}

	public void setPreposto(PessoaFisica preposto) {
		this.preposto = preposto;
	}

	@Override
	public long getCadastroRF() {
		return getCnpj();
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + ", CNPJ: " + getCnpj() + ", Nome Preposto: " + getPreposto().getNome() + "\n";
	}

}

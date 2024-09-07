package model;

import java.io.Serializable;

public class Advogado extends PessoaFisica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long registro;

	public Advogado(String nome, String email, long telefone, long cpf, long registro) {
		super(nome, email, telefone, cpf);
		this.registro = registro;
	}
	
	public Advogado(String nome, long telefone, long cpf, long registro) {
		super(nome, telefone, cpf);
		this.registro = registro;
	}
	
	public Advogado(String nome, String email, long cpf, long registro) {
		super(nome, email, cpf);
		this.registro = registro;
	}

	public long getRegistro() {
		return registro;
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + ", CPF: " + getCpf() + ", Registro: " + getRegistro() + "\n";
	}
	
	

}

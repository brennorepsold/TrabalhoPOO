package model;

public class PessoaFisica extends Pessoa {
	private final long cpf;

	public PessoaFisica(String nome, String email, long telefone, long cpf) {
		super(nome, email, telefone);
		this.cpf = cpf;
	}

	public long getCpf() {
		return cpf;
	}

	@Override
	public long getCadastroRF() {
		return 0;
	}

}

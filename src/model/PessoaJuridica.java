package model;

public class PessoaJuridica extends Pessoa {
	private final long cnpj;

	public PessoaJuridica(String nome, String email, long telefone, long cnpj) {
		super(nome, email, telefone);
		this.cnpj = cnpj;
	}

	public long getCnpj() {
		return cnpj;
	}

	@Override
	public long getCadastroRF() {
		// TODO Auto-generated method stub
		return 0;
	}

}

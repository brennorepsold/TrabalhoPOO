package model;

public class PessoaJuridica extends Pessoa {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Dados Pessoa Juridica";
	}

}

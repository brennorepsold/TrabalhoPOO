package model;

public class Advogado extends PessoaFisica {
	private final long registro;

	public Advogado(String nome, String email, long telefone, long cpf, long registro) {
		super(nome, email, telefone, cpf);
		this.registro = registro;
	}

	public long getRegistro() {
		return registro;
	}

}

package model;

public class Advogado extends PessoaFisica {
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
		return "Dados de contato advogado";
	}
	
	

}

package model;

public class Cliente {
	private final Pessoa pessoa;
	private List<Processo> processos = new ArrayList<>();

	public Cliente(Pessoa pessoa, List<Processo> processos) {
		this.pessoa = pessoa;
		this.processos = processos;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

}

package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente {
	private final Pessoa pessoa;
	private List<Processo> processos = new ArrayList<>();

	public Cliente(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void addProcesso(long numero, Date dataAbertura, EFaseProcesso fase, Cliente cliente, Pessoa parteContraria,
			Tribunal tribunal, IConta conta) {
		processos.add(new Processo(numero, dataAbertura, fase, cliente, parteContraria, tribunal, conta));
	}

}

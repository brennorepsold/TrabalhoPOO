package model;

import java.util.ArrayList;
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

	public List<Processo> getProcessos() {
		return processos;
	}

	public void addProcesso(Processo processo) {
		processos.add(processo);
	}

	public void removeProcesso(Processo processo) {
		processos.remove(processo);
	}

	public StringBuilder getExtratoContas() {
		StringBuilder sb = new StringBuilder();
		for (Processo processo : processos) {
			sb.append(processo.getExtratoConta());
		}
		return sb;
	}

	public double getSaldoContas() {
		double somatorio = 0;
		for (Processo processo : processos) {
			somatorio += processo.getConta().getSaldoConta();
		}
		return somatorio;
	}

	@Override
	public String toString() {
		return "Processos associados a um cliente";
	}

}

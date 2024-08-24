package model;

import java.util.Date;

public class Despesas {
	private final Conta conta;
	private final Date data;
	private final String descricao;
	private final double valor;

	public Despesas(Conta conta, Date data, String descricao, double valor) {
		this.conta = conta;
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Conta getConta() {
		return conta;
	}

	public Date getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

}

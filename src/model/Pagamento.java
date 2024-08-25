package model;

import java.util.Date;

public class Pagamento {
	private final EFormaPagamento forma;
	private final Date data;
	private final double valor;

	public Pagamento(EFormaPagamento forma, Date data, double valor) {
		this.forma = forma;
		this.data = data;
		this.valor = valor;
	}

	public EFormaPagamento getForma() {
		return forma;
	}

	public Date getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

}

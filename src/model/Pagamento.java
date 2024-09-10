package model;

import java.io.Serializable;
import java.util.Date;

public class Pagamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final EFormaPagamento forma;
	private final Date data;
	private final double valor;

	public Pagamento(EFormaPagamento forma, double valor) {
		this.forma = forma;
		this.data = new Date();
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

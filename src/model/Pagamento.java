package model;

import java.util.Date;

public class Pagamento {
	private EFormaPagamento forma;
	private Date data;
	private double valor;

	public Pagamento(EFormaPagamento forma, Date data, double valor) {
		this.forma = forma;
		this.data = data;
		this.valor = valor;
	}

	public EFormaPagamento getForma() {
		return forma;
	}

	public void setForma(EFormaPagamento forma) {
		this.forma = forma;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}

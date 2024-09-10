package model;

import java.io.Serializable;
import java.util.Date;

public class Despesa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Date data;
	private final String descricao;
	private final double valor;

	public Despesa(String descricao, double valor) {
		this.data = new Date();
		this.descricao = descricao;
		this.valor = valor;
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

	@Override
	public String toString() {
		return "Despesa [data=" + data + ", descricao=" + descricao + ", valor=" + valor + "]";
	}

}

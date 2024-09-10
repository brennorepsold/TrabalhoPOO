package model;

import java.io.Serializable;
import java.util.Date;

public class Audiencia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Date data;
	private final String recomendacao;

	private final Advogado advogado;

	public Audiencia(String recomendacao, Advogado advogado) {
		this.data = new Date();// trocar depois pela entrada
		this.recomendacao = recomendacao;
		this.advogado = advogado;
	}

	public Date getData() {
		return data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	@Override
	public String toString() {
		return "Caracterisicas da Audiencia";
	}

}

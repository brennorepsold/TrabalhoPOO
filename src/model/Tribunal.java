package model;

import java.io.Serializable;

public class Tribunal implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String sigla;
	private final String descricao;
	private final String secao;

	public Tribunal(String sigla, String descricao, String secao) {
		this.sigla = sigla;
		this.descricao = descricao;
		this.secao = secao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSecao() {
		return secao;
	}

	@Override
	public String toString() {
		 return sigla + " - " + descricao;
	}

}

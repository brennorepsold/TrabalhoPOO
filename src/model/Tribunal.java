package model;

import java.io.Serializable;

import exception.TribunalException;

public class Tribunal implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String sigla;
	private final String descricao;
	private final String secao;

	public Tribunal(String sigla, String descricao, String secao) throws TribunalException {
		if (sigla == null || sigla.equals(""))
			throw new TribunalException("Sigla deve ser preenchida");
		
		if (descricao == null || descricao.equals(""))
			throw new TribunalException("Nome deve ser preenchida");
		
		if (secao == null || secao.equals(""))
			throw new TribunalException("Seção deve ser preenchida");
		
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

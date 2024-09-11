package model;

import java.io.Serializable;
import java.util.Date;

import exception.AudienciaException;

public class Audiencia implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String recomendacao;
	private final Advogado advogado;
	private final Date data;

	public Audiencia(String recomendacao, Advogado advogado, Date data) throws AudienciaException {
		if (recomendacao == null || recomendacao.trim().isEmpty()) {
			throw new AudienciaException("Recomendação da audiência não pode ser vazia.");
		}
		if (advogado == null) {
			throw new AudienciaException("Advogado não pode ser nulo.");
		}
		this.recomendacao = recomendacao;
		this.advogado = advogado;
		this.data = data;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public Date getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Audiência [recomendação=" + recomendacao + ", advogado=" + advogado.getNome() + "]";
	}
}

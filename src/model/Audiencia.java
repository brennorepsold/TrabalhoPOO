package model;

import java.io.Serializable;
import exception.AudienciaException;

public class Audiencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String recomendacao;
    private final Advogado advogado;

    public Audiencia(String recomendacao, Advogado advogado) throws AudienciaException {
        if (recomendacao == null || recomendacao.trim().isEmpty()) {
            throw new AudienciaException("Recomendação da audiência não pode ser vazia.");
        }
        if (advogado == null) {
            throw new AudienciaException("Advogado não pode ser nulo.");
        }
        this.recomendacao = recomendacao;
        this.advogado = advogado;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    @Override
    public String toString() {
        return "Audiência [recomendação=" + recomendacao + ", advogado=" + advogado.getNome() + "]";
    }
}

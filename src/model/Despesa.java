package model;

import java.io.Serializable;
import java.util.Date;
import exception.DespesaException;

public class Despesa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Date data;
    private final String descricao;
    private final double valor;

    public Despesa(String descricao, double valor) throws DespesaException {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new DespesaException("Descrição da despesa não pode ser vazia.");
        }
        if (valor <= 0) {
            throw new DespesaException("Valor da despesa deve ser maior que zero.");
        }
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

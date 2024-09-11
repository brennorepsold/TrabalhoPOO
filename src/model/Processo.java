package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.AudienciaException;
import exception.ProcessoException;
import util.Verificacoes;

public class Processo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final long numero;
    private final Date dataAbertura;
    private EFaseProcesso fase;

    private final Cliente cliente;
    private final Pessoa parteContraria;
    private Tribunal tribunal;
    private final IConta conta;

    private List<Audiencia> audiencias = new ArrayList<>();

    public Processo(long numero, Cliente cliente, Pessoa parteContraria, Tribunal tribunal) throws ProcessoException {
        if (!Verificacoes.validarNumeroProcesso(numero)) {
            throw new ProcessoException("Número do processo inválido.");
        }

        if (cliente == null) {
            throw new ProcessoException("Cliente não pode ser nulo.");
        }

        if (parteContraria == null) {
            throw new ProcessoException("Parte contrária não pode ser nula.");
        }

        if (tribunal == null) {
            throw new ProcessoException("Tribunal não pode ser nulo.");
        }

        this.numero = numero;
        this.dataAbertura = new Date();
        this.cliente = cliente;
        this.parteContraria = parteContraria;
        this.tribunal = tribunal;

        this.fase = EFaseProcesso.INICIAL;
        this.conta = new Conta();
    }

    public EFaseProcesso getFase() {
        return fase;
    }

    public void setFase(EFaseProcesso fase) {
        this.fase = fase;
    }

    public long getNumero() {
        return numero;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pessoa getParteContraria() {
        return parteContraria;
    }

    public Tribunal getTribunal() {
        return tribunal;
    }

    public void setTribunal(Tribunal tribunal) {
        this.tribunal = tribunal;
    }

    public IConta getConta() {
        return conta;
    }

    public void addAudiencia(String recomendacao, Advogado advogado) throws AudienciaException {
        if (!Verificacoes.verificarCamposPreenchidos(recomendacao, "Recomendação")) {
            throw new AudienciaException("Recomendação da audiência deve ser preenchida.");
        }

        if (advogado == null) {
            throw new AudienciaException("Advogado não pode ser nulo.");
        }

        audiencias.add(new Audiencia(recomendacao, advogado));
    }

    public double getTotalCustas() {
        return this.conta.getTotalDespesas();
    }

    public StringBuilder getExtratoConta() {
        return this.conta.getExtrato();
    }

    public StringBuilder getAudiencias() {
        StringBuilder sb = new StringBuilder();
        for (Audiencia audiencia : audiencias) {
            sb.append(audiencia);
        }
        return sb;
    }

    @Override
    public String toString() {
        return "Processo [numero=" + numero + ", dataAbertura=" + dataAbertura + ", fase=" + fase + ", cliente="
                + cliente + ", parteContraria=" + parteContraria + ", tribunal=" + tribunal + ", conta=" + conta
                + ", audiencias=" + audiencias + "]";
    }
}

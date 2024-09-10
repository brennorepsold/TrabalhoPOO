package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Processo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long numero;
	private final Date dataAbertura;
	private EFaseProcesso fase;

	private final Cliente cliente;
	private final Pessoa parteContraria;
	private Tribunal tribunal;
	private final IConta conta;

	private List<Audiencia> audiencias = new ArrayList<>();

	public Processo(long numero, Cliente cliente, Pessoa parteContraria, Tribunal tribunal) {
		this.numero = numero;
		//Depois trocar para entrada de data
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

	public void addAudiencia(String recomendacao, Advogado advogado) {
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

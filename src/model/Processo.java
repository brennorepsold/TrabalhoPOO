package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Processo {
	private final long numero;
	private final Date dataAbertura;
	private EFaseProcesso fase;

	private final Cliente cliente;
	private final Pessoa parteContraria;
	private final Tribunal tribunal;
	private final IConta conta;

	private List<Audiencia> audiencias = new ArrayList<>();

	public Processo(long numero, Date dataAbertura, EFaseProcesso fase, Cliente cliente, Pessoa parteContraria,
			Tribunal tribunal, IConta conta) {
		this.numero = numero;
		this.dataAbertura = dataAbertura;
		this.fase = fase;
		this.cliente = cliente;
		this.parteContraria = parteContraria;
		this.tribunal = tribunal;
		this.conta = conta;
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

	public IConta getConta() {
		return conta;
	}
	
	public void addAudiencia(Date data, String recomendacao, Advogado advogado) {
		audiencias.add(new Audiencia(data, recomendacao, advogado));
	}

}

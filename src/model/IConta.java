package model;

import java.util.Date;

public interface IConta {
	public void addPagamento(EFormaPagamento forma, Date data, double valor);

	public void addDespesa(Date data, String descricao, double valor);

	public double getTotalPagamentos();

	public double getTotalDespesas();

	public double getSaldoConta();

	public StringBuilder getExtrato();
}

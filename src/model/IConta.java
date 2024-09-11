package model;

import exception.DespesaException;
import exception.PagamentoException;

public interface IConta {

	public void addPagamento(EFormaPagamento forma, double valor) throws PagamentoException;

	public void addDespesa(String descricao, double valor) throws DespesaException;

	public double getTotalPagamentos();

	public double getTotalDespesas();

	public double getSaldoConta();

	public StringBuilder getExtrato();
}

package model;

import java.util.Date;

import exception.DespesaException;
import exception.PagamentoException;

public interface IConta {

	public void addPagamento(EFormaPagamento forma, double valor, Date data) throws PagamentoException;

	public void addDespesa(String descricao, double valor, Date data) throws DespesaException;

	public double getTotalPagamentos();

	public double getTotalDespesas();

	public double getSaldoConta();

	public StringBuilder getExtrato();
}

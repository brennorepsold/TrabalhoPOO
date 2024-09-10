package model;

public interface IConta {

	public void addPagamento(EFormaPagamento forma, double valor);

	public void addDespesa(String descricao, double valor);

	public double getTotalPagamentos();

	public double getTotalDespesas();

	public double getSaldoConta();

	public StringBuilder getExtrato();
}

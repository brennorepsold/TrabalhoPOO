package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements IConta, Serializable {

	private static final long serialVersionUID = 1L;
	private List<Pagamento> pagamentos = new ArrayList<>();
	private List<Despesa> despesas = new ArrayList<>();

	@Override
	public void addPagamento(EFormaPagamento forma, double valor) {
		pagamentos.add(new Pagamento(forma, valor));
	}

	@Override
	public void addDespesa(String descricao, double valor) {
		despesas.add(new Despesa(descricao, valor));

	}

	@Override
	public double getTotalPagamentos() {
		double somatorio = 0;
		for (Pagamento pagamento : pagamentos) {
			somatorio += pagamento.getValor();
		}
		return somatorio;
	}

	@Override
	public double getTotalDespesas() {
		double somatorio = 0;
		for (Despesa despesa : despesas) {
			somatorio += despesa.getValor();
		}
		return somatorio;
	}

	@Override
	public double getSaldoConta() {
		return this.getTotalDespesas() - this.getTotalPagamentos();
	}

	@Override
	public StringBuilder getExtrato() {
		StringBuilder sb = new StringBuilder();

		sb.append("Despesas: " + this.getTotalDespesas());
		sb.append("\n");
		sb.append("Total Pago: " + this.getTotalPagamentos());
		sb.append("\n");
		sb.append("Saldo conta: " + this.getSaldoConta());

		return sb;
	}

}

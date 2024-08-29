package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conta implements IConta {
	private List<Pagamento> pagamentos = new ArrayList<>();
	private List<Despesa> despesas = new ArrayList<>();

	@Override
	public void addPagamento(EFormaPagamento forma, Date data, double valor) {
		pagamentos.add(new Pagamento(forma, data, valor));
	}

	@Override
	public void addDespesa(Date data, String descricao, double valor) {
		despesas.add(new Despesa(data, descricao, valor));

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
		return this.getTotalPagamentos() - this.getTotalDespesas();
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

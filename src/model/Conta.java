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

}

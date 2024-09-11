package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import exception.AudienciaException;
import exception.DespesaException;
import exception.PagamentoException;
import exception.ProcessoException;
import model.Advogado;
import model.Cliente;
import model.EFormaPagamento;
import model.Pessoa;
import model.Processo;
import model.Tribunal;
import util.Verificacoes;

public class ProcessoController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map<Long, Processo> processos;
	private Map<String, Cliente> clientes;
	private Map<String, Pessoa> parteContrarias;

	public ProcessoController() {
		this.processos = new TreeMap<>();
		this.clientes = new TreeMap<>();
		this.parteContrarias = new TreeMap<>();
	}

	public void addProcesso(long numero, String cadastroCliente, String cadastroParteContraria, String siglaTribunal)
			throws ProcessoException {
		TribunalController tribunalController = MainController.getTribunalController();

		if (!Verificacoes.validarNumeroProcesso(numero)) {
			throw new ProcessoException("Número do processo inválido.");
		}

		Cliente cliente = getClientesByCadastro(cadastroCliente);
		Pessoa parteContraria = getParteContrariaByCadastro(cadastroParteContraria);
		Tribunal tribunal = tribunalController.getTribunalBySigla(siglaTribunal);

		if (tribunal == null) {
			throw new ProcessoException("Tribunal não encontrado: " + siglaTribunal);
		}

		try {
			Processo processo = new Processo(numero, cliente, parteContraria, tribunal);
			processos.put(numero, processo);
			cliente.addProcesso(processo);

			MainController.save();
		} catch (Exception e) {
			throw new ProcessoException("Erro ao adicionar processo: " + e.getMessage());
		}
	}

	public void addAudiencia(long numeroProcesso, String recomendacao, Pessoa advogado) throws AudienciaException {
		Processo processo = processos.get(numeroProcesso);

		if (processo == null) {
			throw new AudienciaException("Processo não encontrado: " + numeroProcesso);
		}

		if (!Verificacoes.verificarCamposPreenchidos(recomendacao, "Recomendação")) {
			throw new AudienciaException("Recomendação da audiência deve ser preenchida.");
		}

		try {
			processo.addAudiencia(recomendacao, (Advogado) advogado);
			MainController.save();
		} catch (AudienciaException e) {
			throw e; // Relança a exceção específica
		} catch (Exception e) {
			throw new AudienciaException("Erro ao adicionar audiência: " + e.getMessage());
		}
	}

	public void addPagamento(long numeroProcesso, EFormaPagamento formaPagamento, double valor)
			throws PagamentoException {
		Processo processo = processos.get(numeroProcesso);

		if (processo == null) {
			throw new PagamentoException("Processo não encontrado: " + numeroProcesso);
		}

		if (!Verificacoes.validarValorMonetario(valor)) {
			throw new PagamentoException("Valor de pagamento inválido.");
		}

		try {
			processo.getConta().addPagamento(formaPagamento, valor); // Adiciona o pagamento na conta do processo
			MainController.save();
		} catch (PagamentoException e) {
			throw e; // Relança a exceção específica
		} catch (Exception e) {
			throw new PagamentoException("Erro ao adicionar pagamento: " + e.getMessage());
		}
	}

	public void addDespesa(long numeroProcesso, String descricao, double valor) throws DespesaException {
		Processo processo = processos.get(numeroProcesso);

		if (processo == null) {
			throw new DespesaException("Processo não encontrado: " + numeroProcesso);
		}

		if (!Verificacoes.verificarCamposPreenchidos(descricao, "Descrição")) {
			throw new DespesaException("Descrição da despesa deve ser preenchida.");
		}

		if (!Verificacoes.validarValorMonetario(valor)) {
			throw new DespesaException("Valor de despesa inválido.");
		}

		try {
			processo.getConta().addDespesa(descricao, valor); // Adiciona a despesa na conta do processo
			MainController.save();
		} catch (DespesaException e) {
			throw e; // Relança a exceção específica
		} catch (Exception e) {
			throw new DespesaException("Erro ao adicionar despesa: " + e.getMessage());
		}
	}

	public Set<String> getClientes() {
		return clientes.keySet();
	}

	public Set<Long> getProcessos() {
		return processos.keySet();
	}

	private Cliente getClientesByCadastro(String cadastroCliente) {
		PessoaController pessoaController = MainController.getPessoaController();
		Cliente cliente = clientes.get(cadastroCliente);
		if (cliente == null) {
			Pessoa pessoa = pessoaController.getPessoasByCadastro(cadastroCliente);
			if (pessoa != null) {
				cliente = new Cliente(pessoa);
				clientes.put(cadastroCliente, cliente);
			} else {
				System.out.println("Cliente não encontrado: " + cadastroCliente);
			}
		}
		return cliente;
	}

	private Pessoa getParteContrariaByCadastro(String cadastroParteContraria) {
		PessoaController pessoaController = MainController.getPessoaController();
		Pessoa parteContraria = parteContrarias.get(cadastroParteContraria);
		if (parteContraria == null) {
			parteContraria = pessoaController.getPessoasByCadastro(cadastroParteContraria);
			if (parteContraria != null) {
				parteContrarias.put(cadastroParteContraria, parteContraria);
			} else {
				System.out.println("Parte contrária não encontrada: " + cadastroParteContraria);
			}
		}
		return parteContraria;
	}

	public String getExtratoContaPorProcesso(Long numeroProcesso) {
		Processo processo = processos.get(numeroProcesso);

		if (processo != null) {
			return processo.getExtratoConta().toString(); // Retorna o extrato formatado como string
		} else {
			System.out.println("Processo não encontrado: " + numeroProcesso);
			return "Processo não encontrado.";
		}
	}

	public List<Processo> getProcessosCliente(String cadastroCliente) {
		List<Processo> listaProcessos = new ArrayList<>();

		Cliente cliente = clientes.get(cadastroCliente);

		if (cliente != null) {
			listaProcessos.addAll(cliente.getProcessos());
		} else {
			System.out.println("Cliente não encontrado: " + cadastroCliente);
		}

		return listaProcessos;
	}

	public List<String> getAudienciasPorProcesso(Long numeroProcesso) {
		Processo processo = processos.get(numeroProcesso);
		List<String> audienciasList = new ArrayList<>();

		if (processo != null) {
			StringBuilder sb = processo.getAudiencias(); // Retorna as audiências formatadas
			audienciasList.add(sb.toString());
		} else {
			System.out.println("Processo não encontrado: " + numeroProcesso);
		}

		return audienciasList;
	}

	public String[] getFormasPagamentoArray() {
		EFormaPagamento[] formas = EFormaPagamento.values();
		String[] formasArray = new String[formas.length];

		for (int i = 0; i < formas.length; i++) {
			formasArray[i] = formas[i].name();
		}

		return formasArray;
	}
}

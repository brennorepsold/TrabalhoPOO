package controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import model.EFaseProcesso;
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

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void addProcesso(long numero, String cadastroCliente, String cadastroParteContraria, String siglaTribunal,
			String dataAbertura) throws ProcessoException, ParseException {
		TribunalController tribunalController = MainController.getTribunalController();

		Verificacoes.validarDataCompleta(dataAbertura);
		Date data = sdf.parse(dataAbertura);

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
			Processo processo = new Processo(numero, cliente, parteContraria, tribunal, data);
			processos.put(numero, processo);
			cliente.addProcesso(processo);
			MainController.save();
		} catch (Exception e) {
			throw new ProcessoException("Erro ao adicionar processo: " + e.getMessage());
		}
	}

	public void addAudiencia(long numeroProcesso, String recomendacao, Pessoa advogado, String data)
			throws AudienciaException, ParseException {
		Processo processo = processos.get(numeroProcesso);

		if (processo == null) {
			throw new AudienciaException("Processo não encontrado: " + numeroProcesso);
		}

		if (!Verificacoes.verificarCamposPreenchidos(recomendacao, "Recomendação")) {
			throw new AudienciaException("Recomendação da audiência deve ser preenchida.");
		}

		Verificacoes.validarDataCompleta(data);
		Date dataAudiencia = sdf.parse(data);

		try {
			processo.addAudiencia(recomendacao, (Advogado) advogado, dataAudiencia);
			MainController.save();
		} catch (AudienciaException e) {
			throw e;
		} catch (Exception e) {
			throw new AudienciaException("Erro ao adicionar audiência: " + e.getMessage());
		}
	}

	public void addPagamento(long numeroProcesso, EFormaPagamento formaPagamento, double valor, String data)
			throws PagamentoException, ParseException {
		Processo processo = processos.get(numeroProcesso);

		if (processo == null) {
			throw new PagamentoException("Processo não encontrado: " + numeroProcesso);
		}

		if (!Verificacoes.validarValorMonetario(valor)) {
			throw new PagamentoException("Valor de pagamento inválido.");
		}

		Verificacoes.validarDataCompleta(data);
		Date dataPagamento = sdf.parse(data);

		try {
			processo.getConta().addPagamento(formaPagamento, valor, dataPagamento);
			MainController.save();
		} catch (PagamentoException e) {
			throw e;
		} catch (Exception e) {
			throw new PagamentoException("Erro ao adicionar pagamento: " + e.getMessage());
		}
	}

	public void addDespesa(long numeroProcesso, String descricao, double valor, String data)
			throws DespesaException, ParseException {
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

		Verificacoes.validarDataCompleta(data);
		Date dataDespesa = sdf.parse(data);

		try {
			processo.getConta().addDespesa(descricao, valor, dataDespesa);
			MainController.save();
		} catch (DespesaException e) {
			throw e;
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

	private Cliente getClientesByCadastro(String cadastroCliente) throws ProcessoException {
		PessoaController pessoaController = MainController.getPessoaController();
		Cliente cliente = clientes.get(cadastroCliente);
		if (cliente == null) {
			Pessoa pessoa = pessoaController.getPessoasByCadastro(cadastroCliente);
			if (pessoa != null) {
				cliente = new Cliente(pessoa);
				clientes.put(cadastroCliente, cliente);
			} else {
				throw new ProcessoException("Cliente não encontrado: " + cadastroCliente);
			}
		}
		return cliente;
	}

	private Pessoa getParteContrariaByCadastro(String cadastroParteContraria) throws ProcessoException {
		PessoaController pessoaController = MainController.getPessoaController();
		Pessoa parteContraria = parteContrarias.get(cadastroParteContraria);
		if (parteContraria == null) {
			parteContraria = pessoaController.getPessoasByCadastro(cadastroParteContraria);
			if (parteContraria != null) {
				parteContrarias.put(cadastroParteContraria, parteContraria);
			} else {
				throw new ProcessoException("Parte contrária não encontrada: " + cadastroParteContraria);
			}
		}
		return parteContraria;
	}

	public String getExtratoContaPorProcesso(Long numeroProcesso) throws ProcessoException {
		Processo processo = processos.get(numeroProcesso);
		if (processo != null) {
			return processo.getExtratoConta().toString();
		} else {
			throw new ProcessoException("Processo não encontrado: " + numeroProcesso);
		}
	}

	public void atualizarProcesso(long numeroProcesso, String fase, String siglaTribunal) throws ProcessoException {
		Processo processo = processos.get(numeroProcesso);

		if (processo == null) {
			throw new ProcessoException("Processo não encontrado: " + numeroProcesso);
		}

		TribunalController tribunalController = MainController.getTribunalController();
		Tribunal tribunal = tribunalController.getTribunalBySigla(siglaTribunal);

		if (tribunal == null) {
			throw new ProcessoException("Tribunal não encontrado: " + siglaTribunal);
		}

		try {
			EFaseProcesso novaFase = EFaseProcesso.valueOf(fase);
			processo.setFase(novaFase);
			processo.setTribunal(tribunal);
			MainController.save();
		} catch (IllegalArgumentException e) {
			throw new ProcessoException("Fase do processo inválida: " + fase);
		}
	}

	public List<Processo> getProcessosCliente(String cadastroCliente) throws ProcessoException {
		List<Processo> listaProcessos = new ArrayList<>();
		Cliente cliente = clientes.get(cadastroCliente);
		if (cliente != null) {
			listaProcessos.addAll(cliente.getProcessos());
		} else {
			throw new ProcessoException("Cliente não encontrado: " + cadastroCliente);
		}
		return listaProcessos;
	}

	public List<String> getAudienciasPorProcesso(Long numeroProcesso) throws AudienciaException {
		Processo processo = processos.get(numeroProcesso);
		List<String> audienciasList = new ArrayList<>();
		if (processo != null) {
			StringBuilder sb = processo.getAudiencias();
			audienciasList.add(sb.toString());
		} else {
			throw new AudienciaException("Processo não encontrado: " + numeroProcesso);
		}
		return audienciasList;
	}
	
	public String getExtratoPorCliente(String cadastroCliente) throws ProcessoException {
	    Cliente cliente = clientes.get(cadastroCliente);
	    if (cliente == null) {
	        throw new ProcessoException("Cliente não encontrado: " + cadastroCliente);
	    }
	    StringBuilder extrato = cliente.getExtratoContas();
	    extrato.append("\n\nSaldo total: ").append(cliente.getSaldoContas());
	    return extrato.toString();
	}

	public String[] getFormasPagamentoArray() {
		EFormaPagamento[] formas = EFormaPagamento.values();
		String[] formasArray = new String[formas.length];
		for (int i = 0; i < formas.length; i++) {
			formasArray[i] = formas[i].name();
		}
		return formasArray;
	}

	public String[] getFasesProcessoArray() {
		EFaseProcesso[] fases = EFaseProcesso.values();
		String[] fasesArray = new String[fases.length];
		for (int i = 0; i < fases.length; i++) {
			fasesArray[i] = fases[i].name();
		}
		return fasesArray;
	}
}

package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Cliente;
import model.Pessoa;
import model.Processo;

public class ClienteController implements Serializable {

	private static final long serialVersionUID = -1234567890123456789L;

	private Map<Long, Cliente> clientes; // Mapeamento de clientes pelo seu cadastroRF

	public ClienteController() {
		clientes = new TreeMap<>();
	}

	// Método para adicionar um novo cliente
	public boolean addCliente(Pessoa pessoa) {
		if (!clientes.containsKey(pessoa.getCadastroRF())) { // Verifica se o cliente já existe
			clientes.put(pessoa.getCadastroRF(), new Cliente(pessoa));
			MainController.save();
			return true;
		}
		return false; // Cliente já existe
	}

	// Método para listar clientes
	public Map<Long, Cliente> getClientes() { // Retorna um Map de Clientes
		return clientes;
	}

	// Método para adicionar um processo a um cliente
	public boolean addProcessoToCliente(Long cadastroRFCliente, Processo processo) {
		Cliente cliente = clientes.get(cadastroRFCliente);
		if (cliente != null) {
			cliente.addProcesso(processo); // Adiciona o processo ao cliente
			MainController.save();
			return true;
		}
		return false; // Cliente não encontrado
	}

	// Método para remover um processo de um cliente
	public boolean removeProcessoFromCliente(Long cadastroRFCliente, Processo processo) {
		Cliente cliente = clientes.get(cadastroRFCliente);
		if (cliente != null) {
			cliente.removeProcesso(processo); // Remove o processo do cliente
			MainController.save();
			return true;
		}
		return false; // Cliente ou processo não encontrado
	}

	// Método para obter o extrato de contas de um cliente
	public StringBuilder getExtratoContas(Long cadastroRFCliente) {
		Cliente cliente = clientes.get(cadastroRFCliente);
		if (cliente != null) {
			return cliente.getExtratoContas();
		}
		return new StringBuilder("Cliente não encontrado.");
	}

	// Método para calcular o saldo total das contas de um cliente
	public double calcularSaldoContas(Long cadastroRFCliente) {
		Cliente cliente = clientes.get(cadastroRFCliente);
		if (cliente != null) {
			return cliente.getSaldoContas();
		}
		return 0.0; // Cliente não encontrado
	}
	public List<Long> getClientesComProcessos() {
	    return clientes.entrySet().stream()
	            .filter(entry -> !entry.getValue().getProcessos().isEmpty()) // Filtra clientes que possuem processos
	            .map(Map.Entry::getKey) // Mapeia para o cadastroRF (chave do cliente)
	            .collect(Collectors.toList());
	}

}

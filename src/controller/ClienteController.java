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

    // Método para listar IDs (cadastroRF) dos clientes
    public Map<Long, Cliente> getClientes() { // Retorna o map de clientes
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

    // Método para listar os números dos processos de um cliente específico
    public List<Long> getProcessosDoCliente(Long cadastroRFCliente) {
        Cliente cliente = clientes.get(cadastroRFCliente);
        if (cliente != null) {
            // Supondo que Cliente tenha um método getProcessos() que retorna uma lista de números de processos
            return cliente.getProcessos().stream().map(Processo::getNumero).collect(Collectors.toList());
        }
        return List.of(); // Retorna uma lista vazia se o cliente não for encontrado
    }
}

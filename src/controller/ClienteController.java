package controller;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Cliente;
import model.Pessoa;
import model.Processo;

public class ClienteController implements Serializable {

    private static final long serialVersionUID = -1234567890123456789L;

    private Map<String, Cliente> clientes;

    public ClienteController() {
        clientes = new TreeMap<>();
    }

    // Método para adicionar um novo cliente
    public boolean addCliente(Pessoa pessoa) {
        if (clientes == null) {
            clientes = new TreeMap<>();
        }
        clientes.put(pessoa.getNome(), new Cliente(pessoa)); // insere novo objeto Cliente no map
        MainController.save();
        return true;
    }

    // Método para listar nomes dos clientes
    public Set<String> getClientes() {
        return clientes.keySet(); // retorna lista das chaves do map clientes
    }

    // Método para buscar um cliente pelo nome
    public Cliente getClienteByName(String nome) {
        return clientes.get(nome);
    }

    // Método para adicionar um processo a um cliente
    public boolean addProcessoToCliente(String nomeCliente, Processo processo) {
        Cliente cliente = clientes.get(nomeCliente);
        if (cliente != null) {
            cliente.addProcesso(processo);
            MainController.save();
            return true;
        }
        return false;
    }

    // Método para remover um processo de um cliente
    public boolean removeProcessoFromCliente(String nomeCliente, Processo processo) {
        Cliente cliente = clientes.get(nomeCliente);
        if (cliente != null) {
            cliente.removeProcesso(processo);
            MainController.save();
            return true;
        }
        return false;
    }

    // Método para obter o extrato de contas de um cliente
    public StringBuilder getExtratoContas(String nomeCliente) {
        Cliente cliente = clientes.get(nomeCliente);
        if (cliente != null) {
            return cliente.getExtratoContas();
        }
        return new StringBuilder("Cliente não encontrado.");
    }

    // Método para calcular o saldo total das contas de um cliente
    public double calcularSaldoContas(String nomeCliente) {
        Cliente cliente = clientes.get(nomeCliente);
        if (cliente != null) {
            return cliente.getSaldoContas();
        }
        return 0.0;
    }
}
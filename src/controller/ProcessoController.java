package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import model.Cliente;
import model.Pessoa;
import model.Processo;
import model.Tribunal;

public class ProcessoController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Long, Processo> processos;
    private Map<String, Cliente> clientes;
    private Map<String, Pessoa> parteContrarias;
    private PessoaController pessoaController = MainController.getPessoaController();
    private TribunalController tribunalController = MainController.getTribunalController();

    public ProcessoController() {
        this.processos = new TreeMap<>();
        this.clientes = new TreeMap<>();
        this.parteContrarias = new TreeMap<>();
    }

    // Método para adicionar um processo
    public void addProcesso(long numero, Date dataAbertura, String cadastroCliente, String cadastroParteContraria, String siglaTribunal) {
        // Verifica e cria o Cliente se não existir
        Cliente cliente = getOrCreateCliente(cadastroCliente);

        // Verifica e cria a Parte Contrária se não existir
        Pessoa parteContraria = getOrCreateParteContraria(cadastroParteContraria);

        // Obtém o tribunal pela sigla
        Tribunal tribunal = tribunalController.getTribunalBySigla(siglaTribunal);
        //tratar essa exceção posteriormente
        if (tribunal == null) {
            System.out.println("Tribunal não encontrado: " + siglaTribunal);
            return; // Tribunal não existe, então não prossegue
        }

        // Cria e adiciona o processo ao mapa de processos
        Processo processo = new Processo(numero, dataAbertura, cliente, parteContraria, tribunal);
        processos.put(numero, processo);

        // Adiciona o processo ao cliente
        cliente.addProcesso(processo);

        MainController.save();  // Persiste os dados
    }

    // Método para obter ou criar um cliente a partir de um cadastro
    private Cliente getOrCreateCliente(String cadastroCliente) {
        Cliente cliente = clientes.get(cadastroCliente);
        if (cliente == null) {
            Pessoa pessoa = pessoaController.getPessoasByCadastro(cadastroCliente);
            if (pessoa != null) {
                cliente = new Cliente(pessoa);
                clientes.put(cadastroCliente, cliente); // Adiciona o cliente ao mapa
            } else {
                System.out.println("Cliente não encontrado: " + cadastroCliente);
            }
        }
        return cliente;
    }

    // Método para obter ou criar uma parte contrária a partir de um cadastro
    private Pessoa getOrCreateParteContraria(String cadastroParteContraria) {
        Pessoa parteContraria = parteContrarias.get(cadastroParteContraria);
        if (parteContraria == null) {
            parteContraria = pessoaController.getPessoasByCadastro(cadastroParteContraria);
            if (parteContraria != null) {
                parteContrarias.put(cadastroParteContraria, parteContraria); // Adiciona a parte contrária ao mapa
            } else {
                System.out.println("Parte contrária não encontrada: " + cadastroParteContraria);
            }
        }
        return parteContraria;
    }

    // Outros métodos do ProcessoController...

    public Map<Long, Processo> getProcessos() {
        return processos;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public Map<String, Pessoa> getParteContrarias() {
        return parteContrarias;
    }
}

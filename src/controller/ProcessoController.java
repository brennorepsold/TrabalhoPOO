package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import model.Cliente;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Processo;
import model.Tribunal;

public class ProcessoController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map<Long, Processo> processos;
	private Map<String, Cliente> clientes;
	private Map<String, Pessoa> parteContrarias;
	private PessoaController pessoaController = MainController.getPessoaController();

	public void addProcesso(long numero, Date dataAbertura, Cliente cliente, Pessoa parteContraria, Tribunal tribunal) {
		Processo processo = new Processo(numero, dataAbertura, cliente, parteContraria, tribunal);
		processos.put(numero, processo);
		MainController.save();
	}

	public void addCliente(Pessoa pessoa) {
		Cliente cliente = new Cliente(pessoa);
		clientes.put(pessoa.getCadastroRF(), cliente);
		MainController.save();
	}
/*
	public void addParteContraria(Pessoa pessoa) {
		for (Pessoa x : pessoaController.getPessoasFisicas().values()) {
			if (x.equals(pessoa)) { 
				parteContrarias.put(x.getCadastroRF(), x);
			} else {
				util.Verificacoes.exibirPopup("Erro", "Pessoa Ja cadastrada");
			}
		}
		
		

		MainController.save();
	}
*/
}

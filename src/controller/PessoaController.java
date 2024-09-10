package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Advogado;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class PessoaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, Pessoa> pessoas;
	private Map<String, Pessoa> advogados;

	public PessoaController() {
		this.pessoas = new TreeMap<>();
		this.advogados = new TreeMap<>();
	}

	// Método para adicionar uma pessoa física
	public boolean addPessoaFisica(String nome, String email, long telefone, String cpf) {
		if (pessoas == null) { // Verifica se 'pessoas' está nulo
	        pessoas = new TreeMap<>(); // Inicializa 'pessoas' se estiver nulo
	    }
		
		Pessoa pessoaFisica = new PessoaFisica(nome, email, telefone, cpf);
		pessoas.put(cpf, pessoaFisica);
		MainController.save();
		return true;
	}

	// Método para adicionar uma pessoa jurídica
	public boolean addPessoaJuridica(String nome, String email, long telefone, String cnpj, PessoaFisica pessoa) {
		Pessoa pessoaJuridica = new PessoaJuridica(nome, telefone, cnpj, pessoa);
		pessoas.put(cnpj, pessoaJuridica);
		MainController.save();

		return true;
	}

	// Método para adicionar um advogado
	public boolean addAdvogado(String nome, String email, long telefone, String cpf, long registro) {
		Pessoa advogado = new Advogado(nome, email, telefone, cpf, registro);
		advogados.put(cpf, advogado);
		MainController.save();
		return true;
	}

	// Método para listar todas as pessoas físicas
	public Set<String> getNomesPessoas() {
		return pessoas.keySet();
	}

	public List<String> getNomesPessoasFisicas() {
	    List<String> nomesPessoasFisicas = new ArrayList<>(); // Usando ArrayList para evitar duplicatas
	    for (Pessoa pessoa : pessoas.values()) { // Itera sobre os valores do mapa de pessoas
	        if (pessoa instanceof PessoaFisica) { // Verifica se é uma instância de PessoaFisica
	            nomesPessoasFisicas.add(pessoa.getNome()); // Adiciona o nome à lista
	        }
	    }
	    return nomesPessoasFisicas; // Retorna a lista de nomes de pessoas físicas
	}


	// Método para listar todos os advogados
	public Set<String> getNomesAdvogados() {
		return advogados.keySet();
	}

	// Método para obter uma lista de nomes das pessoas físicas
	public List<Pessoa> getPessoasFisicas() {
		List<Pessoa> lista = new ArrayList<>();
		for (Pessoa t : pessoas.values()) { // Itera sobre os valores do mapa
			if (t instanceof PessoaFisica) {
				lista.add(new PessoaFisica(t.getNome(), t.getEmail(), t.getTelefone(), t.getCadastroRF()));
			}
		}
		return lista;
	}

	public List<Pessoa> getPessoasJuridicas() {
		List<Pessoa> lista = new ArrayList<>();
		for (Pessoa t : pessoas.values()) { // Itera sobre os valores do mapa
			if (t instanceof PessoaJuridica) {
				PessoaJuridica pj = (PessoaJuridica) t;
				lista.add(new PessoaJuridica(pj.getNome(), pj.getEmail(), pj.getTelefone(), pj.getCadastroRF(),
						pj.getPreposto()));
			}
		}
		return lista;
	}

	public List<Advogado> getAdvogados() {
		List<Advogado> lista = new ArrayList<>();
		for (Pessoa t : advogados.values()) { // Itera sobre os valores do mapa
			Advogado a = (Advogado) t;
			lista.add(new Advogado(a.getNome(), a.getEmail(), a.getTelefone(), a.getCpf(), a.getRegistro()));
			
		}
		return lista;
	}
}

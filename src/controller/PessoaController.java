package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.Advogado;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class PessoaController implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private Map<Long, Pessoa> pessoasFisicas;
    private Map<Long, Pessoa> pessoasJuridicas;
    private Map<Long, Pessoa> advogados;

    public PessoaController() {
        this.pessoasFisicas = new TreeMap<>();
        this.pessoasJuridicas = new TreeMap<>();
        this.advogados = new TreeMap<>();
    }

    // Método para adicionar uma pessoa física
    public boolean addPessoaFisica(String nome, String email, long telefone, long cpf) {
        Pessoa pessoaFisica = new PessoaFisica(nome, email, telefone, cpf);
        pessoasFisicas.put(cpf, pessoaFisica);
        MainController.save();
        return true;
    }

    // Método para adicionar uma pessoa jurídica
    public boolean addPessoaJuridica(String nome, String email, long telefone, long cnpj, PessoaFisica pessoa) {
        Pessoa pessoaJuridica = new PessoaJuridica(nome, telefone, cnpj, pessoa);
        pessoasJuridicas.put(cnpj, pessoaJuridica);
        MainController.save();
        
        return true;
    }

    // Método para adicionar um advogado
    public boolean addAdvogado(String nome, String email, long telefone, long cpf, long registro) {
        Pessoa advogado = new Advogado(nome, email, telefone, cpf, registro);
        advogados.put(cpf, advogado);
        MainController.save();
        return true;
    }

    // Método para listar todas as pessoas físicas
    public Map<Long, Pessoa> getPessoasFisicas() {
        return pessoasFisicas;
    }

    // Método para listar todas as pessoas jurídicas
    public Map<Long, Pessoa> getPessoasJuridicas() {
        return pessoasJuridicas;
    }

    // Método para listar todos os advogados
    public Map<Long, Pessoa> getAdvogados() {
        return advogados;
    }
    
    // Método para obter uma lista de nomes das pessoas físicas
    public List<String> getNomesPessoasFisicas() {
        List<String> nomes = new ArrayList<>();
        for (Pessoa pessoa : pessoasFisicas.values()) { // Itera sobre os valores do mapa
            if (pessoa instanceof PessoaFisica) { // Verifica se é uma instância de PessoaFisica
                nomes.add(((PessoaFisica) pessoa).getNome()); // Faz o cast e obtém o nome
            }
        }
        return nomes;
    }
}

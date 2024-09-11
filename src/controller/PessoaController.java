package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import exception.AdvogadoException;
import exception.PessoaFisicaException;
import exception.PessoaJuridicaException;
import model.Advogado;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import util.Verificacoes;

public class PessoaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<String, Pessoa> pessoas;
    private Map<String, Pessoa> advogados;

    public PessoaController() {
        this.pessoas = new TreeMap<>();
        this.advogados = new TreeMap<>();
    }

    public boolean addPessoaFisica(String nome, String email, long telefone, String cpf) throws PessoaFisicaException {
        verificarCampos(nome, email, String.valueOf(telefone), cpf);

        if (!Verificacoes.validarEmail(email)) {
            throw new PessoaFisicaException("Email inválido.");
        }

        Pessoa pessoaFisica = new PessoaFisica(nome, email, telefone, cpf);
        pessoas.put(cpf, pessoaFisica);
        MainController.save();
        return true;
    }

    public boolean addPessoaJuridica(String nome, String email, long telefone, String cnpj, PessoaFisica preposto) throws PessoaJuridicaException, PessoaFisicaException {
        verificarCampos(nome, email, String.valueOf(telefone), cnpj);

        if (!Verificacoes.validarEmail(email)) {
            throw new PessoaJuridicaException("Email inválido.");
        }

        Pessoa pessoaJuridica = new PessoaJuridica(nome, email, telefone, cnpj, preposto);
        pessoas.put(cnpj, pessoaJuridica);
        MainController.save();
        return true;
    }

    public boolean addAdvogado(String nome, String email, long telefone, String cpf, long registro) throws PessoaFisicaException {
        verificarCampos(nome, email, String.valueOf(telefone), cpf);

        if (!Verificacoes.validarEmail(email)) {
            throw new AdvogadoException("Email inválido.");
        }

        Pessoa advogado = new Advogado(nome, email, telefone, cpf, registro);
        advogados.put(cpf, advogado);
        MainController.save();
        return true;
    }

    private void verificarCampos(String... campos) throws PessoaFisicaException {
        if (!Verificacoes.verificarCamposPreenchidos(campos)) {
            throw new PessoaFisicaException("Todos os campos devem ser preenchidos.");
        }
    }

    public Set<String> getNomesPessoas() {
        return pessoas.keySet();
    }

    public Pessoa getPessoasByCadastro(String cadastroRF) {
        return pessoas.get(cadastroRF);
    }

    public Pessoa getAdvogadosByCadastro(String cadastroRF) {
        return advogados.get(cadastroRF);
    }

    public List<String> getNomesPessoasFisicas() {
        List<String> nomesPessoasFisicas = new ArrayList<>();
        for (Pessoa pessoa : pessoas.values()) {
            if (pessoa instanceof PessoaFisica) {
                nomesPessoasFisicas.add(pessoa.getNome());
            }
        }
        return nomesPessoasFisicas;
    }

    public Set<String> getNomesAdvogados() {
        return advogados.keySet();
    }

    public List<Pessoa> getPessoasFisicas() {
        List<Pessoa> lista = new ArrayList<>();
        for (Pessoa t : pessoas.values()) {
            if (t instanceof PessoaFisica) {
                try {
                    lista.add(new PessoaFisica(t.getNome(), t.getEmail(), t.getTelefone(), t.getCadastroRF()));
                } catch (PessoaFisicaException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public List<Pessoa> getPessoasJuridicas() {
        List<Pessoa> lista = new ArrayList<>();
        for (Pessoa t : pessoas.values()) {
            if (t instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) t;
                try {
                    lista.add(new PessoaJuridica(pj.getNome(), pj.getEmail(), pj.getTelefone(), pj.getCadastroRF(), pj.getPreposto()));
                } catch (PessoaJuridicaException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public List<Advogado> getAdvogados() {
        List<Advogado> lista = new ArrayList<>();
        for (Pessoa t : advogados.values()) {
            Advogado a = (Advogado) t;
            try {
                lista.add(new Advogado(a.getNome(), a.getEmail(), a.getTelefone(), a.getCpf(), a.getRegistro()));
            } catch (PessoaFisicaException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}

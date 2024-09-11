package model;

import java.io.Serializable;
import exception.PessoaFisicaException;
import util.Verificacoes;

public class PessoaFisica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String cpf;

    public PessoaFisica(String nome, String email, long telefone, String cpf) throws PessoaFisicaException {
        super(nome, email, telefone);
        
        if (cpf == null || cpf.equals("")) {
            throw new PessoaFisicaException("CPF deve ser preenchido");
        }
        if (!Verificacoes.validarCPF(cpf)) {
            throw new PessoaFisicaException("CPF inválido");
        }
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getCadastroRF() {
        return getCpf();
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + ", CPF: " + getCpf() + "\n";
    }
}

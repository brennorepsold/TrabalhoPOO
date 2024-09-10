package model;

import java.io.Serializable;
import exception.PessoaFisicaException;

public class PessoaFisica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String cpf;

    public PessoaFisica(String nome, String email, long telefone, String cpf) throws PessoaFisicaException {
        super(nome, email, telefone);
        if (cpf == null || cpf.equals("")) {
            throw new PessoaFisicaException("CPF deve ser preenchido");
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

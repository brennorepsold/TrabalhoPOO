package model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String cpf;  // Alterado de long para String

    public PessoaFisica(String nome, String email, long telefone, String cpf) {  // CPF alterado para String
        super(nome, email, telefone);
        this.cpf = cpf;
    }

    public PessoaFisica(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = cpf;
    }

    public PessoaFisica(String nome, long telefone, String cpf) {
        super(nome, telefone);
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

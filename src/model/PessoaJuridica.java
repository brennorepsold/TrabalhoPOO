package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String cnpj;  // Alterado de long para String
    private PessoaFisica preposto;

    public PessoaJuridica(String nome, String email, long telefone, String cnpj, PessoaFisica preposto) {  // CNPJ alterado para String
        super(nome, email, telefone);
        this.cnpj = cnpj;
        this.preposto = preposto;
    }

    public PessoaJuridica(String nome, String email, String cnpj, PessoaFisica preposto) {
        super(nome, email);
        this.cnpj = cnpj;
        this.preposto = preposto;
    }

    public PessoaJuridica(String nome, long telefone, String cnpj, PessoaFisica preposto) {
        super(nome, telefone);
        this.cnpj = cnpj;
        this.preposto = preposto;
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaFisica getPreposto() {
        return preposto;
    }

    public void setPreposto(PessoaFisica preposto) {
        this.preposto = preposto;
    }

    @Override
    public String getCadastroRF() {
        return getCnpj();
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + ", CNPJ: " + getCnpj() + ", Nome Preposto: " + getPreposto().getNome() + "\n";
    }
}

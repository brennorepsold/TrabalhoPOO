package model;

import java.io.Serializable;
import exception.PessoaJuridicaException;

public class PessoaJuridica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String cnpj;
    private PessoaFisica preposto;

    public PessoaJuridica(String nome, String email, long telefone, String cnpj, PessoaFisica preposto) throws PessoaJuridicaException {
        super(nome, email, telefone);
        if (cnpj == null || cnpj.equals("")) {
            throw new PessoaJuridicaException("CNPJ deve ser preenchido");
        }
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

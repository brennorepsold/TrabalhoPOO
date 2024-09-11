package model;

import java.io.Serializable;
import exception.AdvogadoException;
import exception.PessoaFisicaException;

public class Advogado extends PessoaFisica implements Serializable {
    private static final long serialVersionUID = 1L;
    private final long registro;

    public Advogado(String nome, String email, long telefone, String cpf, long registro) throws PessoaFisicaException {
        super(nome, email, telefone, cpf);
        
        if (registro <= 0) {
            throw new AdvogadoException("Registro deve ser um número positivo");
        }
        
        this.registro = registro;
    }

    public long getRegistro() {
        return registro;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", Email: " + getEmail() + ", Telefone: " + getTelefone() + ", CPF: " + getCpf() + ", Registro: " + getRegistro() + "\n";
    }
}

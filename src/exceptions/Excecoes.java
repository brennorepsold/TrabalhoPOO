package exceptions;

public class Excecoes extends Exception {

    private static final long serialVersionUID = 1L;

    public Excecoes(String mensagem) {
        super(mensagem);
    }

    public static class EmailInvalidoException extends Excecoes {
        public EmailInvalidoException() {
            super("Email inválido!");
        }
    }

    public static class TelefoneInvalidoException extends Excecoes {
        public TelefoneInvalidoException() {
            super("O telefone deve ter exatamente 12 dígitos: (DDD)9XXXX-XXXX (sem espaços ou caracteres entre os números)");
        }
    }

    public static class CPFInvalidoException extends Excecoes {
        public CPFInvalidoException() {
            super("CPF inválido!");
        }
    }

    public static class CNPJInvalidoException extends Excecoes {
        public CNPJInvalidoException() {
            super("CNPJ inválido!");
        }
    }

    public static class PrepostoNaoSelecionadoException extends Excecoes {
        public PrepostoNaoSelecionadoException() {
            super("Por favor, selecione um preposto.");
        }
    }

    public static class PrepostoNaoEncontradoException extends Excecoes {
        public PrepostoNaoEncontradoException() {
            super("Preposto selecionado não encontrado.");
        }
    }

    public static class RegistroInvalidoException extends Excecoes {
        public RegistroInvalidoException() {
            super("O registro informado não é válido, digite somente números.");
        }
    }
}

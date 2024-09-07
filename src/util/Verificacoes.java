package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class Verificacoes {
	public static void exibirPopup(String titulo, String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
	}

	public static void exibirPopupSucesso(String titulo, String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean validarEmail(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validarCPF(String cpf) {
		if (cpf.matches("\\d{11}")) { // -> verifica se tem 11 digitos

			if (IntStream.range(0, 9).anyMatch(num -> ((11111111111L * num) + "").equals(cpf))) {
				return false; // -> confere se os digitos não são repetidos
			}

			int soma = 0, peso = 10; // -> calcula a soma ponderada dos dígitos
			for (int i = 0; i < 9; i++) {
				soma += Integer.parseInt(cpf.charAt(i) + "") * peso--;
			}

			int digito1 = soma * 10 % 11 == 10 ? 0 : soma * 10 % 11; // -> calcula e verifica o 1 dígito verificador
			if (digito1 != Integer.parseInt(cpf.charAt(9) + "")) {
				return false;
			}

			soma = 0;
			peso = 11;
			for (int i = 0; i < 10; i++) {
				soma += Integer.parseInt(cpf.charAt(i) + "") * peso--; // -> calcula a soma ponderada dnv
			}

			int digito2 = soma * 10 % 11 == 10 ? 0 : soma * 10 % 11; // -> calcula e verifica o 2 dígito verificador
			return digito2 == Integer.parseInt(cpf.charAt(10) + "");
		} else {
			return false;
		}
	}

	public static boolean validarCNPJ(String cnpj) {
		if (!cnpj.matches("\\d{14}")) { // -> verifica se o CNPJ tem 14 dígitos numéricos
			return false;
		}

		int digito1 = calcularDigito(cnpj.substring(0, 12), 5, 9); // -> calcula o 1 e 2 dígito verificador
		int digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, 6, 9);

		// -> verifica se os dígitos verificadores correspondem aos últimos dois dígitos
		// do CNPJ
		return (digito1 == Character.getNumericValue(cnpj.charAt(12)))
				&& (digito2 == Character.getNumericValue(cnpj.charAt(13)));
	}

	// calcular um dígito verificador do CNPJ
	private static int calcularDigito(String parteCNPJ, int pesoInicial, int pesoFinal) {
		int soma = 0;
		int peso = pesoInicial;

		for (char c : parteCNPJ.toCharArray()) {
			soma += Character.getNumericValue(c) * peso;
			peso--;

			if (peso < pesoFinal) {
				peso = 9;
			}
		}

		int resto = soma % 11;
		return (resto < 2) ? 0 : (11 - resto);
	}

	public static boolean verificarCamposPreenchidos(String... campos) {
		for (String campo : campos) {
			if (campo == null || campo.trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}



}

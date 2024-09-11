package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		if (cpf == null || !cpf.matches("\\d{11}")) {
			return false;
		}
		return IntStream.range(0, 10).noneMatch(num -> cpf.equals(String.valueOf(num).repeat(11)));
	}

	public static boolean validarCNPJ(String cnpj) {
		if (cnpj == null || !cnpj.matches("\\d{14}")) {
			return false;
		}
		return IntStream.range(0, 10).noneMatch(num -> cnpj.equals(String.valueOf(num).repeat(14)));
	}

	public static boolean verificarCamposPreenchidos(String... campos) {
		for (String campo : campos) {
			if (campo == null || campo.trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean validarData(String data) {
		if (data == null || data.trim().isEmpty()) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean anoMaiorQueAtual(int ano) {
		Calendar calAtual = Calendar.getInstance();
		int anoAtual = calAtual.get(Calendar.YEAR);
		return ano > anoAtual;
	}
	
	public static boolean validarNumeroProcesso(long numero) {
		return numero > 0;
	}
	
	public static boolean validarValorMonetario(double valor) {
		return valor > 0;
	}
}

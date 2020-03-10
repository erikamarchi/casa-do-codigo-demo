package br.com.caelum.casadocodigo.form.validacao;

public class Validacao {

	public static boolean validaCampoEmBranco(String campo) {
		return (campo == null || campo.trim().isEmpty());
	}

	public static boolean validaCampoComValorNumerico(String campo) {
		return validaCampoEmBranco(campo) && !campo.matches("[0-9]+");
	}
	
	public static boolean validaLimiteDeCaractere(String campo, int limite){
		return campo.length() > limite;
	}

	public static boolean validaCampoComValorDecimal(String campo) {
		return validaCampoEmBranco(campo) && !campo.matches("[0-9]+[\\.0-9]?");
	}
}

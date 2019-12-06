package br.com.caelum.casadocodigo.form.validacao;

public class Validacao {

	public static boolean validaCampoEmBranco(String campo) {
		return (campo == null || campo.trim().isEmpty());
	}
	
	public static boolean validaLimiteDeCaractere(String campo, int limite){
		return campo.length() > limite;
	}
}

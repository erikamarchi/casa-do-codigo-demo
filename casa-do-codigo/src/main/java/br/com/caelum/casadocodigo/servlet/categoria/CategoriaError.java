package br.com.caelum.casadocodigo.servlet.categoria;

import java.util.Optional;

import br.com.caelum.casadocodigo.form.validacao.Validacao;
import br.com.caelum.casadocodigo.validacao.GerenciadorDeCategoria;

public class CategoriaError {
	
	private final String erroNome;

	public CategoriaError(String erroNome) {		
		this.erroNome = erroNome;
	}

	public String getErroNome() {
		return erroNome;
	}
	
	static class Validator {
		private final CategoriaDto dto;

		public Validator(CategoriaDto dto) {
			this.dto = dto;
		}
		
		Optional<CategoriaError> validaForm() {
			String erroNome = null;
			
			if (Validacao.validaCampoEmBranco(dto.getNome())) {
				erroNome = "Ei, não esquece de informar o nome da categoria ;-)";
			} 			
			
			if (Validacao.validaLimiteDeCaractere(dto.getNome(), 30)) {
				erroNome = "Esse nome está muito grande, escolha um que não ultrapasse 30 caracteres :)";
			} 
			
			return erroNome == null ? Optional.empty(): Optional.of(new CategoriaError(erroNome));
		}
		
		Optional<CategoriaError> validaRegrasDeCategoria(GerenciadorDeCategoria gerenciadorDeCategoria){
			String erroNome = null;
			
			if(gerenciadorDeCategoria.temCategoriaComMesmoNome(dto)) {
				erroNome = "Já existe uma categoria com esse nome"; 
			}
			
			return erroNome == null ? Optional.empty(): Optional.of(new CategoriaError(erroNome));
		}
	}
}

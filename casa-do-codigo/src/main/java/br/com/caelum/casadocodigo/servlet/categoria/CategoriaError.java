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
			Optional<CategoriaError> erro = Optional.empty();
						
			if (Validacao.validaCampoEmBranco(dto.getNome())) {
				erro = Optional.of(new CategoriaError("Ei, não esquece de informar o nome da categoria ;-)"));
			} 			
			
			if (Validacao.validaLimiteDeCaractere(dto.getNome(), 30)) {
				erro = Optional.of(new CategoriaError("Esse nome está muito grande, escolha um que não ultrapasse 30 caracteres :)"));
			} 
			
			return erro;
		}
		
		Optional<CategoriaError> validaRegrasDeCategoria(GerenciadorDeCategoria gerenciadorDeCategoria){
			Optional<CategoriaError> erro = Optional.empty();
			
			if(gerenciadorDeCategoria.temCategoriaComMesmoNome(dto)) {
				erro = Optional.of(new CategoriaError("Já existe uma categoria com esse nome")); 
			}
			
			return erro;
		}
	}
}

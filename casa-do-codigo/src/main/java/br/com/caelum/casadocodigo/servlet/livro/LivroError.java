package br.com.caelum.casadocodigo.servlet.livro;

import java.util.Optional;

import br.com.caelum.casadocodigo.form.validacao.Validacao;
import br.com.caelum.casadocodigo.validacao.GerenciadorDeLivro;

public class LivroError {
	
	private final String erroTitulo;
	private final String erroCategoria;
	private final String erroPreco;
	private final String erroResumo;
	private final String erroAutor;
	private final String erroNumeroDePaginas;
	private final String erroIsbn;
	
	public LivroError(String erroTitulo, String erroCategoria, String erroPreco, String erroResumo, String erroAutor,
			String erroNumeroDePaginas, String erroIsbn) {
		this.erroTitulo = erroTitulo;
		this.erroCategoria = erroCategoria;
		this.erroPreco = erroPreco;
		this.erroResumo = erroResumo;
		this.erroAutor = erroAutor;
		this.erroNumeroDePaginas = erroNumeroDePaginas;
		this.erroIsbn = erroIsbn;
	}

	public String getErroTitulo() {
		return erroTitulo;
	}

	public String getErroCategoria() {
		return erroCategoria;
	}

	public String getErroPreco() {
		return erroPreco;
	}

	public String getErroResumo() {
		return erroResumo;
	}

	public String getErroAutor() {
		return erroAutor;
	}

	public String getErroNumeroDePaginas() {
		return erroNumeroDePaginas;
	}

	public String getErroIsbn() {
		return erroIsbn;
	}

	static class Validator {
		private final LivroDto dto;

		public Validator(LivroDto dto) {
			this.dto = dto;
		}
		
		Optional<LivroError> validaForm() {
			String erroTitulo = null;
			String erroCategoria = null;
			String erroPreco = null;
			String erroResumo = null;
			String erroAutor = null;
			String erroNumeroDePaginas = null;
			String erroIsbn = null;
						
			if (Validacao.validaCampoEmBranco(dto.getTitulo())) {
				erroTitulo = "Ei, não esquece de informar o titulo do livro ;-)";
			} else if (Validacao.validaLimiteDeCaractere(dto.getTitulo(), 255)) {
				erroTitulo = "Esse titulo está muito grande, escolha um que não ultrapasse 255 caracteres :)";
			}
			
			if (Validacao.validaCampoComValorNumerico(dto.getIdDaCategoria())) {
				erroCategoria = "Ei, não esquece de informar o código da categoria do livro ;-)";
			}
			
			if (Validacao.validaCampoComValorDecimal(dto.getPrecoFormatado())) {
				erroPreco = "Ei, não esquece de informar um preço válido ;-)";
			}
			
			if (Validacao.validaCampoEmBranco(dto.getResumo())) {
				erroResumo = "Ei, não esquece de informar o resumo do livro ;-)";
			}
			
			if (Validacao.validaCampoComValorNumerico(dto.getIdDoAutor())) {
				erroAutor = "Ei, não esquece de informar o código do autor do livro ;-)";
			}
			
			if (Validacao.validaCampoComValorNumerico(dto.getNumeroDePaginas())) {
				erroNumeroDePaginas = "Ei, não esquece de informar um numero valido de pagina ;-)";
			} else if (Integer.parseInt(dto.getNumeroDePaginas()) < 100) {
				erroNumeroDePaginas = "Ei, o numero de paginas precisa ser no minimo 100 ;-)";
			}
			
			if (Validacao.validaCampoComValorNumerico(dto.getIsbn())) {
				erroIsbn = "Ei, não esquece de informar o ISBN (que é um número) ;-)";
			}
			
			if (erroTitulo != null || 
				erroCategoria != null || 
				erroPreco != null || 
				erroResumo != null || 
				erroAutor != null || 
				erroNumeroDePaginas != null ||
				erroIsbn != null) {
				return Optional.of(new LivroError(erroTitulo, erroCategoria, erroPreco, erroResumo, erroAutor,
						erroNumeroDePaginas, erroIsbn));
			} else {
				return Optional.empty();
			}
			
		}
		
		Optional<LivroError> validaRegrasDeLivro(GerenciadorDeLivro gerenciadorDeLivro){
			Optional<LivroError> erro = Optional.empty();
			
			if(gerenciadorDeLivro.temLivroComMesmoTitulo(dto)) {
				erro = Optional.of(new LivroError("Já existe uma livro com esse titulo",null,null,null,null,null,null)); 
			}
			
			return erro;
		}
	}
}

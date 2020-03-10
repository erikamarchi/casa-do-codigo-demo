package br.com.caelum.casadocodigo.validacao;

import br.com.caelum.casadocodigo.dao.LivroDao;
import br.com.caelum.casadocodigo.servlet.livro.LivroDto;

public class GerenciadorDeLivro {
	
	private LivroDao livroDao;

	public GerenciadorDeLivro(LivroDao livroDao) {		
		this.livroDao = livroDao;
	}
	
	public boolean temLivroComMesmoTitulo(LivroDto livroDto) {
		  return livroDao.getPorTitulo(livroDto.getTitulo())
				  			 .filter(c -> !c.getId().equals(livroDto.getId()))
				  			 .isPresent();
	}
}

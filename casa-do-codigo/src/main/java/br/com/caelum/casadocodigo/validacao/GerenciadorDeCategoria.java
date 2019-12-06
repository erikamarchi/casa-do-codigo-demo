package br.com.caelum.casadocodigo.validacao;

import br.com.caelum.casadocodigo.dao.CategoriaDao;
import br.com.caelum.casadocodigo.servlet.categoria.CategoriaDto;

public class GerenciadorDeCategoria {
	
	private CategoriaDao categoriaDao;

	public GerenciadorDeCategoria(CategoriaDao categoriaDao) {		
		this.categoriaDao = categoriaDao;
	}
	
	public boolean temCategoriaComMesmoNome(CategoriaDto categoriaDto) {
		
		  return categoriaDao.getPorNome(categoriaDto.getNome())
				  			 .filter(c -> !c.getId().equals(categoriaDto.getId()))
				  			 .isPresent();
	}
}

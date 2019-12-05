package br.com.caelum.casadocodigo.servlet.categoria;

import java.util.Optional;

import br.com.caelum.casadocodigo.dao.CategoriaDao;

public class CategoriaValidator {

	public static Optional<String> valida(CategoriaDto categoriaDto, CategoriaDao categoriaDao) {
		Optional<String> erroNome = Optional.empty();
		String nomeCategoria = categoriaDto.getNome();

		if (categoriaDto.getNome() == null || nomeCategoria.trim().isEmpty()) {
			erroNome = Optional.of("Ei, não esquece de informar o nome da categoria ;-) ");
		} else if (nomeCategoria.length() > 30) {
			erroNome = Optional.of("Esse nome está muito grande, escolha um que não ultrapasse 30 caracteres :)");
		} else if (categoriaDao.getPorNome(nomeCategoria).filter(c -> !c.getId().equals(categoriaDto.getId())).isPresent()) {
			erroNome = Optional
					.of("Essa não, já existe uma categoria com esse nome! Seja criativo e escolha outro nome :D");
		}		
		
		return erroNome;
	}	
}

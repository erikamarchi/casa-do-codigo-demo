package br.com.caelum.casadocodigo.service;

import java.util.List;
import java.util.Optional;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.model.Autor;

public class AutorService {

	private final AutorDao autorDao;

	public AutorService(AutorDao autorDao) {
		this.autorDao = autorDao;
	}

	public boolean temAutorComMesmoNome(String nome, Long idAtual) {
		return autorDao.getPorNome(nome).filter(a -> !a.getId().equals(idAtual)).isPresent();
	}

	public void atualiza(Autor autor) {
		autorDao.atualiza(autor);
	}
	
	public void adiciona(Autor autor) {
		autorDao.adiciona(autor);
	}

	public List<Autor> buscaAutores() {		
		return autorDao.getLista();
	}
	
	public Optional<Autor> buscaAutor(Long id) {
		return autorDao.getAutor(id);
	}
	
	public void exclui(Autor autor) {
		autorDao.exclui(autor);
	}

}

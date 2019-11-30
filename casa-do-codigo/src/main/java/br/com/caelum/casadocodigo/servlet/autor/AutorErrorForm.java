package br.com.caelum.casadocodigo.servlet.autor;

import java.util.Optional;

import br.com.caelum.casadocodigo.dao.AutorDao;
import br.com.caelum.casadocodigo.model.Autor;

public class AutorErrorForm {
	
	private final String erroNome;
	private final String erroResumo;
	
	private AutorErrorForm(String erroNome, String erroResumo) {
		this.erroNome = erroNome;
		this.erroResumo = erroResumo;
	}

	public String getErroNome() {
		return erroNome;
	}

	public String getErroResumo() {
		return erroResumo;
	}
	
	static class Validator {
		private final AutorDto dto;

		public Validator(AutorDto dto) {
			this.dto = dto;
		}
		
		Optional<AutorErrorForm> valida(AutorDao autorDao) {
			String erroNome;
			if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
				erroNome = "Ei, não esqueça o nome do autor :D";
			} else {				
				erroNome = autorDao
						.getPorNome(dto.getNome())
						.filter(a -> !a.getId().equals(dto.getId()))
						.map(a -> "Já existe um autor com esse nome")
						.orElse(null);
			}
			
			String erroResumo;
			if (dto.getResumo() == null || dto.getResumo().trim().isEmpty()) {
				erroResumo = "Você precisa nos contar um pouquinho sobre o autor, prometo que vai ser rápidinho ;-)";
			} else {
				erroResumo = null;
			}
			
			if (erroNome == null && erroResumo == null) {
				return Optional.empty();
			} else {
				return Optional.of(new AutorErrorForm(erroNome, erroResumo));
			}
		}
	}
	
}

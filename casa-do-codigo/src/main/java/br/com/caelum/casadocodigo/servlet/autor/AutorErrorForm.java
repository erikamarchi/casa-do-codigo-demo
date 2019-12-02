package br.com.caelum.casadocodigo.servlet.autor;

import java.util.Optional;

import br.com.caelum.casadocodigo.service.AutorService;

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
		
		Optional<AutorErrorForm> valida(AutorService autorService) {
			String erroNome;
			if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
				erroNome = "Ei, não esqueça o nome do autor :D";
			} else {				
				erroNome = autorService.temAutorComMesmoNome(dto.getNome(), dto.getId()) ? "Já existe um autor com esse nome" : null;
						
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

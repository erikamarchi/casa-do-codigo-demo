package br.com.caelum.casadocodigo.model;

public class Autor {
	
	private Long id;
	private String nome;
	private String resumo;
	
	public Autor(Long id, String nome, String resumo) {
		this.id = id;
		this.nome = nome;
		this.resumo = resumo;
	}

	public String getNome() {
		return nome;
	}

	public String getResumo() {
		return resumo;
	}

	public Long getId() {
		return id;
	}	
	
}

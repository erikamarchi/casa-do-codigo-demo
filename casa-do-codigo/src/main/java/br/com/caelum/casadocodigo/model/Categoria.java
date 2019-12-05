package br.com.caelum.casadocodigo.model;

public class Categoria {

	private Long id;
	private String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}	
}

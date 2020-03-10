package br.com.caelum.casadocodigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.caelum.casadocodigo.model.Livro;

public class LivroDao {
	
	private final static String SELECT = "select l.*, c.nome AS nome_da_categoria, a.nome AS nome_do_autor from livros l inner join categorias c on c.id = l.categoria_id inner join autores a on a.id = l.autor_id";

	private Connection connection;
	
	public LivroDao(Connection connection) {
		this.connection = connection;
	}
	
	public void adiciona(Livro livro) {
		try {
			String sql = "insert into livros (titulo,categoria_id,preco,resumo,autor_id,numero_de_paginas,isbn) values (?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, livro.getTitulo());
			stmt.setLong(2, livro.getIdDaCategoria());
			stmt.setDouble(3, livro.getPreco());
			stmt.setString(4, livro.getResumo());
			stmt.setLong(5, livro.getIdDoAutor());
			stmt.setInt(6, livro.getNumeroDePaginas());
			stmt.setString(7, livro.getIsbn());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Livro livro) {
		String sql = "update livros set "
				+ "titulo = ?, "
				+ "categoria_id = ?, "
				+ "preco = ?, "
				+ "resumo = ?, "
				+ "autor_id = ?, "
				+ "numero_de_paginas = ?, "
				+ "isbn = ? "
				+ "where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, livro.getTitulo());
			stmt.setLong(2, livro.getIdDaCategoria());
			stmt.setDouble(3, livro.getPreco());
			stmt.setString(4, livro.getResumo());
			stmt.setLong(5, livro.getIdDoAutor());
			stmt.setInt(6, livro.getNumeroDePaginas());
			stmt.setString(7, livro.getIsbn());
			
			stmt.setLong(8, livro.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Livro> getLista() {
		try {
			List<Livro> livros = new ArrayList<Livro>();
			PreparedStatement stmt = this.connection.prepareStatement(SELECT);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Livro livro = populaLivro(rs);
				livros.add(livro);
			}

			rs.close();
			stmt.close();

			return livros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Optional<Livro> getLivro(long id) {
		try {
			Optional<Livro> livro = Optional.empty();
			
			String sql = SELECT + " where l.id=?";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				livro = Optional.of(populaLivro(rs));
			}			
			
			rs.close();
			stmt.close();

			return livro;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Long id) {
		String sql = "delete from livros where id=?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	

	public Optional<Livro> getPorTitulo(String titulo) {
		try {
			Optional<Livro> livro = Optional.empty();
			
			String sql = SELECT + " where l.titulo=?";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, titulo);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				livro = Optional.of(populaLivro(rs));
			}
			
			rs.close();
			stmt.close();

			return livro;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Livro populaLivro(ResultSet rs) throws SQLException {
		return new Livro(
				rs.getLong("id"), 
				rs.getString("titulo"), 
				rs.getLong("categoria_id"), rs.getString("nome_da_categoria"), 
				rs.getDouble("preco"), 
				rs.getString("resumo"), 
				rs.getLong("autor_id"), rs.getString("nome_do_autor"),
				rs.getInt("numero_de_paginas"), 
				rs.getString("isbn")
				);
	}
}

package br.com.caelum.casadocodigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.caelum.casadocodigo.model.Categoria;

public class CategoriaDao {

	private Connection connection;
	
	public CategoriaDao(Connection connection) {
		this.connection = connection;
	}
	
	public void adiciona(Categoria categoria) {
		try {
			String sql = "insert into categorias (nome) values (?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, categoria.getNome());			

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Categoria categoria) {
		String sql = "update categorias set nome = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());			
			stmt.setLong(2, categoria.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Categoria> getLista() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from categorias");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = populaCategoria(rs);
				categorias.add(categoria);
			}

			rs.close();
			stmt.close();

			return categorias;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Optional<Categoria> getCategoria(long id) {
		try {
			Optional<Categoria> categoria = Optional.empty();
			
			String sql = "select * from categorias where id=?";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				categoria = Optional.of(populaCategoria(rs));
			}			
			
			rs.close();
			stmt.close();

			return categoria;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Long id) {
		String sql = "delete from categorias where id=?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	

	public Optional<Categoria> getPorNome(String nome) {
		try {
			Optional<Categoria> categoria = Optional.empty();
			
			String sql = "select * from categorias where nome=?";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				categoria = Optional.of(populaCategoria(rs));
			}			
			
			rs.close();
			stmt.close();

			return categoria;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Categoria populaCategoria(ResultSet rs) throws SQLException {

		Categoria categoria = new Categoria(rs.getString("nome"));
		categoria.setId(rs.getLong("id"));

		return categoria;
	}
}

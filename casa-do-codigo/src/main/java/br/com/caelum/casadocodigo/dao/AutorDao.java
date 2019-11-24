package br.com.caelum.casadocodigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.model.Autor;

public class AutorDao {

	private Connection connection;

	public AutorDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Autor autor) {
		try {
			String sql = "insert into autores (nome, resumo) values (?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, autor.getNome());
			stmt.setString(2, autor.getResumo());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Autor autor) {
		String sql = "update autores set nome = ?, resumo = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.setString(2, autor.getResumo());
			stmt.setLong(3, autor.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Autor> getLista() {
		try {
			List<Autor> autores = new ArrayList<Autor>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from autores");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Autor autor = populaAutor(rs);
				autores.add(autor);
			}

			rs.close();
			stmt.close();

			return autores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Autor getAutor(long id) {
		try {
			Autor autor = null;
			String sql = "select * from autores where id=?";

			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				autor = populaAutor(rs);
			}

			rs.close();
			stmt.close();

			return autor;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Autor autor) {
		String sql = "delete from autores where id=?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, autor.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Autor populaAutor(ResultSet rs) throws SQLException {

		Autor autor = new Autor(rs.getString("nome"), rs.getString("resumo"));
		autor.setId(rs.getLong("id"));

		return autor;
	}

}

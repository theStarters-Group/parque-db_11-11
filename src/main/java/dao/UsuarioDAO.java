package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelo.Itinerario;
import modelo.Usuario;

public class UsuarioDAO {
	public List<Usuario> findAll() throws SQLException {
		String sql = "SELECT * FROM usuarios";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Usuario> usuarios = new LinkedList<Usuario>();
		while (resultados.next()) {
			usuarios.add(toUsuario(resultados));
		}

		return usuarios;
	}

	public int update(Usuario usuario) throws SQLException {
		String sql = "UPDATE USUARIOS SET dinero = ?, tiempo=? WHERE id_usuario = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, usuario.getDinero());
		statement.setDouble(2, usuario.getTiempo());
		statement.setInt(3, usuario.getIdUsuario());

		int rows = statement.executeUpdate();

		return rows;
	}

	private Usuario toUsuario(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getInt(1), resultados.getString(2), resultados.getInt(3), resultados.getDouble(4),
				resultados.getDouble(5));
	}

}

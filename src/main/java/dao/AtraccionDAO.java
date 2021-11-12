package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelo.Atraccion;
import modelo.Itinerario;

public class AtraccionDAO {

	public int insert(Atraccion atraccion) throws SQLException {
		String sql = "INSERT INTO atracciones (id, nombre, precio, duracion, cupo, id_tipo_atraccion) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atraccion.getIdAtraccion());
		statement.setString(2, atraccion.getNombre());
		statement.setDouble(3, atraccion.getCosto());
		statement.setDouble(4, atraccion.getTiempo());
		statement.setDouble(5, atraccion.getCupo());
		statement.setDouble(6, atraccion.getTipoDeAtraccion());

		int rows = statement.executeUpdate();

		return rows;
	}

	public int updateCupo(Atraccion atraccion) throws SQLException {
		String sql = "UPDATE atracciones SET cupo = ? WHERE id = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setDouble(1, atraccion.getCupo());
		statement.setInt(2, atraccion.getIdAtraccion());
		int rows = statement.executeUpdate();

		return rows;
	}

//	public int update(Atraccion atraccion) throws SQLException {
//		String sql = "UPDATE atracciones SET id_tipo_atraccion = ?, nombre = ?, precio=? , duracion= ?, cupo = ?, WHERE id = ?";
//		Connection conn = ConnectionProvider.getConnection();
//
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setDouble(1, atraccion.getTipoDeAtraccion());
//		statement.setString(2, atraccion.getNombre());
//		statement.setDouble(3, atraccion.getCosto());
//		statement.setDouble(4, atraccion.getTiempo());
//		statement.setDouble(5, atraccion.getCupo());
//		statement.setInt(6, atraccion.getIdAtraccion());
//		int rows = statement.executeUpdate();
//
//		return rows;
//	}

	public static int countAll() throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("TOTAL");

		return total;
	}

	// public int countAll() throws SQLException {
	// String sql = "SELECT COUNT(1) AS TOTAL FROM USERS";
	// Connection conn = ConnectionProvider.getConnection();
	// PreparedStatement statement = conn.prepareStatement(sql);
	// ResultSet resultados = statement.executeQuery();

	// resultados.next();
	// int total = resultados.getInt("TOTAL");

	// return total;
	// }

	public List<Atraccion> findAll() throws SQLException {
		String sql = "SELECT * FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		while (resultados.next()) {
			atracciones.add(toAtraccion(resultados));
		}

		return atracciones;
	}

	public static Atraccion getAtraccionPorId(int id) throws SQLException {
		Atraccion atraccion = null;
		String sql = "SELECT * FROM ATRACCIONES WHERE ID = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		// Atraccion atraccion = null;

		if (resultados.next()) {
			atraccion = toAtraccion(resultados);
		}

		return atraccion;
	}

	private static Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt(1), resultados.getString(2), resultados.getDouble(3),
				resultados.getDouble(4), resultados.getInt(5), resultados.getInt(6));
	}
}

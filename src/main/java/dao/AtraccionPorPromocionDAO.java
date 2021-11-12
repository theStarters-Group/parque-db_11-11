package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;

import modelo.AtraccionPorPromocion;

public class AtraccionPorPromocionDAO {
	public List<AtraccionPorPromocion> findAll() throws SQLException {
		String sql = "SELECT * FROM atracciones_promo";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<AtraccionPorPromocion> atracciones_promo = new LinkedList<AtraccionPorPromocion>();
		while (resultados.next()) {
			atracciones_promo.add(toAtraccionPorPromocion(resultados));
		}

		return atracciones_promo;
	}

	private AtraccionPorPromocion toAtraccionPorPromocion(ResultSet resultados) throws SQLException {
		return new AtraccionPorPromocion(resultados.getInt(1), resultados.getInt(2));
	}

}

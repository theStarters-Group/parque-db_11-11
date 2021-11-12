package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;

import modelo.TipoPromocion;

public class TipoPromocionDAO {
	public List<TipoPromocion> findAll() throws SQLException {
		String sql = "SELECT * FROM tipo_promociones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<TipoPromocion> tipoPromociones = new LinkedList<TipoPromocion>();
		while (resultados.next()) {
			tipoPromociones.add(toTipoPromocion(resultados));
		}

		return tipoPromociones;
	}

	private TipoPromocion toTipoPromocion(ResultSet resultados) throws SQLException {
		return new TipoPromocion(resultados.getInt(1), resultados.getString(2));
	}

}

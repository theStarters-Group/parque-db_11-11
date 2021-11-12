package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jdbc.ConnectionProvider;
import modelo.TipoAtraccion;

public class TipoAtraccionDAO {

	public List<TipoAtraccion> findAll() throws SQLException {
		String sql = "SELECT * FROM tipo_atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<TipoAtraccion> tipoAtracciones = new LinkedList<TipoAtraccion>();
		while (resultados.next()) {
			tipoAtracciones.add(toTipoAtraccion(resultados));

		}

		return tipoAtracciones;
	}
	
	public String findByIdAtraccion(int id) throws SQLException {
		String sql = "SELECT nombre FROM tipo_atracciones WHERE ID = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		String nombreAtraccion = null;
		
		if (resultados.next()) {
	    nombreAtraccion = resultados.getString(2);
		}
		

		return nombreAtraccion;
	}

	
	//private String toNombreAtraccion(ResultSet resultados)throws SQLException {
	//	return resultados.getString(1);
	//} 
		
		

	private TipoAtraccion toTipoAtraccion(ResultSet resultados) throws SQLException {
		return new TipoAtraccion(resultados.getInt(1), resultados.getString(2));
	}

}

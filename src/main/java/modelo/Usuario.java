package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

public class Usuario {

	private int idUsuario;
	private String nombre;
	private double tiempo;
	private double dinero;
	private int tipo;

	LinkedList<Ofertable> itinerario = new LinkedList<Ofertable>();
	LinkedList<Ofertable> atraccionComprada = new LinkedList<Ofertable>();

	public LinkedList<Ofertable> getAtraccionComprada() {
		return atraccionComprada;
	}

	public String getNombre() {
		return nombre;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getTipo() {
		return tipo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public double getDinero() {
		return dinero;
	}

	public Usuario(int idUsuario, String nombre, int tipo, double dinero, double tiempo) {

		this.idUsuario = idUsuario;
		this.nombre = nombre;

		this.tiempo = tiempo;
		this.dinero = dinero;
		this.tipo = tipo;
	}

	@Override
	public String toString() {

		return "Usuario [nombre=" + nombre + ", tipo=" + tipo + ", dinero=" + dinero + ",tiempo=" + tiempo + "]\n";
	}

	public void comprar(Ofertable oferta)throws SQLException {
		if (oferta.hayCupo() && this.puedeComprar(oferta)) {
			itinerario.add(oferta);
			this.dinero -= oferta.getCosto();
			this.tiempo -= oferta.getTiempo();
			oferta.actualizarCupo();
		}

	}

	public boolean puedeComprar(Ofertable oferta) {

		return this.dinero >= oferta.getCosto() && this.tiempo >= oferta.getTiempo();

		
		
	}

	public LinkedList<Ofertable> getItinerario() {
		return itinerario;
	}

	public void imprimirItinerario(LinkedList<Ofertable> itinerario, String file) throws IOException {

		file = "salida/" + file;
		PrintWriter salida = new PrintWriter(new FileWriter(file));

		for (Ofertable oferta : itinerario) {
			salida.println(oferta);
		}
		salida.close();

	}

}

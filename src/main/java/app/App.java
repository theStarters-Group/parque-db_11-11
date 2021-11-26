package app;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import dao.AtraccionDAO;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;
import modelo.Atraccion;
import modelo.Itinerario;
import modelo.Promocion;
import modelo.Usuario;
import modelo.Ofertable;
import modelo.ComparadorDeOfertas;

public class App {

	public static void main(String[] args) throws SQLException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.findAll();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAll();
		PromocionDAO promocionDAO = new PromocionDAO();
		List<Promocion> promociones = promocionDAO.findAll(atracciones);
		ItinerarioDAO itinerarioDAO = new ItinerarioDAO();

		List<Ofertable> ofertas = new LinkedList<Ofertable>();
		ofertas.addAll(promociones);
		ofertas.addAll(atracciones);

		System.out.println("                       PARQUE DE LA COSTA");
		System.out.println("                  Presione ENTER para comenzar");
		Scanner in = new Scanner(System.in);

		String entradaConsola = in.nextLine();
		for (Usuario usuario : usuarios) {

			int atraccionFavorita = usuario.getTipo();
			HashMap<Atraccion, String> atraccionComprada = new HashMap<Atraccion, String>();
			ofertas.sort(new ComparadorDeOfertas(atraccionFavorita));

			System.out.println("\n");
			System.out.println("                    �Bienvenido " + usuario.getNombre() + "!" + " \n");
			System.out.println("Saldo disponible: $" + usuario.getDinero() + "     Tiempo disponible: "
					+ String.format("%.1f", (usuario.getTiempo())) + " hs." + "\n");
			System.out.println("                Atracci�n favorita: " + usuario.getTipo());
			System.out.println("\n");
			for (Ofertable oferta : ofertas) {

				if (usuario.puedeComprar(oferta, atraccionComprada)) {

					System.out.println(oferta.getNombre() + "\n");
					System.out.println("Costo = $" + Math.round(oferta.getCosto() * 100) / 100 + "");
					System.out.println("Tipo de Atraccion=" + oferta.getTipoAtraccion() + " ");
					System.out.println("Cupo atraccion=" + oferta.getCupo());
					System.out.println("Duraci�n = " + String.format("%.1f", (oferta.getTiempo())) + "hs. \n");
					System.out.println("�Desea comprarlo? " + "(s/n)");

					String acepta;
					do {
						System.out.println("Introduzca el caracter 's' o 'n' ");
						acepta = in.nextLine();
					} while (!(acepta.equalsIgnoreCase("s")) && !(acepta.equalsIgnoreCase("n")));

					if (acepta.equalsIgnoreCase("s")) {
						usuario.comprar(oferta, atraccionComprada);
						usuarioDAO.update(usuario);
						atraccionDAO.updateCupo(oferta);
						Itinerario itinerario = new Itinerario(usuario.getIdUsuario(), oferta.getIdPromo(),
								oferta.getIdAtraccion());
						itinerarioDAO.insert(itinerario);
						if (oferta.esPromocion()) {
							for (int l = 0; l < oferta.getAtraccionesEnPromocion().length; l++) {
								atraccionDAO.updateCupo(oferta.getAtraccionesEnPromocion()[l]);
							}
						} else {
							atraccionDAO.updateCupo(oferta);
						}
						System.out.println("\n");
						System.out.println("");
					}
				}
			}
			System.out.println("**************************************************************************");
			System.out.println(" \n");
			System.out.println("Presione enter para continuar");

			try {
				System.in.read();
			} catch (Exception e) {
			}
		}
		System.err.println(entradaConsola);
		in.close();
	}
}
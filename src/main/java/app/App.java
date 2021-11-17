package app;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

import dao.AtraccionDAO;
import dao.AtraccionPorPromocionDAO;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.TipoAtraccionDAO;
import dao.UsuarioDAO;
import modelo.Atraccion;
import modelo.AtraccionPorPromocion;
import modelo.Itinerario;
import modelo.Promocion;
import modelo.TipoAtraccion;
import modelo.Usuario;
import modelo.Ofertable;
import modelo.ComparadorDeOfertas;

public class App {

	public static void main(String[] args) throws SQLException {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.findAll();
		TipoAtraccionDAO tipoAtraccionDAO = new TipoAtraccionDAO();
		List<TipoAtraccion> tipoAtracciones = tipoAtraccionDAO.findAll();
		AtraccionDAO atraccionDAO = new AtraccionDAO();
		List<Atraccion> atracciones = atraccionDAO.findAll();
		PromocionDAO promocionDAO = new PromocionDAO();
		List<Promocion> promociones = promocionDAO.findAll();
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

			LinkedList<Atraccion> atraccionComprada = new LinkedList<Atraccion>();

			ofertas.sort(new ComparadorDeOfertas(atraccionFavorita));

			System.out.println("\n");
			System.out.println("                    �Bienvenido " + usuario.getNombre() + "!" + " \n");
			System.out.println("Saldo disponible: $" + usuario.getDinero() + "     Tiempo disponible: "
					+ usuario.getTiempo() + " hs." + "\n");
			System.out.println("                Atracci�n favorita: " + usuario.getTipo());
			System.out.println("\n");
			for (Ofertable oferta : ofertas) {

				if (usuario.puedeComprar(oferta) && !atraccionComprada.contains(oferta) && oferta.getCupo() > 0) {

					System.out.println(oferta.getNombre() + "\n");
					System.out.println("Costo = $" + Math.round(oferta.getCosto() * 100) / 100 + "");
					System.out.println("Tipo de Atraccion=" + oferta.getTipoDeAtraccion() + " ");
					System.out.println("Cupo atraccion=" + oferta.getCupo());
					System.out.println("Duraci�n = " + oferta.getTiempo() + "hs. \n");
					System.out.println("�Desea comprarlo? " + "(s/n)");

					String acepta;
					do {
						System.out.println("Introduzca el caracter 's' o 'n' ");
						acepta = in.nextLine();
					} while (!(acepta.equalsIgnoreCase("s")) && !(acepta.equalsIgnoreCase("n")));

					if (acepta.equalsIgnoreCase("s")) {
						usuario.comprar(oferta);
						oferta.actualizarCupo();
						Itinerario itinerario = new Itinerario(usuario.getIdUsuario(), oferta.getIdPromo(),
								oferta.getIdTipoAtraccion());
						itinerarioDAO.insert(itinerario);

						if (oferta.esPromocion()) {
							for (int l = 0; l < oferta.getAtraccionesEnPromocion().length; l++) {

								Atraccion a = new Atraccion();
								a = oferta.getAtraccionesEnPromocion()[l];
								if (!atraccionComprada.contains(a))
									atraccionComprada.add(a);
							}

						} else {
							Atraccion a = new Atraccion();
							if (!atraccionComprada.contains(a))
								atraccionComprada.add(a);
						}

						System.out.println("\n");
						System.out.println("");
					}

				}

			}
			List<Itinerario> itinerarios = itinerarioDAO.findAll();
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

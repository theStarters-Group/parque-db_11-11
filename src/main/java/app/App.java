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
		AtraccionPorPromocionDAO atraccionPorPromocionDAO = new AtraccionPorPromocionDAO();
		List<AtraccionPorPromocion> atracciones_promo = atraccionPorPromocionDAO.findAll();
		ItinerarioDAO itinerarioDAO = new ItinerarioDAO();
		// PromocionDAO listaAtraccionesDAO = new PromocionDAO();
		// List<String> listaAtracciones = listaAtraccionesDAO.findAtracciones();
		
		
//		for (Promocion promocion : promociones) {

//			String[] s = promocion.getAtracciones().split(" ");
//			Atraccion[] atraccionesEnPromo = new Atraccion[s.length];

//			for (int i = 0; i < s.length; i++) {
//
//				for (Atraccion atraccion : atracciones) {
//
//					if (Integer.parseInt(s[i]) == (atraccion.getIdAtraccion())) {
//
//						atraccionesEnPromo[i] = atraccion;
//						break;
//					}
//
//				}
//				promocion.atraccionesEnPromocion = atraccionesEnPromo;
//				// System.out.println(promocion.getAtraccionesEnPromocion());
//			}

//			int totalPromociones = PromocionDAO.countAll();
//			int totalAtracciones = AtraccionDAO.countAll();
//
//			Ofertable[] ofertas = new Ofertable[totalPromociones + totalAtracciones];
//			int j = 0;
//			for (Promocion promo : promociones) {
//
//				ofertas[j] = promo;
//				j = j++;
//			}
//			for (Atraccion atraccion : atracciones) {
//				ofertas[j] = atraccion;
//			}

//			System.arraycopy(promociones, 0, ofertas, 0, totalPromociones);
//			 System.arraycopy(atracciones, 0, ofertas, totalPromociones,
//			totalAtracciones);

			List<Ofertable> ofertas = new LinkedList<Ofertable>();
			ofertas.addAll(promociones);
			ofertas.addAll(atracciones);

			// Ofertable[] ofertas = new Ofertable[atracciones.length + promociones.length];

			// System.arraycopy(promociones, 0, ofertas, 0, promociones.length);
			// System.arraycopy(atracciones, 0, ofertas, promociones.length,
			// atracciones.length);
			System.out.println("                       PARQUE DE LA COSTA");
			System.out.println("                  Presione ENTER para comenzar");
			Scanner in = new Scanner(System.in);

			String entradaConsola = in.nextLine();
			// System.out.println(ofertas);
			for (Usuario usuario : usuarios) {

				int atraccionFavorita = usuario.getTipo();

				LinkedList<Atraccion> atraccionComprada = new LinkedList<Atraccion>();

				ofertas.sort(new ComparadorDeOfertas(atraccionFavorita));
				// System.out.println(ofertas);
				System.out.println("\n");
				System.out.println("                    ¡Bienvenido " + usuario.getNombre() + "!" + " \n");
				System.out.println("Saldo disponible: $" + usuario.getDinero() + "     Tiempo disponible: "
						+ usuario.getTiempo() + " hs." + "\n");
				System.out.println("                Atracción favorita: " + usuario.getTipo());
				System.out.println("\n");

				// for (int i = 0; i < ofertas.length; j++) {

				// Ofertable oferta = ofertas[i];

				for (Ofertable oferta : ofertas) {

					if (usuario.puedeComprar(oferta) && !atraccionComprada.contains(oferta)) {

						System.out.println(oferta);
						System.out.println("Costo = $" + Math.round(oferta.getCosto() * 100) / 100 + " \n");
						System.out.println("Duración = " + oferta.getTiempo() + "hs. \n");
						System.out.println("¿Desea comprarlo? " + "(s/n)");

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
							itinerarioDAO.update(itinerario);

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
				System.out.println("Itinerario del usuario " + usuario.getNombre() + "\n");
				System.out.println(usuario.getItinerario());

				List<Itinerario> itinerarios = itinerarioDAO.findAll();

				System.out.println("Itinerario del usuario " + usuario.getNombre() + "\n");
				for (Itinerario itinerario : itinerarios) {
					if (usuario.getIdUsuario() == itinerario.getIdUsuario()) {
						System.out.println(itinerario);
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
	}
//}

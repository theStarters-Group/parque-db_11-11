package modelo;

import java.util.Comparator;

public class ComparadorDeOfertas implements Comparator<Ofertable> {
	int atraccionFavorita;

	public ComparadorDeOfertas(int atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}


	public int compare(Ofertable o1, Ofertable o2) {

		if (o2.getTipoDeAtraccion() == atraccionFavorita && o1.getTipoDeAtraccion() != atraccionFavorita) {
			return 1;
		} else if (o2.getTipoDeAtraccion() != atraccionFavorita && o1.getTipoDeAtraccion() == atraccionFavorita) {
			return -1;
		}

		if (o2.esPromocion() && !o1.esPromocion()) {
			return 1;
		} else if (!o2.esPromocion() && o1.esPromocion()) {
			return -1;
		}

		double diferenciaCosto = o2.getCosto() - o1.getCosto();
		if (diferenciaCosto != 0) {
			return diferenciaCosto > 0 ? 1 : (diferenciaCosto < 0 ? -1 : 0);
		}

		double diferenciaTiempo = o2.getTiempo() - o1.getTiempo();
		return diferenciaTiempo > 0 ? 1 : (diferenciaTiempo < 0 ? -1 : 0);

	}

}
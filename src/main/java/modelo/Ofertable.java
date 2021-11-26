package modelo;
import java.util.HashMap;

public abstract class Ofertable {

	protected double tiempo;
	int tipoDeAtraccion;
	protected double costo;
	protected String nombre;
	int cupo;
	Atraccion[] atraccionesEnPromocion;
	public Object getAtraccionesEnPromocion;

	public Atraccion[] getAtraccionesEnPromocion() {
		return null;
	}

	public double getTiempo() {
		return tiempo;
	}

	public double getCosto() {
		return costo;
	}

	public abstract int getCupo();

	public String getNombre() {
		return nombre;
	}

	public boolean esPromocion() {
		return this instanceof Promocion;

	}

	public abstract void actualizarCupo(Atraccion[] atraccionesEnPromocion);

	public abstract void actualizarCupo();

	public int getTipoAtraccion() {
		return tipoDeAtraccion;
	}

	public abstract boolean hayCupo();

	public abstract double calcularCosto(double datoExtra);

	public boolean contenidoEn(HashMap<Atraccion, String> atraccionComprada) {
		boolean estaContenida = false;
		int i = 0;
		if (this.esPromocion()) {
			while (i < this.getAtraccionesEnPromocion().length && estaContenida == false) {

				estaContenida = atraccionComprada.containsKey(this.getAtraccionesEnPromocion()[i]);

				i++;
			}
			return estaContenida;
		} else {
			return atraccionComprada.containsKey(this);
		}
	}

	public abstract int getIdPromo();

	public abstract int getIdAtraccion();

}
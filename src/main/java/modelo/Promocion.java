package modelo;

public class Promocion extends Ofertable {
	protected int tipoPromo;
	protected int tipoAtraccion;
	protected String nombrePromocion;
	protected Atraccion[] atraccionesEnPromocion;
	protected double datoExtra;
	protected String atracciones;
	protected double costo;
	protected int idPromo;
	protected int cupo;
	protected String[] atraccionesPromo;

	public Promocion(int idPromo, String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			Atraccion[] atraccionesEnPromocion) {
		super();
		this.tipoPromo = tipoPromo;
		this.idPromo = idPromo;
		this.nombrePromocion = nombrePromocion;
		this.atraccionesEnPromocion = atraccionesEnPromocion;
		this.tipoAtraccion = tipoAtraccion;
		this.costo = this.calcularCosto(datoExtra);
	}

	public double calcularDuracion(Atraccion[] atraccionesEnPromocion) {
		double duracionPromo = 0;
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			duracionPromo += atraccionesEnPromocion[i].getTiempo();
		}
		return duracionPromo;
	}

	public Promocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}

	public double calcularCosto(double datoExtra) {

		switch (tipoPromo) {

		case 1:
			this.costo = datoExtra;
			break;
		case 2:
			this.costo = sumaPrecio(getAtraccionesEnPromocion()) * (1 - datoExtra);
			break;
		case 3:

			this.costo = sumaPrecio(getAtraccionesEnPromocion());

			break;
		}

		return costo;
	}

	public double sumaPrecio(Atraccion[] atraccionesPromo) {
		double precioTotal = 0;

		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			precioTotal += atraccionesEnPromocion[i].getCosto();
		}
		return precioTotal;
	}

	public double calcularTiempo(Atraccion[] atraccionesEnPromocion) {
		double tiempoTotal = 0;
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			tiempoTotal += atraccionesEnPromocion[i].getTiempo();
		}
		return tiempoTotal;
	}

	@Override
	public void actualizarCupo() {
		for (int i = 0; i < getAtraccionesEnPromocion().length; i++) {
			getAtraccionesEnPromocion()[i].actualizarCupo();
		}
	}

	public void imprimirPromociones(Promocion[] promociones) {
		for (int i = 0; i < promociones.length; i++) {
			this.toString();
		}
	}

	public double getTiempo() {
		return calcularDuracion(atraccionesEnPromocion);
	}

	public int getTipoAtraccion() {
		return this.tipoAtraccion;
	}

	@Override
	public boolean hayCupo() {
		int i = 0;
		boolean hayCupo = true;
		while (i < this.getAtraccionesEnPromocion().length) {
			if (getAtraccionesEnPromocion()[i].hayCupo())
				i++;
			else {
				hayCupo = false;
				break;
			}
		}
		return hayCupo;
	}

	public int getCupo() {

		for (int i = 0; i < this.getAtraccionesEnPromocion().length; i++) {
			this.cupo += atraccionesEnPromocion[i].getCupo();

		}
		return this.cupo;
	}

	public String getNombre() {
		return this.nombrePromocion;
	}

	public double getCosto() {
		return this.costo;
	}

	public Atraccion[] getAtraccionesEnPromocion() {
		return this.atraccionesEnPromocion;
	}

	public String getAtracciones() {
		return this.atracciones;
	}

	@Override
	public int getIdPromo() {
		// TODO Auto-generated method stub
		return this.idPromo;
	}

	@Override
	public int getIdTipoAtraccion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
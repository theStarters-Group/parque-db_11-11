package modelo;

public class Promocion extends Ofertable {
	int idPromo;
	int tipoPromo;
	int tipoAtraccion;
	String nombrePromocion;
	public Atraccion[] atraccionesEnPromocion;
	double datoExtra;
	public String atracciones;
	double costo;
	String[] atraccionesPromo;

	public String getAtracciones() {
		return atracciones;
	}

	public Atraccion[] getAtraccionesEnPromocion() {
		return atraccionesEnPromocion;
	}

	public Promocion(int idPromo, String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			Atraccion[] atraccionesEnPromocion) {
		super();
		this.idPromo = idPromo;
		this.tipoPromo = tipoPromo;
		this.nombrePromocion = nombrePromocion;
		this.atraccionesEnPromocion = atraccionesEnPromocion;
		this.tipoAtraccion = tipoAtraccion;
	}

	public Promocion(int idPromo, String nombrePromocion, int tipoPromo, int tipoAtraccion, double datoExtra,
			String atracciones) {
		super();
		this.idPromo = idPromo;
		this.tipoPromo = tipoPromo;
		this.nombrePromocion = nombrePromocion;
		this.atraccionesEnPromocion = null;
		this.atracciones = atracciones;
	}

	public double calcularDuracion(Atraccion[] atraccionesEnPromocion) {
		double duracionPromo = 0;
		for (int i = 0; i < atraccionesEnPromocion.length; i++) {
			duracionPromo += atraccionesEnPromocion[i].getTiempo();
		}
		return duracionPromo;
	}

	public double getTiempo() {
		return calcularDuracion(atraccionesEnPromocion);
	}

	public Promocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}

//public Atraccion[] getAtraccionesEnPromocion(String[] a) throws SQLException {

//		a = this.atracciones.split(" ");
	// this.atraccionesEnPromocion = new Atraccion[a.length];

	// int totalAtracciones = AtraccionDAO.countAll();

	// for (int i = 0; i < a.length; i++) {

	// for (Atraccion atraccion : atracciones) {
	// if(Integer.parseInt(a[i])== atracciones.getIdAtraccion()) {

	// atraccionesEnPromocion[i] =
	// AtraccionDAO.getAtraccionPorId(Integer.parseInt(a[i]));
	// }
	// }
	// return atraccionesEnPromocion;

	// }

	@Override
	public double getCosto() {
		return this.costo;
	}
	

	public double calcularCosto(Atraccion[] atraccionesEnPromocion) {

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

	public int getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	@Override
	public boolean hayCupo() {
		int i = 0;
		boolean hayCupo = true;
		while (i < this.getAtraccionesEnPromocion().length) {
			if (getAtraccionesEnPromocion()[i].hayCupo())
				i++;
			else
				hayCupo = false;
		}
		return hayCupo;
	}

	@Override
	public int getIdPromo() {
		
		return idPromo;
	}

	@Override
	public int getIdTipoAtraccion() {
		return 0;
	}

}
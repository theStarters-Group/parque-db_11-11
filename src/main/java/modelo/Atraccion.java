package modelo;

public class Atraccion extends Ofertable {
	private int idAtraccion;
	private String nombre;
	private double costo;
	private double tiempo;
	protected int cupo;
	private int tipo;

	public Atraccion() {
		super();
	}

	public Atraccion(int idAtraccion, String nombre, double costo, double tiempo, Integer cupo, int tipo) {
		this.idAtraccion = idAtraccion;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return this.nombre + ", Duraci�n: " + this.tiempo + "hs, Cupo m�ximo: " + this.cupo + ", Tipo: " + this.tipo
				+ ", Costo Original: $" + this.costo + ".\n";
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getCupo() {
		return this.cupo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public double getCosto() {
		return this.costo;
	}

	@Override
	public int getTipoAtraccion() {

		return tipo;
	}

	@Override
	public void actualizarCupo() {
		this.cupo -= 1;
	}

	@Override
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	@Override
	public int getIdPromo() {

		return 0;
	}

	@Override
	public int getIdAtraccion() {

		return idAtraccion;
	}

	@Override
	public double calcularCosto(double datoExtra) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarCupo(Atraccion[] atraccionesEnPromocion) {
		// TODO Auto-generated method stub

	}

}
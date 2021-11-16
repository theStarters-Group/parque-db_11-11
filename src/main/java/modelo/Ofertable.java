package modelo;

import java.sql.SQLException;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Ofertable {

	protected double tiempo;
	int tipoDeAtraccion;
	protected double costo;
	protected String nombre;
	int cupo;
	Atraccion[] atraccionesEnPromocion;

	public abstract Atraccion[] getAtraccionesEnPromocion();

	public double getTiempo() {
		return tiempo;
	}

	public double getCosto() {
		return costo;
	}

	public int getCupo() {
		return cupo;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean esPromocion() {
		return this instanceof Promocion;

	}

	public abstract void actualizarCupo();

	public int getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public abstract boolean hayCupo();

	public abstract double calcularCosto(double datoExtra);

	public abstract int getIdPromo();

	public abstract int getIdTipoAtraccion();

}
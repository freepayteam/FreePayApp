package com.freepayapp.modelo;

public class Cobro {

	
	private int codigoCobro;
	private int codigoCuenta;
	
	public Cobro(final int codigoCobro, final int codigoCuenta){
		this.codigoCobro=codigoCobro;
		this.codigoCuenta=codigoCuenta;		
	}
	
	
	public int codigoCobro(){
		return codigoCobro;
	}
	
	public int codigoCuenta(){
		return codigoCuenta;
	}
	
}

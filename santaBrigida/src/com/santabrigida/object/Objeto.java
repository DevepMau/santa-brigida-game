package com.santabrigida.object;

import java.awt.image.BufferedImage;

import com.santabrigida.core.Herramientas;

public class Objeto {
	
	private String nombre;
	private String descripcion;
	private int cantidad;
	private int id;
	private boolean stackeable;
	private boolean equipado;
	private int aguante;
	private int manos;
	private int calle;
	private int labia;
	private int facha;
	private int locura;
	private int letalidad;
	
	private boolean gorra;
	private boolean bebida;
	private boolean narcotico;
	private boolean comida;
	private boolean disco;
	private boolean ropa;
	private boolean calzado;
	private boolean especiales;
	private boolean arma;
	
	private String CUI;
	
	private BufferedImage imagen;
	
	public Objeto(int id ,String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = 1;
		this.stackeable = false;
		this.equipado = false;
		this.aguante = 0;
		this.manos = 0;
		this.calle = 0;
		this.labia = 0;
		this.facha = 0;
		this.locura = 0;
		this.letalidad = 0;
		
		this.gorra = false;
		this.bebida = false;
		this.narcotico = false;
		this.comida = false;
		this.disco = false;
		this.ropa = false;
		this.calzado = false;
		this.especiales = false;
		this.arma = false;
	}
	
	public void configurarAtributos(int aguante, int manos, int calle, int labia, int facha, int locura, int letalidad) {
		this.aguante = aguante;
		this.manos = manos;
		this.calle = calle;
		this.labia = labia;
		this.facha = facha;
		this.locura = locura;
		this.letalidad = letalidad;
	}
	
	public void configurarImagen(String path) {
		Herramientas uTool = new Herramientas();
    	try {
    		this.imagen = uTool.configurarImagen("/objetos/"+path, 2);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public void generarCUI() {
		String categoria = "";
		
		if(isGorra()){categoria = "GOR";}
		else if(isBebida()){categoria = "BEB";}
		else if(isNarcotico()){categoria = "NAR";}
		else if(isComida()){categoria = "COM";}
		else if(isDisco()){categoria = "DIS";}
		else if(isRopa()){categoria = "ROP";}
		else if(isCalzado()){categoria = "CAL";}
		else if(isEspeciales()){categoria = "ESP";}
		else if(isArma()){categoria = "ARM";}
		
		this.CUI = "ITM-"+categoria+"-"+this.id;
	}
	
	public void esGorra() {
		this.gorra = true;
	}
	
	public void esBebida() {
		this.bebida = true;
	}
	
	public void esNarcotico() {
		this.narcotico = true;
	}
	
	public void esComida() {
		this.comida = true;
	}
	
	public void esDisco() {
		this.disco = true;
	}
	
	public void esRopa() {
		this.ropa = true;
	}
	
	public void esCalzado() {
		this.calzado = true;
	}
	
	public void esEspecial() {
		this.especiales = true;
	}
	
	public void esArma() {
		this.arma = true;
	}
	
	//GETTERS

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getId() {
		return id;
	}

	public boolean isStackeable() {
		return stackeable;
	}

	public boolean isEquipado() {
		return equipado;
	}

	public int getAguante() {
		return aguante;
	}

	public int getManos() {
		return manos;
	}

	public int getCalle() {
		return calle;
	}

	public int getLabia() {
		return labia;
	}

	public int getFacha() {
		return facha;
	}

	public int getLocura() {
		return locura;
	}
	
	//SETTERS

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStackeable(boolean stackeable) {
		this.stackeable = stackeable;
	}

	public void setEquipado(boolean equipado) {
		this.equipado = equipado;
	}

	public void setAguante(int aguante) {
		this.aguante = aguante;
	}

	public void setManos(int manos) {
		this.manos = manos;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public void setLabia(int labia) {
		this.labia = labia;
	}

	public void setFacha(int facha) {
		this.facha = facha;
	}

	public void setLocura(int locura) {
		this.locura = locura;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}

	public int getLetalidad() {
		return letalidad;
	}

	public void setLetalidad(int letalidad) {
		this.letalidad = letalidad;
	}

	public boolean isGorra() {
		return gorra;
	}

	public boolean isBebida() {
		return bebida;
	}

	public boolean isNarcotico() {
		return narcotico;
	}

	public boolean isComida() {
		return comida;
	}

	public boolean isDisco() {
		return disco;
	}

	public boolean isRopa() {
		return ropa;
	}

	public boolean isCalzado() {
		return calzado;
	}

	public boolean isEspeciales() {
		return especiales;
	}

	public void setGorra(boolean gorra) {
		this.gorra = gorra;
	}

	public void setBebida(boolean bebida) {
		this.bebida = bebida;
	}

	public void setNarcotico(boolean narcotico) {
		this.narcotico = narcotico;
	}

	public void setComida(boolean comida) {
		this.comida = comida;
	}

	public void setDisco(boolean disco) {
		this.disco = disco;
	}

	public void setRopa(boolean ropa) {
		this.ropa = ropa;
	}

	public void setCalzado(boolean calzado) {
		this.calzado = calzado;
	}

	public void setEspeciales(boolean especiales) {
		this.especiales = especiales;
	}

	public String getCUI() {
		return CUI;
	}

	public void setCUI(String cUI) {
		CUI = cUI;
	}

	public boolean isArma() {
		return arma;
	}

	public void setArma(boolean arma) {
		this.arma = arma;
	}

}

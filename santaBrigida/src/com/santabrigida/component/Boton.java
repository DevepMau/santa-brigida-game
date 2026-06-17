package com.santabrigida.component;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.santabrigida.core.PanelDeJuego;

public class Boton {
	
	private final int DELAY = 20;
	
	PanelDeJuego pdj;
	private String texto;
	private boolean activo;
	private boolean resaltado;
	private boolean presionado;
	private Rectangle area;
	
	private int timer;
	private boolean delayOK;
	
	private boolean clickOK;
	
	private BufferedImage icono;
	
	public Boton(PanelDeJuego pdj) {
		this.pdj = pdj;
		this.texto = "";
		this.activo = false;
		this.resaltado = false;
		this.presionado = false;
		this.area = null;
		this.timer = 0;
		this.delayOK = true;
		this.clickOK = true;
	}
	
	public void actualizar() {
		int cursorX = pdj.raton.posX;
		int cursorY = pdj.raton.posY;
		boolean click = pdj.raton.CLICK;
		
		activarDelay();
		
		habilitarClick(click);
		
		detectarRatonColision(cursorX, cursorY);
		
		detectarRatonPresionado(click);

	}
	
	private void detectarRatonColision(int cursorX, int cursorY) {
		if(activo && area.contains(cursorX, cursorY)) {
			resaltado = true;
		}
		else {
			resaltado = false;
		}
	}
	
	private void detectarRatonPresionado(boolean click) {
		if(resaltado && click && delayOK && clickOK) {
			presionado = true;
			clickOK = false;
			timer = DELAY;
		}
		else {
			presionado = false;
		}
	}
	
	private void activarDelay() {
		if(timer > 0) {
			delayOK = false;
			timer--;
		}
		else {
			delayOK = true;
		}
	}
	
	private void habilitarClick(boolean click) {
		if(!click) {
			clickOK = true;
		}
	}
	
	public void inicializar(int x, int y, int ancho, int alto) {
		this.activo = true;
		this.area = new Rectangle(x, y, ancho, alto);
	}
	
	public void reposicionar(int x, int y) {
		this.area.setLocation(x, y);
	}
	
	public void redimencionar(int ancho, int alto) {
		this.area.setSize(ancho, alto);
	}

	//GETTERS
	
	public PanelDeJuego getPdj() {
		return pdj;
	}

	public String getTexto() {
		return texto;
	}

	public boolean isActivo() {
		return activo;
	}

	public boolean isResaltado() {
		return resaltado;
	}

	public boolean isPresionado() {
		return presionado;
	}

	public Rectangle getArea() {
		return area;
	}

	//SETTERS

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setResaltado(boolean resaltado) {
		this.resaltado = resaltado;
	}

	public void setPresionado(boolean presionado) {
		this.presionado = presionado;
	}

	public void setArea(Rectangle area) {
		this.area = area;
	}

	public BufferedImage getIcono() {
		return icono;
	}

	public void setIcono(BufferedImage icono) {
		this.icono = icono;
	}

}

package com.santabrigida.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.santabrigida.combat.Formacion;
import com.santabrigida.entity.Entidad;
import com.santabrigida.object.Objeto;

public class FormacionUI {
	Graphics2D g2;
	Formacion model;
	
	BotonUI btnSiguiente;
	BotonUI btnAnterior;
	BotonUI btnAceptar;
	BotonUI btnGuardar;
	
	BotonIconoUI[] botones = new BotonIconoUI[6];
	
	public FormacionUI(Formacion model, Graphics2D g2) {
		this.g2 = g2;
		this.model = model;
		inicializarBotonesUI();
	}
	
	public void dibujar(float escala, int indiceFotograma) {	
		dibujarPanel(g2);
		dibujarInfoPersonaje(g2, escala, indiceFotograma);
		dibujarPlazas(g2, escala);
		dibujarControles(g2, escala);
	}
	
	public void dibujarPanel(Graphics2D g2) {
		int x = 48;
		int y = 48;
		int ancho = 18*48;
		int alto = 10*48;
		int ajusteEfectoAltura = 8;
		int redondeo = 10;
		
		g2.setColor(TemaUI.UI_SOMBRA);
		g2.fillRoundRect(x + ajusteEfectoAltura, y + ajusteEfectoAltura, ancho, alto, redondeo, redondeo);
		
		g2.setColor(TemaUI.UI_PRIMARIO);
		g2.fillRoundRect(x, y, ancho, alto, redondeo, redondeo);
		
		g2.setColor(TemaUI.UI_LUZ);
		g2.fillRoundRect(x + ajusteEfectoAltura, y + ajusteEfectoAltura, ancho - 16, 16, redondeo, redondeo);
		g2.fillRoundRect(x + ajusteEfectoAltura, y + ajusteEfectoAltura, 16, alto - 16, redondeo, redondeo);
	}
	
	private void inicializarBotonesUI() {
		this.btnSiguiente = new BotonUI(model.getBtnSiguiente(), g2);
		this.btnAnterior = new BotonUI(model.getBtnAnterior(), g2);
		this.btnAceptar = new BotonUI(model.getBtnAceptar(), g2);
		this.btnGuardar = new BotonUI(model.getBtnGuardar(), g2);
		for(int i = 0; i<botones.length; i++) {
			botones[i] = new BotonIconoUI(model.getBotones().get(i), g2);
		}
	}
	
	private void dibujarControles(Graphics2D g2, float escala) {
		btnAnterior.dibujar(escala);
		btnSiguiente.dibujar(escala);
		btnAceptar.dibujar(escala);
		btnGuardar.dibujar(escala);
	}
	
	public void dibujarPlazas(Graphics2D g2, float escala) {
		int x = 9*48 + 24;
		int y = 2*48;
		int ancho = 9*48;
		int alto = 8*48;
		int redondeo = 10;
		
		int xTexto = x + 2*48;
		int xArenga = xTexto + 3*48 + 28;
		int yFrente = y + 64;
		int yBanquilloArenga = yFrente + 3*48;
		
		g2.setColor(TemaUI.UI_NEGRO);
		g2.fillRoundRect(x, y, ancho, alto, redondeo, redondeo);
		
		g2.setColor(TemaUI.UI_TEXTO);
		g2.drawRoundRect(x, y, ancho, alto, redondeo, redondeo);
		
		g2.setFont(TemaUI.getMarumonica(TemaUI.TAM_BOTON, escala));
		g2.drawString("----------FRENTE----------", xTexto, yFrente);
		g2.drawString("---BANQUILLO---", xTexto, yBanquilloArenga);
		g2.drawString("-ARENGA-", xArenga, yBanquilloArenga);
		
		for(BotonIconoUI btn : botones) {
			btn.dibujar(escala);
		}
		
	}
	
	private void dibujarInfoPersonaje(Graphics2D g2, float escala, int indiceFotograma) {
		int xCampo = 3*48;
		int xDato = 5*48;
		int xMod = 5*48 + 24;
		int y = 6*48 + 24;
		int ancho = 6*48;
		int alto = 8*48;
		int separacion = 24;
		int redondeo = 10;
		Entidad entidad = model.getPersonajeActual();
		
		g2.setColor(TemaUI.UI_NEGRO);
		g2.fillRoundRect(2*48 + 24, 2*48, ancho, alto, redondeo, redondeo);
		
		g2.setColor(TemaUI.UI_TEXTO);
		g2.drawRoundRect(2*48 + 24, 2*48, ancho, alto, redondeo, redondeo);
	
		g2.setFont(TemaUI.getMarumonica(TemaUI.TAM_TEXTO, escala));
		
		g2.drawString("Nombre: ", xCampo, y - 10);
		g2.drawString("Aguante: ", xCampo, y + (separacion));
		g2.drawString("Manos: ", xCampo, y + (separacion*2));
		g2.drawString("Calle: ", xCampo, y + (separacion*3));
		g2.drawString("Labia: ", xCampo, y + (separacion*4));
		g2.drawString("Facha: ", xCampo, y + (separacion*5));
		g2.drawString("Locura: ", xCampo, y + (separacion*6));
		
		g2.setColor(TemaUI.UI_STAT);
		
		g2.drawString("" + entidad.getNombre(), xDato, y - 10);
		g2.drawString("" + entidad.getAguante(), xDato, y + (separacion));
		g2.drawString("" + entidad.getManos(), xDato, y + (separacion*2));
		g2.drawString("" + entidad.getCalle(), xDato, y + (separacion*3));
		g2.drawString("" + entidad.getLabia(), xDato, y + (separacion*4));
		g2.drawString("" + entidad.getFacha(), xDato, y + (separacion*5));
		g2.drawString("" + entidad.getLocura(), xDato, y + (separacion*6));
		
		dibujarStatMod(entidad.getAguanteObj(), xMod, y + (separacion));
		dibujarStatMod(entidad.getManosObj(), xMod, y + (separacion*2));
		dibujarStatMod(entidad.getCalleObj(), xMod, y + (separacion*3));
		dibujarStatMod(entidad.getLabiaObj(), xMod, y + (separacion*4));
		dibujarStatMod(entidad.getFachaObj(), xMod, y + (separacion*5));
		dibujarStatMod(entidad.getLocuraObj(), xMod, y + (separacion*6));
		
		g2.drawImage(entidad.getImagenes()[indiceFotograma], 3*48 + 24, 3*48 - 24, null);
		
		dibujarObjetos(entidad);

	}
	
	private void dibujarObjetos(Entidad entidad) {
		int x = 6*48 + 24;
		int y = 6*48;
		int tamano = 64;
		int redondeo = 5;
		int separacion = 2*48;
		Objeto objeto1 = model.getObjeto1();
		Objeto objeto2 = model.getObjeto2();
		
		g2.setColor(Color.black);
		g2.fillRoundRect(x, y, tamano, tamano, redondeo, redondeo);
		g2.fillRoundRect(x, y + separacion, tamano, tamano, redondeo, redondeo);
		
		g2.setColor(TemaUI.UI_TEXTO);
		g2.drawRoundRect(x, y, tamano, tamano, redondeo, redondeo);
		g2.drawRoundRect(x, y + separacion, tamano, tamano, redondeo, redondeo);
		
		if(objeto1 != null) {
			g2.drawImage(objeto1.getImagen(), x, y, null);
		}
		if(objeto2 != null) {
			g2.drawImage(objeto2.getImagen(), x, y + separacion, null);
		}
	}
	
	private void dibujarStatMod(int valor, int x, int y) {
		if (valor > 0) {
			g2.setColor(TemaUI.UI_BUFF);
			g2.drawString("+" + valor, x, y);
		} else if (valor < 0) {
			g2.setColor(TemaUI.UI_DEBUFF);
			g2.drawString("" + valor, x, y);
		}
	}

}

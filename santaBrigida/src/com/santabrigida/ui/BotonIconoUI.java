package com.santabrigida.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.santabrigida.component.Boton;

public class BotonIconoUI {
	Graphics2D g2;
	public Boton model;
	
	
	public BotonIconoUI(Boton model, Graphics2D g2) {
		this.g2 = g2;
		this.model = model;
	}
	
	public void dibujar(float escalado) {
		int x = model.getArea().x;
		int y = model.getArea().y;
		int ancho = model.getArea().width;
		int alto = model.getArea().height;
		
		int ajusteAltura = 4;
		
		g2.setColor(TemaUI.UI_SOMBRA);
		g2.fillRoundRect(x + ajusteAltura, y + ajusteAltura, ancho, alto, 8, 8);
		
		g2.setColor(Color.black);
		g2.fillRoundRect(x, y, ancho, alto, 8, 8);
		
		g2.setColor(TemaUI.UI_TEXTO);
		g2.drawRoundRect(x, y, ancho, alto, 8, 8);

		Stroke anterior = g2.getStroke();
		g2.setStroke(new BasicStroke(3));

		if(model.isPresionado()) {
			g2.setColor(TemaUI.UI_TEXTO);
			g2.fillRoundRect(x, y, ancho, alto, 8, 8);
		}
		else if(model.isResaltado()) {
			g2.setColor(TemaUI.UI_DORADO);
			g2.drawRoundRect(x, y, ancho, alto, 8, 8);
		}
		
		g2.setStroke(anterior);
		
		g2.drawImage(model.getIcono(), x, y, null);
		
	}

}

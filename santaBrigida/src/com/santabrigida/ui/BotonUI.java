package com.santabrigida.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.santabrigida.core.Boton;

public class BotonUI {
	
	Graphics2D g2;
	public Boton model;
	
	public BotonUI(Boton model, Graphics2D g2) {
		this.g2 = g2;
		this.model = model;
	}
	
	public void dibujar(float escalado) {
		int ajusteX = (int)(8 * escalado);
		int ajusteY = (int)(24 * escalado);
		
		int x = model.getArea().x;
		int y = model.getArea().y;
		int ancho = model.getArea().width;
		int alto = model.getArea().height;
		int linea = 16;
		
		g2.setColor(TemaUI.COL_SHADOW);
		g2.fillRect(x, y, ancho, alto + linea);
		g2.fillRect(x, y, ancho + linea, alto);
		
		g2.setColor(TemaUI.COL_BORDER);
		g2.fillRect(x, y - (linea/2), ancho, alto + linea);
		g2.fillRect(x - (linea/2), y, ancho + linea, alto);
		
		if(model.isPresionado()) {
			g2.setColor(Color.white);
			g2.fillRect(x, y, ancho, alto);
		}
		else if(model.isResaltado()) {
			g2.setColor(Color.GRAY);
			g2.fillRect(x, y, ancho, alto);
		}
		else {
			g2.setColor(Color.black);
			g2.fillRect(x, y, ancho, alto);
		}
		
		g2.setColor(TemaUI.COL_TEXT);
		g2.setFont(TemaUI.getMarumonica(TemaUI.TAM_BOTON, escalado));
		g2.drawString(model.getTexto(), x + (linea/2) + (linea/4) + ajusteX, y + ajusteY);
		
	}

}

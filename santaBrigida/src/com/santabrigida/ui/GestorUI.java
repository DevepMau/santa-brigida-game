package com.santabrigida.ui;

import java.awt.Graphics2D;

import com.santabrigida.core.PanelDeJuego;

public class GestorUI {
	
	PanelDeJuego pdj;
	Graphics2D g2;
	
	public float escala;
	
	public GestorUI(PanelDeJuego pdj) {
		this.pdj = pdj;
	}
	
	public void dibujar(Graphics2D g2) {
		this.g2 = g2;
		
		this.escala = Math.min(
		        (float) pdj.getWidth() / pdj.anchoDePantalla,
		        (float) pdj.getHeight() / pdj.altoDePantalla
		    );
		
		escala = Math.min(escala, 1.10f);
		
		BotonUI btnCerrar = new BotonUI(pdj.btnCerrar, g2);
		BotonUI btnMaximizar = new BotonUI(pdj.btnMaximizar, g2);
		BotonUI btnMinimizar = new BotonUI(pdj.btnMinimizar, g2);
		
		switch(pdj.modoJuego) {
		case 3 -> dibujarUIFormacion(g2);
		}
		
		btnMinimizar.dibujar(escala);
		btnMaximizar.dibujar(escala);
		btnCerrar.dibujar(escala);
	}
	
	public void dibujarTabla(Graphics2D g2) {
		int baldosa = pdj.tamañoDeBaldosa;
		int x = 0;
		int y = 0;
		
		g2.setColor(TemaUI.COL_BORDER);
		for(int i = 0; i < pdj.maxFilaDePantalla; i++) {
			g2.drawRect(x, y, baldosa, baldosa);
			
			
			for(int j = 0; j < pdj.maxColDePantalla; j++) {
				g2.drawRect(x, y, baldosa, baldosa);
				x += baldosa;
			}
			x = 0;
			y += baldosa;
		}
	}
	
	public void dibujarUIFormacion(Graphics2D g2){
		dibujarTabla(g2);
		FormacionUI formacion = new FormacionUI(pdj.formacion, g2);
		formacion.dibujar(escala, pdj.fotograma);
	}

}

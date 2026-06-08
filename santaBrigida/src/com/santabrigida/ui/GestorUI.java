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
		
		btnMinimizar.dibujar(escala);
		btnMaximizar.dibujar(escala);
		btnCerrar.dibujar(escala);
	}

}

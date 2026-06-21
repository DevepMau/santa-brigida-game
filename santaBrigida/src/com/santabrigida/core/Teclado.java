package com.santabrigida.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	PanelDeJuego pdj;
	public boolean ENTER, ESCAPE;
	public boolean Z, X, C, IZQUIERDA, DERECHA, ARRIBA, ABAJO;
	//DEBUG
	boolean comprobarTiempoDeDibujado = false;
	
	public Teclado(PanelDeJuego pdj) {
		this.pdj = pdj;
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int codigo = e.getKeyCode();
		
		//MODO JUEGO
		if(pdj.modoJuego == pdj.EXPLORACION || pdj.modoJuego == pdj.FORMACION || pdj.modoJuego == pdj.COMBATE) {
			
			//CONTROLES ACCION
			if(codigo == KeyEvent.VK_Z) {
				Z = true;
			}
			if(codigo == KeyEvent.VK_X) {
				X = true;
			}
			if(codigo == KeyEvent.VK_C) {
				C = true;
			}
			//CONTROLES MOVIMIENTO/NAVEGACION
			if(codigo == KeyEvent.VK_LEFT) {
				IZQUIERDA = true;
			}
			if(codigo == KeyEvent.VK_RIGHT) {
				DERECHA = true;
			}
			if (codigo == KeyEvent.VK_UP) {
				ARRIBA = true;
			}
			if (codigo == KeyEvent.VK_DOWN) {
				ABAJO = true;
			}
			//OTROS CONTROLES
			if(codigo == KeyEvent.VK_P) {
				pdj.modoJuego = pdj.PAUSA;
			}
			if(codigo == KeyEvent.VK_ENTER) {
				ENTER = true;
			}
			if(codigo == KeyEvent.VK_ESCAPE) {
				ESCAPE = true;
			}
			//MODO DEBUG
			if(codigo == KeyEvent.VK_T) {
				if(comprobarTiempoDeDibujado == false) {
					comprobarTiempoDeDibujado = true;
				}
				else if(comprobarTiempoDeDibujado == true) {
					comprobarTiempoDeDibujado = false;
				}
			}
		}
		//MODO PAUSA
		else if(pdj.modoJuego == pdj.PAUSA) {
			if(codigo == KeyEvent.VK_P) {
				pdj.modoJuego = pdj.modoJuego;
			}
		}
		//MODO DIALOGO
		else if(pdj.modoJuego == pdj.INICIO) {
			if(codigo == KeyEvent.VK_ENTER) {
				pdj.modoJuego = pdj.EXPLORACION;
			}
		}
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int codigo = e.getKeyCode();

		if(codigo == KeyEvent.VK_Z) {
			Z = false;
		}
		if(codigo == KeyEvent.VK_X) {
			X = false;
		}
		if(codigo == KeyEvent.VK_C) {
			C = false;
		}
		if(codigo == KeyEvent.VK_LEFT) {
			IZQUIERDA = false;
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			DERECHA = false;
		}
		if(codigo == KeyEvent.VK_UP) {
			ARRIBA = false;
		}
		if(codigo == KeyEvent.VK_DOWN) {
			ABAJO = false;
		}
		if(codigo == KeyEvent.VK_ENTER) {
			ENTER = false;
		}
		if(codigo == KeyEvent.VK_ESCAPE) {
			ESCAPE = false;
		}

	}
}
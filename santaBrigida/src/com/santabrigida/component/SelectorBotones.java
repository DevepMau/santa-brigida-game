package com.santabrigida.component;

import java.util.ArrayList;

import com.santabrigida.core.PanelDeJuego;

public class SelectorBotones extends Boton {
	
	private ArrayList<Boton> botones;
	private boolean botonOK;
	private boolean sonidoOk;
	private int indice;

	public SelectorBotones(PanelDeJuego pdj, ArrayList<Boton> botones) {
		super(pdj);
		this.botones = botones;
		this.botonOK = true;
		this.sonidoOk = true;
		this.indice = 0;
	}
	
	public void actualizar() {
		int cursorX = this.getPdj().raton.posX;
		int cursorY = this.getPdj().raton.posY;
		
		super.actualizar();
		habilitarBotones();
		if(botones != null) {
			cambiarBoton(cursorX, cursorY);
		}
		else {
			this.setArea(botones.get(0).getArea());
		}
		
		
	}
	
	private void cambiarBoton(int cursorX, int cursorY) {
		if(this.getPdj().teclado.DERECHA && botonOK) {
			botonOK = false;
			siguiente();
			this.getPdj().ReproducirSE(0);
		}
		if(this.getPdj().teclado.IZQUIERDA && botonOK) {
			botonOK = false;
			anterior();
			this.getPdj().ReproducirSE(0);
		}
		boolean sobreBoton = false;

		for(int i = 0; i < botones.size(); i++) {
		    if(botones.get(i).getArea().contains(cursorX, cursorY)) {
		        sobreBoton = true;
		        indice = i;
		        if(sonidoOk) {
		            this.getPdj().ReproducirSE(0);
		            sonidoOk = false;
		        }
		        break;
		    }
		}
		if(!sobreBoton) {
		    sonidoOk = true;
		}
		botones.get(indice).setResaltado(true);
	}
	
	private void habilitarBotones() {
		if(!this.getPdj().teclado.DERECHA && !this.getPdj().teclado.IZQUIERDA) {
			botonOK = true;
		}
	}
	
	private void siguiente() {
		if((indice + 1) < botones.size()) {
			indice++;
		}
		else {
			indice = 0;
		}
	}
	
	private void anterior() {
		if((indice - 1) < 0) {
			indice = botones.size() - 1;
		}
		else {
			indice--;
		}
	}

	public ArrayList<Boton> getBotones() {
		return botones;
	}

	public void setBotones(ArrayList<Boton> botones) {
		this.botones = botones;
	}

	public boolean isBotonOK() {
		return botonOK;
	}

	public int getIndice() {
		return indice;
	}

	public void setBotonOK(boolean botonOK) {
		this.botonOK = botonOK;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

}

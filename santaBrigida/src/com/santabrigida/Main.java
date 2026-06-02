package com.santabrigida;

import javax.swing.JFrame;

import com.santabrigida.core.PanelDeJuego;

public class Main {

	public static void main(String[] args) {

		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setTitle("Juego 2D");

		PanelDeJuego pdj = new PanelDeJuego();
		ventana.add(pdj);

		ventana.pack();

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		pdj.configuracionDeJuego();
		pdj.iniciarHiloDeJuego();

	}
}
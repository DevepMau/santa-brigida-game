package com.santabrigida.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.santabrigida.ui.GestorUI;

public class PanelDeJuego extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	// CONFIGURACIÓN DE PANTALLA
	final int tamañoOriginalDeBaldosa = 16;
	final int escala = 3;

	public final int tamañoDeBaldosa = tamañoOriginalDeBaldosa * escala;
	public final int maxColDePantalla = 20;
	public final int maxFilaDePantalla = 12;
	public final int anchoDePantalla = tamañoDeBaldosa * maxColDePantalla;
	public final int altoDePantalla = tamañoDeBaldosa * maxFilaDePantalla;
	
	//CONFIGURACION DEL MUNDO
	public final int maxColDeMundo = 20;
	public final int maxFilaDeMundo = 12;
	public final int anchoMundo = tamañoDeBaldosa * maxColDeMundo;
	public final int altoMundo = tamañoDeBaldosa * maxFilaDeMundo;

	//SISTEMA
	public Teclado teclado = new Teclado(this);
	Raton raton = new Raton(this);
	Sonido musica = new Sonido();
	Sonido se = new Sonido();
	GestorUI ui = new GestorUI(this);
	Thread hiloDeJuego;
	
	//ENTIDADES Y OBJETOS
	public Boton btnCerrar = new Boton(this);
	public Boton btnMaximizar = new Boton(this);
	public Boton btnMinimizar = new Boton(this);

	//ESTADO DE JUEGO
	public int estadoDeJuego;
	
	public final int modoTitulo = 0;
	public final int modoJuego = 1;
	public final int modoPausa = 2;
	public final int modoDialogo = 3;
	public final int modoCombate = 4;
	
	// FPS
	int FPS = 60;

	public PanelDeJuego() {

		this.setPreferredSize(new Dimension(anchoDePantalla, altoDePantalla));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(teclado);
		this.setFocusable(true);
		this.addMouseListener(raton);
	    this.addMouseMotionListener(raton);

	}
	
	public void configuracionDeJuego() {
		estadoDeJuego = modoCombate;
		
		btnCerrar.inicializar(this.anchoDePantalla - 60, 10, 50, 30);
		btnCerrar.setTexto("X");
		
		btnMaximizar.inicializar(this.anchoDePantalla - 128, 10, 50, 30);
		btnMaximizar.setTexto("⬜");
		
		btnMinimizar.inicializar(this.anchoDePantalla - 196, 10, 50, 30);
		btnMinimizar.setTexto("-");
	}

	public void iniciarHiloDeJuego() {
		hiloDeJuego = new Thread(this);
		hiloDeJuego.start();

	}
	
	public void reproducirMusica(int i) {

		musica.cargarArchivo(i);
		musica.reproducir();
		musica.repetir();

	}

	public void detenerMusica() {

		musica.detener();

	}

	public void ReproducirSE(int i) {

		se.cargarArchivo(i);
		se.reproducir();

	}

	@Override
	public void run() {

		double intervaloDeDibujo = 1000000000 / FPS;
		double delta = 0;
		long ultimoTiempo = System.nanoTime();
		long tiempoActual;

		while(hiloDeJuego != null) {

			tiempoActual = System.nanoTime();
			delta += (tiempoActual - ultimoTiempo) / intervaloDeDibujo;
			ultimoTiempo = tiempoActual;

			if(delta >= 1) {
				actualizar();
				repaint();
				delta--;
			}
		}

	}

	public void actualizar() {

		btnCerrar.actualizar();
		btnMaximizar.actualizar();
		btnMinimizar.actualizar();
		
		if(btnCerrar.isPresionado()) {
			System.exit(0);
        }

        if(btnMaximizar.isPresionado()) {
        	JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

            if(ventana != null) {

                if ((ventana.getExtendedState() & JFrame.MAXIMIZED_BOTH)
                        == JFrame.MAXIMIZED_BOTH) {

                    ventana.setExtendedState(JFrame.NORMAL);

                } else {

                    ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);

                }
            }
        }

        if(btnMinimizar.isPresionado()) {
        	JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);

            if(ventana != null) {
            	this.raton.CLICK =false;
                ventana.setState(JFrame.ICONIFIED);
            }
            
        }
		
		if(estadoDeJuego == modoJuego) {
			
			//JUGADR
			
			//NPC
		}
		if(estadoDeJuego == modoPausa) {
			
		}
		if(estadoDeJuego == modoCombate) {
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

	    // Tamaño real del panel
	    int anchoReal = getWidth();
	    int altoReal = getHeight();

	    // Escala dinámica
	    double escalaX = (double) anchoReal / anchoDePantalla;
	    double escalaY = (double) altoReal / altoDePantalla;

	    // Mantener proporción (opcional pero recomendado)
	    double escala = Math.min(escalaX, escalaY);

	    int offsetX = (int) ((anchoReal - anchoDePantalla * escala) / 2);
	    int offsetY = (int) ((altoReal - altoDePantalla * escala) / 2);
	    
	    raton.setEscala(escala);
	    raton.setOffset(offsetX, offsetY);

	    g2.translate(offsetX, offsetY);

	    g2.scale(escala, escala);
		
	    //DIBUJAR A PARTIR DE AQUI
	    g2.setColor(Color.white);
	    g2.drawRect(0, 0, this.anchoDePantalla, this.altoDePantalla);
		
		//DEBUG
		long drawStart = 0;
		if(teclado.comprobarTiempoDeDibujado == true) {
			drawStart = System.nanoTime();
		}
		//COMBATE
		if(estadoDeJuego == modoCombate) {
		}
		//PANTALLA DE TITULO
		if(estadoDeJuego == modoTitulo) {
		}
		//OTROS
		else {
			
			//BALDOSAS
			
			//OBJETOS
			
			//JUGADOR Y NPC
			
			//UI
			ui.dibujar(g2);
			
		}

		//DEBUG
		if(teclado.comprobarTiempoDeDibujado == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
		}

		g2.dispose();

	}
}
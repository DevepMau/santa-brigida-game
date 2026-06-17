package com.santabrigida.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.santabrigida.combat.Formacion;
import com.santabrigida.component.Boton;
import com.santabrigida.entity.Entidad;
import com.santabrigida.enums.Nombre;
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
	public Raton raton = new Raton(this);
	Sonido musica = new Sonido();
	Sonido se = new Sonido();
	GestorUI ui = new GestorUI(this);
	public GestorDeRecursos gdr = new GestorDeRecursos(this);
	public Formacion formacion = new Formacion(this);
	Thread hiloDeJuego;
	
	//ENTIDADES Y OBJETOS
	public Boton btnCerrar = new Boton(this);
	public Boton btnMaximizar = new Boton(this);
	public Boton btnMinimizar = new Boton(this);
	public List<Entidad> banda = new ArrayList<>();
	
	

	//ESTADO DE JUEGO
	public int modoJuego;
	
	public final int INICIO = 0;
	public final int COMBATE = 1;
	public final int EXPLORACION = 2;
	public final int FORMACION = 3;
	public final int PAUSA = 4;
	
	//VARIABLES GLOBALES
	public final int VEL_ANIMACION = 8;
	public final int MAX_FOTOGRAMAS = 4;
	public int fotograma = 0;
	public int timerAnimacion = VEL_ANIMACION;
	
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
		modoJuego = FORMACION;
		
		btnCerrar.inicializar(this.anchoDePantalla - 60, 10, 50, 30);
		btnCerrar.setTexto("X");
		
		btnMaximizar.inicializar(this.anchoDePantalla - 128, 10, 50, 30);
		btnMaximizar.setTexto("⬜");
		
		btnMinimizar.inicializar(this.anchoDePantalla - 196, 10, 50, 30);
		btnMinimizar.setTexto("-");
		
		banda.add(gdr.getPersonaje(Nombre.MAYKI));
		banda.add(gdr.getPersonaje(Nombre.KAYAN));
		banda.add(gdr.getPersonaje(Nombre.CHAVO));
		banda.add(gdr.getPersonaje(Nombre.TORNI));
		banda.add(gdr.getPersonaje(Nombre.NICO));
		banda.add(gdr.getPersonaje(Nombre.CECI));
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
		accionBtnCerrar();
		accionBtnMaxim();
		accionBtnMinim();
		
		if(modoJuego == INICIO) {	
		}
		if(modoJuego == EXPLORACION) {
		}
		if(modoJuego == COMBATE) {
		}
		if(modoJuego == FORMACION) {
			iniciarAnimacion();
			formacion.setEquipo(banda);
			formacion.actualizar();
		}
		if (modoJuego == PAUSA) {
		}
	}
	
	public void iniciarAnimacion() {
		if(timerAnimacion > 0) {
			timerAnimacion--;
		}
		else {
            timerAnimacion = VEL_ANIMACION;
            actualizarFotograma();
        }
	}
	
	private void actualizarFotograma() {
		if((fotograma + 1) < MAX_FOTOGRAMAS) {
			fotograma++;
		} else {
			fotograma = 0;
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
		if(modoJuego == COMBATE) {
		}
		//PANTALLA DE TITULO
		if(modoJuego == INICIO) {
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
	
	//METODOS VARIOS
	
	private void accionBtnCerrar() {
		btnCerrar.actualizar();
		if(btnCerrar.isPresionado()) {
			System.exit(0);
        }
	}
	
	private void accionBtnMaxim() {
		btnMaximizar.actualizar();
        if(btnMaximizar.isPresionado()) {
        	JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            if(ventana != null) {
                if ((ventana.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ventana.setExtendedState(JFrame.NORMAL);
                } else {
                    ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        }
	}
	
	private void accionBtnMinim() {
		btnMinimizar.actualizar();
        if(btnMinimizar.isPresionado()) {
        	JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            if(ventana != null) {
            	this.raton.CLICK =false;
                ventana.setState(JFrame.ICONIFIED);
            }  
        }
	}
}
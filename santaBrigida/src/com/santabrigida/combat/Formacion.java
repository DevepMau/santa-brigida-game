package com.santabrigida.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.santabrigida.component.Boton;
import com.santabrigida.component.SelectorBotones;
import com.santabrigida.core.GestorDeRecursos;
import com.santabrigida.core.PanelDeJuego;
import com.santabrigida.entity.Entidad;
import com.santabrigida.enums.Nombre;
import com.santabrigida.object.Objeto;

public class Formacion {
	
	PanelDeJuego pdj;
	
	private Boton btnSiguiente;
	private Boton btnAnterior;
	private Boton btnAceptar;
	private Boton btnGuardar;
	private ArrayList<Boton> botones;
	private SelectorBotones selector;
	
	private Entidad[] equipo;
	private HashMap<Nombre, Integer> posiciones;
	
	private String nombre;
	private int aguante;
	private int manos;
	private int calle;
	private int labia;
	private int facha;
	private int locura;
	
	private Objeto objeto1;
	private Objeto objeto2;
	
	private List<Entidad> personajes;
	private Entidad personajeActual;
	
	private int indice;
	private boolean botonOK;
	
	public Formacion(PanelDeJuego pdj) {
		this.pdj = pdj;
		this.indice = 0;
		this.botonOK = true;
		this.equipo = new Entidad[6];
		this.posiciones = new HashMap<>();
		configurarBotonesSlot();
		configurarBotonesControl();
	}
	
	public void actualizar() {
		cambiarPersonaje();
		habilitarBotones();
		actualizarBotones();

	}
	
	public void setEquipo(List<Entidad> equipo) {
		if(equipo != null) {
			this.personajes = equipo;
			cargarDatosPersonaje(equipo.get(indice));
		}
	}
	
	//METODOS VARIOS
	
	private void configurarBotonesControl() {
		int x = 80;
		int y = 2*48;
		int ancho = 32;
		int alto = 8*48;
		int separacion = 7*48;
		int ajusteEfectoAltura = -4;
		int xAceptar = 4*48;
		int yAceptar = 10*48 + 8;
		int anchoAceptar = 3*48;
		int altoAceptar = 32;
		int separacionAceptarGuardar = 8*48 + 24;

		this.btnAnterior = new Boton(pdj);
		this.btnSiguiente = new Boton(pdj);
		this.btnAceptar = new Boton(pdj);
		this.btnGuardar = new Boton(pdj);
		
		this.btnAnterior.inicializar(x, y + ajusteEfectoAltura, ancho, alto);
		this.btnSiguiente.inicializar(x + separacion, y + ajusteEfectoAltura, ancho, alto);
		this.btnAceptar.inicializar(xAceptar, yAceptar, anchoAceptar, altoAceptar);
		this.btnGuardar.inicializar(xAceptar + separacionAceptarGuardar, yAceptar, anchoAceptar, altoAceptar);
		
		this.btnAnterior.setTexto("<");
		this.btnSiguiente.setTexto(">");
		this.btnAceptar.setTexto("ACEPTAR");
		this.btnGuardar.setTexto("GUARDAR");
	}
	
	private void actualizarBotones() {
		this.btnAnterior.actualizar();
		this.btnSiguiente.actualizar();
		this.btnAceptar.actualizar();
		this.btnGuardar.actualizar();
		
		for(int i = 0; i < equipo.length; i++) {
			botones.get(i).actualizar();
			if(botones.get(i).isPresionado()) {	
				eliminarPosicion(i);
				establecerPosicion(i);
			}
		}
		
		this.selector.actualizar();
		
		if(btnGuardar.isPresionado()) {
			System.out.println("Guardando equipo...");
			System.out.println("//////////////////////////");
			for(Entidad miembro : equipo) {
				if (miembro != null) {
					System.out.println("Miembro: " + miembro.getNombre() + ", posicion: " + posiciones.get(miembro.getNombre()));
				} 
				else {
					System.out.println("Miembro: Vacío");
				}
			}
			System.out.println("//////////////////////////");
			pdj.modoJuego = pdj.EXPLORACION;
		}
		
		if(btnAceptar.isPresionado() || (pdj.teclado.ENTER && botonOK)) {
			botonOK = false;
			eliminarPosicion(selector.getIndice());
			establecerPosicion(selector.getIndice());
		}
	}
	
	private void eliminarPosicion(int i) {
		Nombre nombreActual = this.personajeActual.getNombre();
		if(equipo[i] != null) {
			Nombre nombreAnterior = equipo[i].getNombre();
			posiciones.remove(nombreAnterior);
		}
		
		if(posiciones.containsKey(nombreActual)) {
			int indiceAnterior = posiciones.get(nombreActual);
			equipo[indiceAnterior] = null;
			botones.get(indiceAnterior).setIcono(null);
			posiciones.remove(nombreActual);
		}
	}
	
	private void establecerPosicion(int i) {
		equipo[i] = personajeActual;
		
		Nombre nombre = equipo[i].getNombre();
		botones.get(i).setIcono(equipo[i].getRetrato());
		posiciones.put(nombre, i);
	}
	
	private void configurarBotonesSlot() {
		int x = 11*48 + 16;
		int y = 4*48;
		int ancho = 64;
		int alto = 64;
		int separacionX = 0;
		int separacionY = 0;
		int valorSeparacion = 2*48;
		int valorSaltoFila = 3*48;
		int indicePivote = 2;
		
		this.botones = new ArrayList<>();
		
		this.botones.add(new Boton(pdj));
		this.botones.add(new Boton(pdj));
		this.botones.add(new Boton(pdj));
		this.botones.add(new Boton(pdj));
		this.botones.add(new Boton(pdj));
		this.botones.add(new Boton(pdj));
		
		for(int i = 0; i < botones.size(); i++) {
			botones.get(i).inicializar(x + separacionX, y + separacionY, ancho, alto);
			separacionX += valorSeparacion;
			if(i == indicePivote) {
				separacionY = valorSaltoFila;
				separacionX = 0;
			}
		}
		
		this.selector = new SelectorBotones(pdj, botones);
	}
	
	private void cargarDatosPersonaje(Entidad entidad) {
		GestorDeRecursos gdr = pdj.gdr;
		this.personajeActual = entidad;
		this.objeto1 = gdr.getObjeto(entidad.getObjeto1());
		this.objeto2 = gdr.getObjeto(entidad.getObjeto2());
	}
	
	private void cambiarPersonaje() {
		if((pdj.teclado.D && botonOK) || btnSiguiente.isPresionado()) {
			botonOK = false;
			siguiente();
			cargarDatosPersonaje(personajes.get(indice));
		}
		if((pdj.teclado.A && botonOK) || btnAnterior.isPresionado()) {
			botonOK = false;
			anterior();
			cargarDatosPersonaje(personajes.get(indice));
		}
	}
	
	private void habilitarBotones() {
		if(!pdj.teclado.A && !pdj.teclado.D && !pdj.teclado.ENTER) {
			botonOK = true;
		}
	}
	
	private void siguiente() {
		if((indice + 1) < personajes.size()) {
			indice++;
		}
		else {
			indice = 0;
		}
	}
	
	private void anterior() {
		if((indice - 1) < 0) {
			indice = personajes.size() - 1;
		}
		else {
			indice--;
		}
	}
	
	//GETTERS & SETTERS

	public PanelDeJuego getPdj() {
		return pdj;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAguante() {
		return aguante;
	}

	public int getManos() {
		return manos;
	}

	public int getCalle() {
		return calle;
	}

	public int getLabia() {
		return labia;
	}

	public int getFacha() {
		return facha;
	}

	public int getLocura() {
		return locura;
	}

	public List<Entidad> getEquipo() {
		return personajes;
	}

	public int getIndice() {
		return indice;
	}

	public boolean isBotonOK() {
		return botonOK;
	}

	public void setPdj(PanelDeJuego pdj) {
		this.pdj = pdj;
	}

	public Entidad getPersonajeActual() {
		return personajeActual;
	}

	public void setPersonajeActual(Entidad personajeActual) {
		this.personajeActual = personajeActual;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAguante(int aguante) {
		this.aguante = aguante;
	}

	public void setManos(int manos) {
		this.manos = manos;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public void setLabia(int labia) {
		this.labia = labia;
	}

	public void setFacha(int facha) {
		this.facha = facha;
	}

	public void setLocura(int locura) {
		this.locura = locura;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public void setBotonOK(boolean botonOK) {
		this.botonOK = botonOK;
	}

	public Objeto getObjeto1() {
		return objeto1;
	}

	public Objeto getObjeto2() {
		return objeto2;
	}

	public void setObjeto1(Objeto objeto1) {
		this.objeto1 = objeto1;
	}

	public void setObjeto2(Objeto objeto2) {
		this.objeto2 = objeto2;
	}

	public Boton getBtnSiguiente() {
		return btnSiguiente;
	}

	public Boton getBtnAnterior() {
		return btnAnterior;
	}

	public void setBtnSiguiente(Boton btnSiguiente) {
		this.btnSiguiente = btnSiguiente;
	}

	public void setBtnAnterior(Boton btnAnterior) {
		this.btnAnterior = btnAnterior;
	}

	public ArrayList<Boton> getBotones() {
		return botones;
	}

	public Entidad[] getRoles() {
		return equipo;
	}

	public void setBotones(ArrayList<Boton> botones) {
		this.botones = botones;
	}

	public void setRoles(Entidad[] roles) {
		this.equipo = roles;
	}


	public Boton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(Boton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public Boton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Boton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

}

package com.santabrigida.core;

import java.util.HashMap;

import com.santabrigida.entity.Entidad;
import com.santabrigida.enums.Nombre;
import com.santabrigida.object.OBJ_RamaSauce;
import com.santabrigida.object.OBJ_Visera;
import com.santabrigida.object.Objeto;

public class GestorDeRecursos {
	
	PanelDeJuego pdj;
	private HashMap<Nombre, Entidad> personajes;
	private HashMap<String, Objeto> objetos;
	
	private int idUltimoObjeto = 0;
	
	public GestorDeRecursos(PanelDeJuego pdj) {
		this.pdj = pdj;
		this.personajes = new HashMap<>();
		this.objetos = new HashMap<>();
		cargarPersonajes();
		cargarImagenes();
		cargarObjetos();
		configurarInventarios();
	}
	
	private void cargarPersonajes() {
		Entidad mayki = new Entidad(Nombre.MAYKI, 5, 4, 3, 4, 4, 2);
		Entidad kayan = new Entidad(Nombre.KAYAN, 9, 6, 2, 1, 1, 0);
		Entidad chavo = new Entidad(Nombre.CHAVO, 4, 4, 5, 8, 2, 3);
		Entidad torni = new Entidad(Nombre.TORNI, 4, 6, 4, 3, 3, 1);
		Entidad nico = new Entidad(Nombre.NICO, 5, 5, 4, 2, 2, 1);
		Entidad ceci = new Entidad(Nombre.CECI,4, 3, 4, 6, 8, 0);
		
		agregarPersonaje(mayki);
		agregarPersonaje(kayan);
		agregarPersonaje(chavo);
		agregarPersonaje(torni);
		agregarPersonaje(nico);
		agregarPersonaje(ceci);
	}
	
	private void cargarObjetos() {
		Objeto visera = new OBJ_Visera();
		Objeto ramaSauce = new OBJ_RamaSauce();
		
		agregarObjeto(visera);
		agregarObjeto(ramaSauce);
	}
	
	private void cargarImagenes() {
		String paths[] = new String[4];
		paths[0] = "/personajes/pj_mayki1";
		paths[1] = "/personajes/pj_mayki2";
		paths[2] = "/personajes/pj_mayki3";
		paths[3] = "/personajes/pj_mayki4";
		String retratoMayki = "/retratos/pt_mayki";
		getPersonaje(Nombre.MAYKI).cargarImagenes(paths);
		getPersonaje(Nombre.MAYKI).cargarRetrato(retratoMayki);
		
		String pathsNadie[] = new String[4];
		pathsNadie[0] = "/personajes/pj_nadie1";
		pathsNadie[1] = "/personajes/pj_nadie2";
		pathsNadie[2] = "/personajes/pj_nadie3";
		pathsNadie[3] = "/personajes/pj_nadie4";
		String retratoNadie = "/retratos/pt_nadie";
		getPersonaje(Nombre.KAYAN).cargarImagenes(pathsNadie);
		getPersonaje(Nombre.CHAVO).cargarImagenes(pathsNadie);
		getPersonaje(Nombre.TORNI).cargarImagenes(pathsNadie);
		getPersonaje(Nombre.NICO).cargarImagenes(pathsNadie);
		getPersonaje(Nombre.CECI).cargarImagenes(pathsNadie);
		
		getPersonaje(Nombre.KAYAN).cargarRetrato(retratoNadie);
		getPersonaje(Nombre.CHAVO).cargarRetrato(retratoNadie);
		getPersonaje(Nombre.TORNI).cargarRetrato(retratoNadie);
		getPersonaje(Nombre.NICO).cargarRetrato(retratoNadie);
		getPersonaje(Nombre.CECI).cargarRetrato(retratoNadie);
	}
	
	private void configurarInventarios() {
		equiparObjeto(Nombre.MAYKI, "ITM-GOR-1");
		equiparObjeto(Nombre.MAYKI, "ITM-ARM-1");
	}
	
	public void equiparObjeto(Nombre nombreDePersonaje, String CUI) {
		personajes.get(nombreDePersonaje).equiparObjeto(objetos.get(CUI));
	}
	
	private void agregarPersonaje(Entidad entidad) {
		personajes.put(entidad.getNombre(), entidad);
	}
	
	private void agregarObjeto(Objeto objeto) {
		objetos.put(objeto.getCUI(), objeto);
	}
	
	public Objeto getObjeto(String CUI) {
		return objetos.get(CUI);
	}
	
	public Entidad getPersonaje(Nombre nombre) {
		return personajes.get(nombre);
	}
	
	//GETTERS & SETTERS

	public int getIdUltimoObjeto() {
		return idUltimoObjeto;
	}

	public void setIdUltimoObjeto(int idUltimoObjeto) {
		this.idUltimoObjeto = idUltimoObjeto;
	}

}

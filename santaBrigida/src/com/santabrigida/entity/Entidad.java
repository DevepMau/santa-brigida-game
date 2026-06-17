package com.santabrigida.entity;

import java.awt.image.BufferedImage;

import com.santabrigida.core.GestorDeRecursos;
import com.santabrigida.core.Herramientas;
import com.santabrigida.enums.Nombre;
import com.santabrigida.object.Objeto;

public class Entidad {
	
	public final int VIDA_MULTIPLICADOR = 10;
	public final int LIMITE_CONSUMO = 5;
	
	private Nombre nombre;
	private int vida;
	private int vidaMod;
	private int vidaObj;
	private int amistad;
	
	private int alcohol;
	private int alcoholLimiteMod;
	private int debilidadAlcohol;
	private int narcoticos;
	private int narcoticosLimiteMod;
	private int debilidadNarcoticos;
	private boolean puedeTomar;
	private boolean puedeDrogarse;
	
	private int aguante;
	private int aguanteMod;
	private int aguanteObj;
	private int manos;
	private int manosMod;
	private int manosObj;
	private int calle;
	private int calleMod;
	private int calleObj;
	private int labia;
	private int labiaMod;
	private int labiaObj;
	private int facha;
	private int fachaMod;
	private int fachaObj;
	private int locura;
	private int locuraMod;
	private int locuraObj;
	
	private int letalidad;
	private int letalidadMod;
	private int letalidadObj;
	
	private String objeto1;
	private String objeto2;
	private boolean hueco1 = true;
	private boolean hueco2 = true;
	
	private BufferedImage imagenes[];
	private BufferedImage retrato;
	
	public Entidad(Nombre nombre, int aguante, int manos, int calle, int labia, int facha, int locura) {
		this.nombre = nombre;
		this.aguante = aguante;
		this.manos = manos;
		this.calle = calle;
		this.labia = labia;
		this.facha = facha;
		this.locura = locura;
		this.letalidad = 0;
		
		this.vida = ((aguante + aguanteMod) * VIDA_MULTIPLICADOR) + vidaMod + vidaObj;
		this.alcohol = (aguante + aguanteMod) + LIMITE_CONSUMO + alcoholLimiteMod;
		this.narcoticos = (aguante + aguanteMod) + LIMITE_CONSUMO + narcoticosLimiteMod;
		
		this.puedeDrogarse = true;
		this.puedeTomar = true;
		
		this.amistad = 0;
		
		this.imagenes = new BufferedImage[4];
		this.retrato = null;
	}
	
	public void equiparObjeto(Objeto objetoNuevo) {
		if(objetoNuevo != null) {
			String CUI = objetoNuevo.getCUI();
			if(this.hueco1) {
				this.objeto1 = CUI;
				this.hueco1 = false;
				sumarBonosDeObjeto(objetoNuevo);
			}
			else if(this.hueco2) {
				this.objeto2 = CUI;
				this.hueco2 = false;
				sumarBonosDeObjeto(objetoNuevo);
			}
			else {
				System.out.println("No hay espacio en el inventario");
			}
		}
	}
	
	public void desequiparObjeto1(GestorDeRecursos gdr) {
		if (!this.hueco1) {
			restarBonosDeObjeto(gdr.getObjeto(this.objeto1));
			this.objeto1 = "";
			this.hueco1 = true;
		} else {
			System.out.println("No hay objeto equipado en el hueco 1");
		}
	}
	
	public void desequiparObjeto2(GestorDeRecursos gdr) {
		if (!this.hueco2) {
			restarBonosDeObjeto(gdr.getObjeto(this.objeto2));
			this.objeto2 = "";
			this.hueco2 = true;
		} else {
			System.out.println("No hay objeto equipado en el hueco 2");
		}
	}
	
	private void sumarBonosDeObjeto(Objeto objetoNuevo) {
		this.aguanteObj += objetoNuevo.getAguante();
		this.manosObj += objetoNuevo.getManos();
		this.calleObj += objetoNuevo.getCalle();
		this.labiaObj += objetoNuevo.getLabia();
		this.fachaObj += objetoNuevo.getFacha();
		this.locuraObj += objetoNuevo.getLocura();
	}
	
	private void restarBonosDeObjeto(Objeto objetoNuevo) {
		this.aguanteObj -= objetoNuevo.getAguante();
		this.manosObj -= objetoNuevo.getManos();
		this.calleObj -= objetoNuevo.getCalle();
		this.labiaObj -= objetoNuevo.getLabia();
		this.fachaObj -= objetoNuevo.getFacha();
		this.locuraObj -= objetoNuevo.getLocura();
	}
	
	public void cargarImagenes(String[] paths) {
    	Herramientas uTool = new Herramientas();
    	try {
			for (int i = 0; i < paths.length; i++) {
				this.imagenes[i] = uTool.configurarImagen(paths[i], 2);
    			
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
	public void cargarRetrato(String path) {
		Herramientas uTool = new Herramientas();
    	try {
    		this.retrato = uTool.configurarImagen(path, 2);	
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
	}
	
	//GETTERS & SETTERS
	public Nombre getNombre() {
		return nombre;
	}

	public int getVida() {
		return vida;
	}

	public int getVidaMod() {
		return vidaMod;
	}

	public int getVidaObj() {
		return vidaObj;
	}

	public int getAmistad() {
		return amistad;
	}

	public int getAlcohol() {
		return alcohol;
	}

	public int getAlcoholLimiteMod() {
		return alcoholLimiteMod;
	}

	public int getDebilidadAlcohol() {
		return debilidadAlcohol;
	}

	public int getNarcoticos() {
		return narcoticos;
	}

	public int getNarcoticosLimiteMod() {
		return narcoticosLimiteMod;
	}

	public int getDebilidadNarcoticos() {
		return debilidadNarcoticos;
	}

	public boolean isPuedeTomar() {
		return puedeTomar;
	}

	public boolean isPuedeDrogarse() {
		return puedeDrogarse;
	}

	public int getAguante() {
		return aguante;
	}

	public int getAguanteMod() {
		return aguanteMod;
	}

	public int getAguanteObj() {
		return aguanteObj;
	}

	public int getManos() {
		return manos;
	}

	public int getManosMod() {
		return manosMod;
	}

	public int getManosObj() {
		return manosObj;
	}

	public int getCalle() {
		return calle;
	}

	public int getCalleMod() {
		return calleMod;
	}

	public int getCalleObj() {
		return calleObj;
	}

	public int getLabia() {
		return labia;
	}

	public int getLabiaMod() {
		return labiaMod;
	}

	public int getLabiaObj() {
		return labiaObj;
	}

	public int getFacha() {
		return facha;
	}

	public int getFachaMod() {
		return fachaMod;
	}

	public int getFachaObj() {
		return fachaObj;
	}

	public int getLocura() {
		return locura;
	}

	public int getLocuraMod() {
		return locuraMod;
	}

	public int getLocuraObj() {
		return locuraObj;
	}

	public int getLetalidad() {
		return letalidad;
	}

	public int getLetalidadMod() {
		return letalidadMod;
	}

	public int getLetalidadObj() {
		return letalidadObj;
	}

	public String getObjeto1() {
		return objeto1;
	}

	public String getObjeto2() {
		return objeto2;
	}

	public boolean isHueco1() {
		return hueco1;
	}

	public boolean isHueco2() {
		return hueco2;
	}

	public BufferedImage[] getImagenes() {
		return imagenes;
	}

	public void setNombre(Nombre nombre) {
		this.nombre = nombre;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void setVidaMod(int vidaMod) {
		this.vidaMod = vidaMod;
	}

	public void setVidaObj(int vidaObj) {
		this.vidaObj = vidaObj;
	}

	public void setAmistad(int amistad) {
		this.amistad = amistad;
	}

	public void setAlcohol(int alcohol) {
		this.alcohol = alcohol;
	}

	public void setAlcoholLimiteMod(int alcoholLimiteMod) {
		this.alcoholLimiteMod = alcoholLimiteMod;
	}

	public void setDebilidadAlcohol(int debilidadAlcohol) {
		this.debilidadAlcohol = debilidadAlcohol;
	}

	public void setNarcoticos(int narcoticos) {
		this.narcoticos = narcoticos;
	}

	public void setNarcoticosLimiteMod(int narcoticosLimiteMod) {
		this.narcoticosLimiteMod = narcoticosLimiteMod;
	}

	public void setDebilidadNarcoticos(int debilidadNarcoticos) {
		this.debilidadNarcoticos = debilidadNarcoticos;
	}

	public void setPuedeTomar(boolean puedeTomar) {
		this.puedeTomar = puedeTomar;
	}

	public void setPuedeDrogarse(boolean puedeDrogarse) {
		this.puedeDrogarse = puedeDrogarse;
	}

	public void setAguante(int aguante) {
		this.aguante = aguante;
	}

	public void setAguanteMod(int aguanteMod) {
		this.aguanteMod = aguanteMod;
	}

	public void setAguanteObj(int aguanteObj) {
		this.aguanteObj = aguanteObj;
	}

	public void setManos(int manos) {
		this.manos = manos;
	}

	public void setManosMod(int manosMod) {
		this.manosMod = manosMod;
	}

	public void setManosObj(int manosObj) {
		this.manosObj = manosObj;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public void setCalleMod(int calleMod) {
		this.calleMod = calleMod;
	}

	public void setCalleObj(int calleObj) {
		this.calleObj = calleObj;
	}

	public void setLabia(int labia) {
		this.labia = labia;
	}

	public void setLabiaMod(int labiaMod) {
		this.labiaMod = labiaMod;
	}

	public void setLabiaObj(int labiaObj) {
		this.labiaObj = labiaObj;
	}

	public void setFacha(int facha) {
		this.facha = facha;
	}

	public void setFachaMod(int fachaMod) {
		this.fachaMod = fachaMod;
	}

	public void setFachaObj(int fachaObj) {
		this.fachaObj = fachaObj;
	}

	public void setLocura(int locura) {
		this.locura = locura;
	}

	public void setLocuraMod(int locuraMod) {
		this.locuraMod = locuraMod;
	}

	public void setLocuraObj(int locuraObj) {
		this.locuraObj = locuraObj;
	}

	public void setLetalidad(int letalidad) {
		this.letalidad = letalidad;
	}

	public void setLetalidadMod(int letalidadMod) {
		this.letalidadMod = letalidadMod;
	}

	public void setLetalidadObj(int letalidadObj) {
		this.letalidadObj = letalidadObj;
	}

	public void setObjeto1(String objeto1) {
		this.objeto1 = objeto1;
	}

	public void setObjeto2(String objeto2) {
		this.objeto2 = objeto2;
	}

	public void setHueco1(boolean hueco1) {
		this.hueco1 = hueco1;
	}

	public void setHueco2(boolean hueco2) {
		this.hueco2 = hueco2;
	}

	public void setImagenes(BufferedImage[] imagenes) {
		this.imagenes = imagenes;
	}

	public BufferedImage getRetrato() {
		return retrato;
	}

	public void setRetrato(BufferedImage retrato) {
		this.retrato = retrato;
	}
	
	
}

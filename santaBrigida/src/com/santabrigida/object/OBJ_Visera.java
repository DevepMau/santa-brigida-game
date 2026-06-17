package com.santabrigida.object;

public class OBJ_Visera extends Objeto {

	public OBJ_Visera() {
		super(1, "Visera", "Una visera muy fachera que te cubre los ojos del sol y las camaras de seguridad");
		configurarAtributos(0, -1, 3, 0, 2, 0, 0);
		configurarImagen("visera_blanca");
		esGorra();
		generarCUI();
	}

}

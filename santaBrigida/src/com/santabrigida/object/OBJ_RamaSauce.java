package com.santabrigida.object;

public class OBJ_RamaSauce extends Objeto {

	public OBJ_RamaSauce() {
		super(1, "Rama de sauce", "Varila fina y flexible proveniente de un sauce. Duele como la concha de la lora");
		configurarAtributos(0, 0, 2, 0, 0, 2, 5);
		configurarImagen("rama_sauce");
		esArma();
		generarCUI();
	}

}

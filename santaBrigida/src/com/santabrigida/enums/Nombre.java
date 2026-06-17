package com.santabrigida.enums;

public enum Nombre {
	
	MAYKI,
    KAYAN,
    CHAVO,
    TORNI,
    NICO,
    NANO,
    CECI,
    EMI,
    EBER,
    PANNIZA,
    SEBA;

	@Override
    public String toString() {
        String nombre = name().toLowerCase();
        return Character.toUpperCase(nombre.charAt(0))
                + nombre.substring(1);
    }
	
}

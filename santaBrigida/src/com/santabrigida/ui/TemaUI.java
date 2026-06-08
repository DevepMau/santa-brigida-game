package com.santabrigida.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

public class TemaUI {
	
	//COLORES
	public static final Color COL_BORDER = new Color(72, 82, 98);
	public static final Color COL_SHADOW = new Color(22, 32, 48);
	public static final Color COL_HOVER = new Color(122, 132, 148);
	public static final Color COL_TEXT = Color.WHITE;
	
	private static Font BASE;

    public static final float TAM_TITULO = 40f;
    public static final float TAM_DIALOGO = 34f;
    public static final float TAM_TEXTO = 24f;
    public static final float TAM_BOTON = 24f;

    static {
        try {
            InputStream is = TemaUI.class
                    .getResourceAsStream("/fuentes/MaruMonica.ttf");

            BASE = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Font getMarumonica(float tamano, float escalado) {
        return BASE.deriveFont(Font.BOLD, tamano * escalado);
    }
}

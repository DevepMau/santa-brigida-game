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
	
	public static final Color UI_PRIMARIO = new Color(181, 138, 96);
	public static final Color UI_LUZ = new Color(216, 195, 165);
	public static final Color UI_SOMBRA = new Color(92, 64, 51);
	public static final Color UI_DORADO = new Color(255, 155, 55);
	public static final Color UI_NEGRO = new Color(20, 20, 20);
	public static final Color UI_TEXTO = new Color(225, 225, 225);
	public static final Color UI_STAT = new Color(255, 255, 155);
	public static final Color UI_BUFF = new Color(155, 255, 155);
	public static final Color UI_DEBUFF = new Color(255, 155, 155);
	
	private static Font BASE;

    public static final float TAM_TITULO = 40f;
    public static final float TAM_DIALOGO = 34f;
    public static final float TAM_TEXTO = 16f;
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

    public static Font getMarumonica(float tamano, float escala) {
        return BASE.deriveFont(Font.BOLD, tamano * escala);
    }
}

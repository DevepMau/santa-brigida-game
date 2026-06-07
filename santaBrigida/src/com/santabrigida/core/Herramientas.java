package com.santabrigida.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Herramientas {

	public BufferedImage escalarImagen(BufferedImage original, int ancho, int alto) {
		BufferedImage imagenEscalada = new BufferedImage(ancho, alto, original.getType());
		Graphics2D g2 = imagenEscalada.createGraphics();
		g2.drawImage(original, 0, 0, ancho, alto, null);
		g2.dispose();

		return imagenEscalada;
	}
	
	public BufferedImage configurarImagen(String rutaImagen, int escala) throws IOException {
        //Herramientas uTool = new Herramientas();
        BufferedImage imagen = ImageIO.read(getClass().getResourceAsStream(rutaImagen + ".png"));
        //return uTool.escalarImagen(imagen, imagen.getWidth() / 2 * escala, imagen.getHeight() / 2 * escala);
        return escalarImagen(imagen, imagen.getWidth() / 2 * escala, imagen.getHeight() / 2 * escala);
    }

}
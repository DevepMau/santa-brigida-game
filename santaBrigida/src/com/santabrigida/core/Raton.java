package com.santabrigida.core;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Raton implements MouseListener, MouseMotionListener {
	
	PanelDeJuego pdj;
	public int posX;
	public int posY;
	public int offSetX;
	public int offSetY;
	public double escala;
	public boolean CLICK;
	public Point posicionRaton;
	private Cursor cursorNormal;
	private Cursor cursorClick;
	private BufferedImage manoClick;
	private BufferedImage manoIdle;

	public Raton(PanelDeJuego pdj) {
		
		this.pdj = pdj;
		this.escala = 1;
		this.offSetX = 0;
		this.offSetY = 0;
		cargarImagenes();
		Point hotspot = new Point(10, 0);
		posicionRaton = new Point(0, 0);
        cursorNormal = Toolkit.getDefaultToolkit().createCustomCursor(manoIdle, hotspot, "ManoIdle");
        cursorClick = Toolkit.getDefaultToolkit().createCustomCursor(manoClick, hotspot, "ManoClick");

	}
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //CLICK = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        CLICK = true;
        pdj.setCursor(cursorClick);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        CLICK = false;
        pdj.setCursor(cursorNormal);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        pdj.setCursor(cursorNormal);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        pdj.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    	int mouseX = (int)((e.getX() - offSetX) / escala);
        int mouseY = (int)((e.getY() - offSetY) / escala);
        posX = mouseX;
        posY = mouseY;
        posicionRaton.setLocation(posX, posY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pdj.setCursor(cursorClick);
    }
    
    public void cargarImagenes() {
    	Herramientas uTool = new Herramientas();
    	try {
    		this.manoIdle = uTool.configurarImagen("/cursores/mano_idle", 2);
    		this.manoClick = uTool.configurarImagen("/cursores/mano_click", 2);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void setEscala(double escala2) {
		this.escala = escala2;
    }
    
	public void setOffset(int offSetX, int offSetY) {
		this.offSetX = offSetX;
		this.offSetY = offSetY;
	}
}
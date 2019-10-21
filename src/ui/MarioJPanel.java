package ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.border.EmptyBorder;
import entities.Casilla;
import entities.Personaje;

public class MarioJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int cantidadCasillas;

	private Casilla[][] tablero;

	public MarioJPanel(Casilla[][] tablero, int cantidadCasillas) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		this.cantidadCasillas = cantidadCasillas;
		this.tablero = tablero;
		//llenarTableroParaProbar();
	
	}

	private void llenarTableroParaProbar() {
		tablero = new Casilla[cantidadCasillas][cantidadCasillas];
		//EJEMPLO NO DIMANICO solo funciona con cantidadCasillas >= 12
		tablero[0][0] = new Casilla(0, 0, new boolean[2]);
		tablero[0][1] = new Casilla(0, 0, new boolean[2]);
		tablero[0][2] = new Casilla(0, 0, new boolean[2]);
		tablero[0][3] = new Casilla(0, 0, new boolean[2]);
		tablero[1][3] = new Casilla(0, 0, new boolean[2]);
		tablero[1][4] = new Casilla(0, 0, new boolean[2]);
		tablero[1][5] = new Casilla(0, 0, new boolean[2]);
		tablero[2][5] = new Casilla(0, 0, new boolean[2]);
		tablero[3][5] = new Casilla(0, 0, new boolean[2]);
		tablero[4][5] = new Casilla(0, 0, new boolean[2]);
		tablero[5][5] = new Casilla(0, 0, new boolean[2]);
		tablero[6][5] = new Casilla(0, 0, new boolean[2]);
		tablero[7][5] = new Casilla(0, 0, new boolean[2]);
		tablero[7][6] = new Casilla(0, 0, new boolean[2]);
		tablero[7][7] = new Casilla(0, 0, new boolean[2]);
		tablero[7][8] = new Casilla(0, 0, new boolean[2]);
		tablero[7][9] = new Casilla(0, 0, new boolean[2]);
		tablero[8][9] = new Casilla(0, 0, new boolean[2]);
		tablero[9][9] = new Casilla(0, 0, new boolean[2]);
		tablero[9][10] = new Casilla(0, 0, new boolean[2]);
		tablero[10][10] = new Casilla(0, 0, new boolean[2]);
		tablero[11][10] = new Casilla(0, 0, new boolean[2]);
		tablero[11][11] = new Casilla(0, 0, new boolean[2]);
		
		tablero[3][5].setPersonajePosicionado(new Personaje("pepe"));
		tablero[7][7].setPersonajePosicionado(new Personaje("pepe"));
		/*
		 * for(int fil = 0; fil < cantidadCasillas; fil++) { for(int col = 0; col <
		 * cantidadCasillas; col++) { if(fil % 2 == 0) { tablero[fil][col] = new
		 * Casilla(0,0,0); }else { tablero[fil][col] = null; } } }
		 */
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D lienzo = (Graphics2D) g;
		lienzo.setStroke(new BasicStroke(5f));

		dibujarCasillas(lienzo, Color.BLACK, Color.GRAY);

	}

	private void dibujarCasillas(Graphics2D lienzo, Color colorBorde, Color colorCamino) {
		int alturaCasilla = getHeight() / cantidadCasillas;

		// Aplica margen de media casilla arriba y abajo
		alturaCasilla = (getHeight() - alturaCasilla) / cantidadCasillas;

		int posicionY = alturaCasilla / 2;
		int posicionXInicial = (getWidth() / 2) - ((cantidadCasillas / 2) * alturaCasilla);

		for (int i = 0; i < cantidadCasillas; i++) {
			int posicionX = posicionXInicial;

			for (int j = 0; j < cantidadCasillas; j++) {
				if (tablero[i][j] == null) {
					lienzo.setPaint(colorBorde);
					lienzo.drawRect(posicionX, posicionY, alturaCasilla, alturaCasilla);
				} else {
					lienzo.setPaint(colorBorde);
					lienzo.drawRect(posicionX, posicionY, alturaCasilla, alturaCasilla);
					lienzo.setPaint(colorCamino);
					lienzo.fillRect(posicionX, posicionY, alturaCasilla, alturaCasilla);
					
					if(tablero[i][j].getPersonajePosicionado() != null) {
						dibujarPersonaje(lienzo, posicionX, posicionY, alturaCasilla);
					}
				}
				
				posicionX += alturaCasilla;
			}

			posicionY += alturaCasilla;
		}
	}
	
	public void redibujar(Casilla[][] tablero){
		this.tablero = tablero;
		repaint();
	}

	private void dibujarPersonaje(Graphics2D lienzo, int posicionX, int posicionY, int alturaCasilla) {
		final int borde = alturaCasilla / 6;
		int alturaPersonaje = alturaCasilla - (2 * borde);
		
		lienzo.setPaint(Color.BLUE);
		lienzo.fillRect(posicionX + borde, posicionY + borde, alturaPersonaje, alturaPersonaje);
		lienzo.setPaint(Color.CYAN);
		lienzo.drawRect(posicionX + borde, posicionY + borde, alturaPersonaje / 2, alturaPersonaje / 2);
		
		lienzo.setPaint(Color.CYAN);
		lienzo.drawRect((posicionX + (alturaPersonaje / 2)) + borde, posicionY + borde, alturaPersonaje / 2, alturaPersonaje / 2);
	}

}

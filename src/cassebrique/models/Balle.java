package cassebrique.models;

import cassebrique.CasseBrique;

import java.awt.*;

public class Balle extends Sprite {
    private int vitesseX;
    private int vitesseY;

    public Balle(int x, int y, int vitesseX, int vitesseY) {
        super(x, y, Color.BLACK);
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
    }

    public void deplacer() {
        x += vitesseX;
        y += vitesseY;

        // Gérer les collisions avec les bords de la fenêtre
        if (x <= 0 || x >= CasseBrique.LARGEUR) {
            vitesseX = -vitesseX;
        }
        if (y <= 0 || y >= CasseBrique.HAUTEUR) {
            vitesseY = -vitesseY;
        }
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x, y, 10, 10);
    }

    public void inverserDirection() {
        vitesseY = -vitesseY;
    }

    public int getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public int getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, 30, 10, couleur);
    }
}

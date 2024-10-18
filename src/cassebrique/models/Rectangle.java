package cassebrique.models;

import java.awt.*;

public class Rectangle extends Sprite {
    protected int largeur;
    protected int hauteur;

    public Rectangle(int x, int y, int largeur, int hauteur) {
        super(x, y, null);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Rectangle(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }

    public boolean contient(Point point) {
        return point.x >= x &&
                point.x <= x + largeur &&
                point.y >= y &&
                point.y <= y + hauteur;
    }

    public boolean intersecte(Rectangle autre) {
        return !(autre.y > this.y + this.hauteur ||
                autre.y + autre.hauteur < this.y ||
                autre.x > this.x + this.largeur ||
                autre.x + autre.largeur < this.x);
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}

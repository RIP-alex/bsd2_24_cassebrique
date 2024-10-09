package cassebrique.models;

import java.awt.*;

public class Brique extends Sprite {

    protected int largeur;
    protected int hauteur;

    public Brique(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
}

package cassebrique.models;

import java.awt.*;
import java.util.Random;

public class Brique extends Rectangle {
    private int resistance;
    private boolean cassable;
    private Color couleur;

    public Brique(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, hauteur);
        this.couleur = couleur;
        this.resistance = 1;
        this.cassable = true;
    }

    public Brique(int x, int y, int largeur, int hauteur, Color couleur, int resistance, boolean cassable) {
        super(x, y, largeur, hauteur);
        this.couleur = couleur;
        this.resistance = resistance;
        this.cassable = cassable;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur); // Utiliser la couleur actuelle de la brique
        dessin.fillRect(x, y, largeur, hauteur);
        if (cassable && resistance > 1) {
            dessin.setColor(new Color(255, 255, 255, 150));
            FontMetrics fm = dessin.getFontMetrics();
            int textWidth = fm.stringWidth(String.valueOf(resistance));
            int textHeight = fm.getAscent();
            dessin.drawString(String.valueOf(resistance), x + (largeur - textWidth) / 2, y + (hauteur + textHeight) / 2);
        }
    }

    public void impact() {
        if (cassable && resistance > 0) {
            resistance--;
        }
    }

    public boolean estDetruite() {
        return cassable && resistance <= 0;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public boolean isCassable() {
        return cassable;
    }

    public void setCassable(boolean cassable) {
        this.cassable = cassable;
    }

    public void reinitialiser() {
        this.resistance = 1; // ou une autre valeur par défaut
        this.cassable = true; // ou une autre valeur par défaut
        this.couleur = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}

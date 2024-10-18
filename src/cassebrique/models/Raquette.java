package cassebrique.models;

import java.awt.*;

public class Raquette extends Rectangle {
    private static final int DEPLACEMENT = 30;

    public Raquette(int x, int y, int largeur, int hauteur) {
        super(x, y, largeur, hauteur, Color.BLUE);
    }

    public void deplacerGauche() {
        if (this.x - DEPLACEMENT >= 0) {
            this.x -= DEPLACEMENT;
        };
    }

    public void deplacerDroite(int LARGEUR) {
        if (this.x + this.largeur + DEPLACEMENT <= LARGEUR) {
            this.x += DEPLACEMENT;
        }
    }

    public boolean contient(Balle balle) {
        return this.contient(new Point(balle.getX(), balle.getY()));
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(Color.BLUE);
        dessin.fillRect(x, y, largeur, hauteur);
    }
}

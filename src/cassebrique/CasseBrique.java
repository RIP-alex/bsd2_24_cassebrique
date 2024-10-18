package cassebrique;

import cassebrique.models.Balle;
import cassebrique.models.Brique;
import cassebrique.models.Raquette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CasseBrique extends Canvas {

    public static final int LARGEUR = 1200;
    public static final int HAUTEUR = 500;
    private final JFrame fenetre = new JFrame();
    private ArrayList<Balle> listeBalle = new ArrayList<>();
    private ArrayList<Brique> listeBrique = new ArrayList<>();
    private final Raquette raquette;

    public CasseBrique() throws InterruptedException {
        this.fenetre.setSize(LARGEUR, HAUTEUR);
        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);

        this.fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
        panneau.add(this);
        this.fenetre.setContentPane(panneau);

        this.setIgnoreRepaint(true);
        this.setFocusable(true);
        this.fenetre.setResizable(false);

        raquette = new Raquette(LARGEUR / 2 - 50, HAUTEUR - 50, 100, 10);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    raquette.deplacerGauche();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    raquette.deplacerDroite(LARGEUR);
                }
            }
        });

        this.fenetre.pack();
        this.requestFocus();

        this.fenetre.setVisible(true);
        this.createBufferStrategy(2);

        initialiserBriques();

        lancerUnePartie();
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }

    private void initialiserBriques() {
        listeBrique = new ArrayList<>();

        int nombreLignes = 6;
        int nombreColonnes = 12;
        int largeurBrique = 50;
        int hauteurBrique = 20;
        int espacementX = 5;
        int espacementY = 5;
        int margeSuperieure = 50;

        int margeGauche = (LARGEUR - (nombreColonnes * (largeurBrique + espacementX) - espacementX)) / 2;

        for (int ligne = 0; ligne < nombreLignes; ligne++) {
            for (int colonne = 0; colonne < nombreColonnes; colonne++) {
                int x = margeGauche + colonne * (largeurBrique + espacementX);
                int y = margeSuperieure + ligne * (hauteurBrique + espacementY);

                Color couleur;
                switch (ligne) {
                    case 0:
                        couleur = Color.RED;
                        break;
                    case 1:
                        couleur = Color.ORANGE;
                        break;
                    case 2:
                        couleur = Color.YELLOW;
                        break;
                    case 3:
                        couleur = Color.GREEN;
                        break;
                    case 4:
                        couleur = Color.BLACK;
                    default:
                        couleur = Color.BLACK;
                }

                Brique brique = new Brique(x, y, largeurBrique, hauteurBrique, couleur);
                listeBrique.add(brique);
            }
        }
    }

    public void lancerUnePartie() throws InterruptedException {
        listeBalle = new ArrayList<>();
        listeBalle.add(new Balle(LARGEUR / 2, HAUTEUR - 100, 3, -3));

        while (true) {
            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

            for (Brique brique : listeBrique) {
                brique.dessiner(dessin);
            }

            raquette.dessiner(dessin);

            for (Balle balle : listeBalle) {
                balle.deplacer();
                balle.dessiner(dessin);
            }

            gererCollisions();

            dessin.dispose();
            this.getBufferStrategy().show();

            Thread.sleep(1000 / 120);
        }
    }

    private void gererCollisions() {
        for (int i = 0; i < listeBalle.size(); i++) {
            Balle balle = listeBalle.get(i);

            // Collision avec la raquette
            if (balle.getRectangle().intersecte(raquette)) {
                balle.setVitesseY(-balle.getVitesseY());
            }

            // Collision avec les briques
            for (int j = 0; j < listeBrique.size(); j++) {
                Brique brique = listeBrique.get(j);
                if (balle.getRectangle().intersecte(brique)) {
                    brique.impact();
                    if (brique.estDetruite()) {
                        listeBrique.remove(j);
                        j--;
                    }
                    balle.inverserDirection();
                    break;
                }
            }
        }
    }
}

package fr.epsi.smalapin.agent;

import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author antony
 */
public class Lapin extends Objet {

    protected final static double VUE = 200;
    protected final static double DISTANCEBEBE = 25;
    /**
     * Quand la variable arrive a zero le lapin veut se reproduire.
     */
    protected double reproduction = 10;

    public Lapin(double _x, double _y) {
        super(_x, _y);
        vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        Normaliser();
    }

    @Override
    public void deplacer(Graphics g, double largeur, double hauteur) {
        Environnement env = Environnement.getInstance();
        if (reproduction <= 0) {
            /*
             * Chercher un partenaire
             */
            List<Lapin> lapinsProche = env.getLapinProche(this);
            if (lapinsProche.size() > 0) {
                double distance = 9999;
                Lapin lapin = null;
                for (Lapin l : lapinsProche) {
                    double distanceTmp = this.DistanceCarre(l);
                    if (distance > distanceTmp & l.reproduction <= 0) {
                        distance = distanceTmp;
                        lapin = l;
                    }
                }
                if (distance < DISTANCEBEBE) {
                    lapin.faireBebe();
                    //Le lapin se reproduit même si l'autre ne veut pas
                    reproduction = 80;
                } else if (lapin != null) {
                    vitesseX = -lapin.vitesseX;
                    vitesseY = -lapin.vitesseY;
                } else if (env.getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
                    vitesseX = env.getGenerateur().nextDouble() - 0.5;
                    vitesseY = env.getGenerateur().nextDouble() - 0.5;
                }

            }
        } else if (env.getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = env.getGenerateur().nextDouble() - 0.5;
            vitesseY = env.getGenerateur().nextDouble() - 0.5;
            reproduction--;
        }

        EviterMurs(0, 0, largeur, largeur);
        MiseAJourPosition();
        g.setColor(Color.BLUE);
        dessiner(g);
    }

    public void faireBebe() {
        Environnement.getInstance().ajoutLapin(new Lapin(posX, posY));
        reproduction = 80;
    }

    @Override
    public double getVue() {
        return VUE;
    }

}

package fr.epsi.smalapin.agent;

import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import org.omg.CORBA.Environment;

/**
 *
 * @author antony
 */
public class Lapin extends Animal {

    /**
     * Quand la variable arrive a zero le lapin veut se reproduire.
     */
    protected double reproduction = 20;

    public Lapin(double _x, double _y) {
        super(_x, _y);
        vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        reproduction = Environnement.getInstance().getREPRODUCTION();
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
                    if (distance > distanceTmp && l.reproduction <= 0) {
                        distance = distanceTmp;
                        lapin = l;
                    }
                }
                if (distance < env.getDISTANCEBEBE()) {
                    lapin.faireBebe();
                    reproduction = Environnement.getInstance().getREPRODUCTION();
                } else if (lapin != null) {
                    vitesseX = lapin.posX - this.posX;
                    vitesseY = lapin.posY - this.posY;
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
        Normaliser();
        EviterMurs(0, 0, largeur, hauteur);
        MiseAJourPosition();
        g.setColor(Color.BLUE);
        dessiner(g);
    }

    public void faireBebe() {
        Environnement.getInstance().ajoutLapin(new Lapin(posX, posY));
        reproduction = Environnement.getInstance().getREPRODUCTION();
    }

}

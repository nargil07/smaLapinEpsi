package fr.epsi.smalapin.agent;

import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author antony
 */
public class Lapin extends Objet {

    public Lapin(double _x, double _y) {
        super(_x, _y);
        vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        Normaliser();
    }

    @Override
    public void deplacer(Graphics g, double largeur, double hauteur) {
        if (Environnement.getInstance().getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        }
        EviterMurs(0, 0, largeur, largeur);
        MiseAJourPosition();
        g.setColor(Color.BLUE);
        dessiner(g);
    }

}

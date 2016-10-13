package fr.epsi.smalapin.agent;

import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author antony
 */
public abstract class Objet {

    public double posX;
    public double posY;
    protected final static double PAS = 3;
    protected final static double PROB_CHGT_DIRECTION = 0.05;
    protected double vitesseX;
    protected double vitesseY;

    public Objet(double _x, double _y) {
        posX = _x;
        posY = _y;
    }
    
    protected void Normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }
    
    public void MiseAJourPosition() {
        posX += PAS * vitesseX;
        posY += PAS * vitesseY;
        double largeur = Environnement.getInstance().getLargeur();
        double hauteur = Environnement.getInstance().getHauteur();
        if (posX < 0) {
            posX = 0;
        }
        else if (posX > largeur) {
            posX = largeur;
        }
        if (posY < 0) {
            posY = 0;
        }
        else if (posY > hauteur) {
            posY = hauteur;
        }
    }
    public double DistanceCarre(Objet o) {
        return (o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY);
    }

    protected void dessiner(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) posX - 1, (int) posY - 1, 3, 3);
    }
    
    public abstract void deplacer(Graphics g);
}

package fr.epsi.smalapin.agent;

import java.awt.Graphics;

/**
 *
 * @author antony
 */
public abstract class Animal {

    public double posX;
    public double posY;
    protected final static double PAS = 3;
    protected final static double PROB_CHGT_DIRECTION = 0.05;
    protected double vitesseX;
    protected double vitesseY;
    protected int reproduction;

    public Animal(double _x, double _y) {
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
    }
    
    public double DistanceCarre(Animal o) {
        return (o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY);
    }

    protected void dessiner(Graphics g) {
        g.fillRect((int) posX - 1, (int) posY - 1, 3, 3);
    }
    
    protected double DistanceAuMur(double murXMin, double murYMin, double murXMax, double murYMax) {
        double min = Math.min(posX - murXMin, posY - murYMin);
        min = Math.min(min, murXMax - posX);
        min = Math.min(min, murYMax - posY);
        return min;
    }
    
    protected boolean EviterMurs(double murXMin, double murYMin, double murXMax, double murYMax) {
        // On s'arrête aux murs
        if (posX < murXMin) {
            posX = murXMin;
        }
        else if (posY < murYMin) {
            posY = murYMin;
        }
        else if (posX > murXMax) {
            posX = murXMax;
        }
        else if (posY > murYMax) {
            posY = murYMax;
        }
        
        // Changer de direction
        double distance = DistanceAuMur(murXMin, murYMin, murXMax, murYMax);
        if (distance < 5) {
            if (distance == (posX - murXMin)) {
                vitesseX += 0.1;
            }
            else if (distance == (posY - murYMin)) { 
                vitesseY += 0.1; 
            } 
            else if (distance == (murXMax - posX)) {
                vitesseX -= 0.1;
            } 
            else if (distance == (murYMax - posY)) {
                vitesseY -= 0.1;
            }   
            Normaliser();
            return true;
        }
        return false;
    }
    public abstract void deplacer(Graphics g, double largeur, double hauteur);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin.agent;

import static fr.epsi.smalapin.agent.Objet.PROB_CHGT_DIRECTION;
import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author antony
 */
public class Renard extends Objet{

    public Renard(double _x, double _y) {
        super(_x, _y);
        vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        Normaliser();
    }

    @Override
    public void deplacer(Graphics g) {
         if (Environnement.getInstance().getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        }
        MiseAJourPosition();
        g.setColor(Color.red);
        dessiner(g);
    }

    
    
    
}
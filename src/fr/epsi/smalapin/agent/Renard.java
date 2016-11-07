/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin.agent;

import static fr.epsi.smalapin.agent.Animal.PROB_CHGT_DIRECTION;
import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author antony
 */
public class Renard extends Animal {
    
    int laFaim;

    public Renard(double _x, double _y, int faim) {
        super(_x, _y);
        vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        reproduction = Environnement.getInstance().getREPRODUCTION()*10;
        laFaim = faim;
        Normaliser();
    }

    @Override
    public void deplacer(Graphics g, double largeur, double hauteur) {
        Environnement env = Environnement.getInstance();
        this.affamer();
        if (Environnement.getInstance().getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        }
        EviterMurs(0, 0, largeur, largeur);
        MiseAJourPosition();
        g.setColor(Color.red);
        dessiner(g);
    }
    
    public int affamer(){
        laFaim--;
        return laFaim;
    }
    
    public void manger() {
        Environnement env = Environnement.getInstance();
        List<Lapin> lapinsProche = env.getLapinProche(this);
        if (lapinsProche.size() > 0) {
            double distance = 9999;
            Lapin lapin = null;
            for (Lapin l : lapinsProche) {
                double distanceTmp = this.DistanceCarre(l);
                if (distance > distanceTmp & this.laFaim <= env.getFAIM()/2) {
                    distance = distanceTmp;
                    lapin = l;
                }
            }
            if (lapin != null & this.DistanceCarre(lapin)<=env.getDISTANCEMIAM()){
                env.killLapin(lapin);
                laFaim = env.getFAIM();
            };
        }
        
    }
    

}

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

    public Renard(double _x, double _y) {
        super(_x, _y);
        vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        reproduction = Environnement.getInstance().getREPRODUCTION() * 10;
        laFaim = Environnement.getInstance().getFAIM();
        Normaliser();
    }

    @Override
    public void deplacer(Graphics g, double largeur, double hauteur) {
        Environnement env = Environnement.getInstance();
        this.affamer();
        if (this.laFaim < 0) {
            env.killRenard(this);
        }
        if (Environnement.getInstance().getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        }
        EviterMurs(0, 0, largeur, hauteur);
        poursuiteLapin();
        manger();
        rechercheCompagnon();
        Normaliser();

        MiseAJourPosition();
        g.setColor(Color.red);
        dessiner(g);
    }

    public int affamer() {
        laFaim--;
        return laFaim;
    }

    public void manger() {
        Environnement env = Environnement.getInstance();
        List<Lapin> lapinsProche = env.getLapinProche(this);
        if (lapinsProche.size() > 0) {
            double distance = 9999;
            Lapin lapinToEat = null;
            for (Lapin l : lapinsProche) {
                double distanceTmp = this.DistanceCarre(l);
                if (distance > distanceTmp & this.laFaim <= env.getFAIM() / 2) {
                    distance = distanceTmp;
                    lapinToEat = l;
                }
            }
            if (lapinToEat != null && this.DistanceCarre(lapinToEat) <= env.getDISTANCEMIAM()) {
                env.killLapin(lapinToEat);
                laFaim = env.getFAIM();
            }
        }
    }

    public void faireBebe() {
        Environnement.getInstance().ajoutRenard(new Renard(posX, posY));
        reproduction = Environnement.getInstance().getREPRODUCTION()*10;
    }

    public void poursuiteLapin() {
        Environnement env = Environnement.getInstance();
        List<Lapin> lapinsProche = env.getLapinProche(this);
        if (lapinsProche.size() > 0) {
            double distance = 9999;
            Lapin proie = null;
            for (Lapin l : lapinsProche) {
                double distanceTmp = this.DistanceCarre(l);
                if (distance > distanceTmp & this.laFaim <= env.getFAIM() / 2) {
                    distance = distanceTmp;
                    proie = l;
                }
            }
            if (proie != null && this.DistanceCarre(proie) <= env.getDISTANCECHASSE()) {
                vitesseX = proie.posX - this.posX;
                vitesseY = proie.posY - this.posY;
            }
        }
    }

    private void rechercheCompagnon() {
        Environnement env = Environnement.getInstance();
        List<Renard> lesRenards = env.getRenards();
        if (lesRenards.size() > 0) {
            double distance = 9999;
            Renard compagnon = null;
            for (Renard rrr : lesRenards) {
                double distanceTmp = this.DistanceCarre(rrr);
                if (distance > distanceTmp && this.reproduction <= 0) {
                    distance = distanceTmp;
                    compagnon = rrr;
                }
            }
            if (compagnon != null && this.DistanceCarre(compagnon) <= env.getDISTANCEBEBE()) {
                compagnon.faireBebe();
                this.reproduction = env.getREPRODUCTION()*10;
            }
        }
    }
}

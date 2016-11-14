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

    public int getLaFaim() {
        return laFaim;
    }

    public Renard(double _x, double _y) {
        super(_x, _y);
        Environnement env = Environnement.getInstance();
        vitesseX = env.getGenerateur().nextDouble() - 0.5;
        vitesseY = env.getGenerateur().nextDouble() - 0.5;
        reproduction = env.getREPRODUCTION() * 10;

        laFaim = Environnement.getInstance().getFAIM();
        Normaliser();
    }
    
    public Renard(double _x, double _y, int faim) {
        super(_x, _y);
        Environnement env = Environnement.getInstance();
        vitesseX = env.getGenerateur().nextDouble() - 0.5;
        vitesseY = env.getGenerateur().nextDouble() - 0.5;
        if (env.getREPRODUCTION() * 10 < env.getFAIM()) {
            reproduction = env.getFAIM() + 1;
        } else {
            reproduction = env.getREPRODUCTION() * 10;
        }
        laFaim = Environnement.getInstance().getFAIM();
        Normaliser();
    }

    @Override
    public void deplacer(Graphics g, double largeur, double hauteur) {
        Environnement env = Environnement.getInstance();
        this.affamer();
        reproduction--;
        if (this.laFaim < 0) {
            env.killRenard(this);
        }
        if (Environnement.getInstance().getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Environnement.getInstance().getGenerateur().nextDouble() - 0.5;
        }
        EviterMurs(0, 0, largeur, hauteur);
        if (laFaim > env.getFAIM() / 4 && reproduction <= 0) {
            rechercheCompagnon();
        }
        if (laFaim < env.getFAIM() / 4 && reproduction <= 0 || laFaim <= env.getFAIM() / 2 && reproduction >= 0) {
            poursuiteLapin();
        }
        manger();
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

    public void faireBebe(int faimMoy) {
        Environnement env = Environnement.getInstance();
        env.ajoutRenard(new Renard(posX, posY, faimMoy));
        reproduction = Environnement.getInstance().getREPRODUCTION() * 10;
        if (env.getREPRODUCTION() * 10 < env.getFAIM()) {
            reproduction = env.getFAIM() + 1;
        } else {
            reproduction = env.getREPRODUCTION() * 10;
        }
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
        List<Renard> lesRenards = env.getRenardsProche(this);
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
            if (distance < env.getDISTANCEBEBE()) {
                int moyFaim;
                moyFaim = (this.getLaFaim()+compagnon.getLaFaim())/2;
                compagnon.faireBebe(moyFaim);
                reproduction = env.getREPRODUCTION() * 10;
            } else if (compagnon != null) {
                vitesseX = compagnon.posX - this.posX;
                vitesseY = compagnon.posY - this.posY;
            } else if (env.getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
                vitesseX = env.getGenerateur().nextDouble() - 0.5;
                vitesseY = env.getGenerateur().nextDouble() - 0.5;
            }
        }
    }
}

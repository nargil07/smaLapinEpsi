package fr.epsi.smalapin.environnement;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author antony
 */
public class Environnement extends Observable{
    private static Environnement instance;
    
    public static Environnement getInstance() {
        if (instance == null) {
            instance = new Environnement();
        }
        return instance;
    }
    
    protected Random generateur;
    protected double largeur;
    protected double hauteur;

    private Environnement() {
        this.generateur = new Random();
    }
    
    
}

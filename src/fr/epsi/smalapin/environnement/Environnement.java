package fr.epsi.smalapin.environnement;

import fr.epsi.smalapin.agent.Lapin;
import fr.epsi.smalapin.agent.Renard;
import java.util.ArrayList;
import java.util.List;
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
    protected List<Lapin> lapins = new ArrayList<>();
    protected List<Renard> renards = new ArrayList<>();

    private Environnement() {
        this.generateur = new Random();
        this.largeur = 600;
        this.hauteur = 400;
        
    }
    
    public void init(){
        for(int i = 0; i < 100; ++i){
            lapins.add(new Lapin(this.generateur.nextDouble() * largeur, this.generateur.nextDouble() * hauteur));
        }
        for(int i = 0; i < 50; ++i){
            renards.add(new Renard(this.generateur.nextDouble() * largeur, this.generateur.nextDouble() * hauteur));
        }
    }

    public Random getGenerateur() {
        return generateur;
    }

    public void setGenerateur(Random generateur) {
        this.generateur = generateur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }
    
    

    public List<Lapin> getLapins() {
        return lapins;
    }

    public void setLapins(List<Lapin> lapins) {
        this.lapins = lapins;
    }

    public List<Renard> getRenards() {
        return renards;
    }

    public void setRenards(List<Renard> renards) {
        this.renards = renards;
    }
    
    
    
    public void MiseAJour() {
        
        setChanged();
        notifyObservers();
    }
    
    
}

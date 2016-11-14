package fr.epsi.smalapin.environnement;

import fr.epsi.smalapin.agent.Lapin;
import fr.epsi.smalapin.agent.Animal;
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
    protected double VUE = 40000000;
    protected double DISTANCEBEBE = 40;
    protected int DISTANCEMIAM = 40;
    protected int DISTANCECHASSE = 360;
    protected int REPRODUCTION = 20;
    protected int FAIM = 200;

    public int getFAIM() {
        return FAIM;
    }

    public void setFAIM(int FAIM) {
        this.FAIM = FAIM;
    }
    
    private int nbLapin = 0;
    private int nbRenard = 0;
    
    public static Environnement getInstance() {
        if (instance == null) {
            instance = new Environnement();
        }
        return instance;
    }
    public int getDISTANCECHASSE() {
        return DISTANCECHASSE;
    }

    public void setDISTANCECHASSE(int DISTANCECHASSE) {
        this.DISTANCECHASSE = DISTANCECHASSE;
    }
    
    protected Random generateur;
    protected double largeur;
    protected double hauteur;
    protected List<Lapin> lapins = new ArrayList<>();
    protected List<Lapin> lapinToAdd = new ArrayList<>();
    protected List<Renard> renards = new ArrayList<>();
    protected List<Lapin> lapinToKill = new ArrayList<>();
    protected List<Renard> renardToKill = new ArrayList<>();
    protected List<Renard> renardToAdd = new ArrayList<>();

    private Environnement() {
        this.generateur = new Random();
    }
        
    public int getNbLapin() {
        return nbLapin;
    }

    public void setNbLapin(int nbLapin) {
        this.nbLapin = nbLapin;
    }

    public int getNbRenard() {
        return nbRenard;
    }

    public void setNbRenard(int nbRenard) {
        this.nbRenard = nbRenard;
    }

    public int getREPRODUCTION() {
        return REPRODUCTION;
    }

    public void setREPRODUCTION(int REPRODUCTION) {
        this.REPRODUCTION = REPRODUCTION;
    }
    
    public double getVUE() {
        return VUE;
    }

    public void setVUE(double VUE) {
        this.VUE = VUE;
    }

    public double getDISTANCEBEBE() {
        return DISTANCEBEBE;
    }

    public void setDISTANCEBEBE(double DISTANCEBEBE) {
        this.DISTANCEBEBE = DISTANCEBEBE;
    }
    
    public int getDISTANCEMIAM() {
        return DISTANCEMIAM;
    }

    public void setDISTANCEMIAM(int DISTANCEMIAM) {
        this.DISTANCEMIAM = DISTANCEMIAM;
    }
    
    
    
    public void init(){
        clear();
        for(int i = 0; i < this.nbLapin; ++i){
            lapins.add(new Lapin(this.generateur.nextDouble() * largeur, this.generateur.nextDouble() * hauteur));
        }
        for(int i = 0; i < this.nbRenard; ++i){
            renards.add(new Renard(this.generateur.nextDouble() * largeur, this.generateur.nextDouble() * hauteur));
        }
    }

    public void clear(){
        lapins.clear();
        renards.clear();
        this.MiseAJour();
    }

    public List<Lapin> getLapinProche(Animal obj){
        List<Lapin> results = new ArrayList<>();
        for(Lapin l : lapins){
            if(!obj.equals(l) && obj.DistanceCarre(l) < getVUE()){
                results.add(l);
            }
        }
        return results;
    }
    
    public List<Renard> getRenardsProche(Animal obj){
        List<Renard> results = new ArrayList<>();
        for(Renard rrr : renards){
            if(!obj.equals(rrr) && obj.DistanceCarre(rrr) < getVUE()){
                results.add(rrr);
            }
        }
        return results;
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
    
    public void ajoutRenard(Renard rrr){
        renardToAdd.add(rrr);
    }
    
    public void ajoutLapin(Lapin l){
        lapinToAdd.add(l);
    }
    
    public void killRenard(Renard victime){
        renardToKill.add(victime);
    }
    
    public void killLapin(Lapin victime){
        lapinToKill.add(victime);
    }
    
    /**
     * Methode qui ajoute les lapins en attente
     */
    public void flush(){
        lapins.addAll(lapinToAdd);
        lapins.removeAll(lapinToKill);
        renards.addAll(renardToAdd);
        renards.removeAll(renardToKill);
        lapinToAdd.clear();
        renardToAdd.clear();
        renardToKill.clear();
        lapinToKill.clear();
    }
    public void MiseAJour() {
        
        setChanged();
        notifyObservers();
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin;

import fr.epsi.smalapin.agent.Lapin;
import fr.epsi.smalapin.agent.Renard;
import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author antony
 */
public class EnclosPanel extends JPanel implements Observer, MouseListener{

    private Environnement env;
    Timer timer;
    boolean enCours = false;
    TimerTask tache;

    public EnclosPanel() {
        this.setBackground(Color.GREEN);
        this.addMouseListener(this);
        
    }
    
    public void Lancer() {
        env = Environnement.getInstance();
        env.setHauteur(this.getHeight()-5);
        env.setLargeur(this.getWidth()-5);
        env.init();
        env.addObserver(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (enCours) {
            // On arrête le timer
            timer.cancel();
            timer = null;
            enCours = false;
        }
        else {
            // On lance le timer
            timer = new Timer();
            tache = new TimerTask() {
                @Override
                public void run() {
                    env.MiseAJour();
                }
            };
            timer.scheduleAtFixedRate(tache, 0, 15);
            enCours = true;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0, 0, getWidth(), getHeight() );
        for(Lapin lapin : env.getLapins()) {
            lapin.deplacer(g, env.getLargeur(), env.getHauteur());
        }
        for(Renard renard : env.getRenards()) {
            renard.deplacer(g, env.getLargeur(), env.getHauteur());
        }
        
        env.flush();
    }

    @Override
    public void update(Observable o, Object arg) {
        
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

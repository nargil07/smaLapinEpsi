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
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author antony
 */
public class EnclosPanel extends JPanel implements Observer{

    private Environnement env;
    Timer timer;
    boolean enCours = false;
    TimerTask tache;
    MenuPanel menuPanel;
    public EnclosPanel(MenuPanel menuPanel) {
        this.setBackground(new Color(0, 240, 50));
        this.menuPanel = menuPanel;
    }
    
    public void Lancer() {
        env = Environnement.getInstance();
        env.setHauteur(this.getHeight()-5);
        env.setLargeur(this.getWidth()-5);
        env.init();
        env.addObserver(this);
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
        menuPanel.setNbLapinTextField(env.getLapins().size());
        menuPanel.setNbRenardTextField(env.getRenards().size());
        env.flush();
    }

    @Override
    public void update(Observable o, Object arg) {
        
        this.repaint();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin;

import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.omg.CORBA.Environment;

/**
 *
 * @author antony
 */
public class MenuPanel extends JPanel {

    private JButton butLancer;
    private JButton butPause;
    private JButton butReset;

    private TimerTask tache;
    private Timer timer;
    private boolean enCours;

    public MenuPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(200, 200, 200));
        butLancer = new JButton("Lancer la simulation");
        butPause = new JButton("Pause");
        butReset = new JButton("Reset");

        butLancer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Environnement env = Environnement.getInstance();
                if (!enCours) {
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
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        butPause.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (enCours) {
                    // On arrête le timer
                    timer.cancel();
                    timer = null;
                    enCours = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.add(butLancer);
        this.add(butPause);
        this.add(butReset);
    }

}

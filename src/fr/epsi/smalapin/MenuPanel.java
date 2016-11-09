/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin;

import fr.epsi.smalapin.environnement.Environnement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author antony
 */
public class MenuPanel extends JPanel {

    private JButton butLancer;
    private JButton butPause;
    private JButton butReset;

    private JSlider sliderReproduction;
    private JSlider sliderFaim;

    private JTextField jtfNbLapin;
    private JTextField jtfNbRenard;

    private TimerTask tache;
    private Timer timer;
    private boolean enCours;
    private boolean reset = true;

    public MenuPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(200, 200, 200));
        
        butLancer = new JButton("Lancer la simulation");
        
        butPause = new JButton("Pause");
        
        butReset = new JButton("Reset");
        
        sliderReproduction = new JSlider(0, 50);
        sliderReproduction.setMinorTickSpacing(5);
        sliderReproduction.setMajorTickSpacing(10);
        sliderReproduction.setPaintTicks(true);
        sliderReproduction.setPaintLabels(true);
        sliderReproduction.setBackground(new Color(200, 200, 200));
        
        sliderFaim = new JSlider(100, 1000);
        sliderFaim.setMajorTickSpacing(900);
        sliderFaim.setPaintTicks(true);
        sliderFaim.setPaintLabels(true);
        sliderFaim.setBackground(new Color(200, 200, 200));
        
        jtfNbLapin = new JTextField();
        jtfNbLapin.setPreferredSize(new Dimension(50, 20));
        
        jtfNbRenard = new JTextField();
        jtfNbRenard.setPreferredSize(new Dimension(50, 20));

        butLancer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Environnement env = Environnement.getInstance();
                env.setREPRODUCTION(52 - sliderReproduction.getValue());
                env.setFAIM(sliderFaim.getValue());
                if (reset) {
                    int nbLapin;
                    int nbRenard;
                    try {
                        nbLapin = Integer.valueOf(jtfNbLapin.getText());
                    } catch (NumberFormatException nfe) {
                        nbLapin = 0;
                        jtfNbLapin.setText("0");
                    }

                    try {
                        nbRenard = Integer.valueOf(jtfNbRenard.getText());
                    } catch (NumberFormatException nfe) {
                        nbRenard = 0;
                        jtfNbRenard.setText("0");
                    }
                    env.setNbLapin(nbLapin);
                    env.setNbRenard(nbRenard);
                    env.init();
                }

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
                    reset = false;
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

        butReset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (enCours) {
                    // On arrête le timer
                    timer.cancel();
                    timer = null;
                    enCours = false;
                }

                Environnement.getInstance().clear();
                reset = true;
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

        this.add(new JLabel("Nombre Lapin :"));
        this.add(jtfNbLapin);
        this.add(new JLabel("Nombre Renard :"));
        this.add(jtfNbRenard);
        this.add(new JLabel("Rapidité de reproduction"));
        this.add(sliderReproduction);
        this.add(new JLabel("Resistance à la faim"));
        this.add(sliderFaim);
    }

}

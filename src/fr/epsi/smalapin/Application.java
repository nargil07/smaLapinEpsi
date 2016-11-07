/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author antony
 */
public class Application {
    public static void main(String[] args) {
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Tri sélectif");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(600, 400));
        MenuPanel panel = new MenuPanel();
        panel.setPreferredSize(new Dimension(200, 400));
        EnclosPanel enclosPanel = new EnclosPanel();
        enclosPanel.setPreferredSize(new Dimension(400, 400));
        contentPane.add(enclosPanel, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.EAST);
        fenetre.setContentPane(contentPane);
        fenetre.setVisible(true);
        enclosPanel.Lancer();
        
    }
}

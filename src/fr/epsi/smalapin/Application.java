/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.smalapin;

import java.awt.Color;
import javax.swing.JFrame;

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
        fenetre.setBackground(new Color(150, 255, 100, 255));
        EnclosPanel enclosPanel = new EnclosPanel();
        fenetre.setContentPane(enclosPanel);
        fenetre.setVisible(true);
        enclosPanel.Lancer();
        
    }
}

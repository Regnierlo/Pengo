/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Ressources.Positions;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author loisr
 */
public class Ecran extends JFrame{
    
    private Map m;
    
    public Ecran(KeyListener[] k){
        //Dimension de la fenetre
        this.setSize(500, 500);
        //Fermeture du programme quand on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Positionne la fenetre au centre
        this.setLocationRelativeTo(null);
        //Interdire le redimensionnement de la fenetre
        this.setResizable(false);
        //Ajout du keylistener
        for(int i=0;i<k.length;i++){
            this.addKeyListener(k[i]);
        }
        //Creation du layoutManager pour differencier le HUD et la carte
        this.getContentPane().setLayout(new BorderLayout());
        //Panel du HUD
        JPanel panHud = new JPanel();
        panHud.setBackground(Color.red);
        //Panel de la carte
        JPanel panMap = new JPanel();
        panMap.setBackground(Color.black);
        //Ajout de panHud en haut de la fenetre
        this.getContentPane().add(BorderLayout.NORTH, panHud);
        //Ajout de panMap au centre de la fenetre
        this.getContentPane().add(BorderLayout.CENTER, panMap);
        //Creation de la carte
        m = new Map(this.getContentPane().get
        
        
        
        //Affiche la fenetre
        this.setVisible(true);
    }
    
    public void afficheMap(Positions p, Rectangle r){
        m.paintComponent(this.getGraphics(), p,r);
    }

    public void afficheMap(Positions p){
        m.paintComponent(this.getGraphics(), p);
    }
}

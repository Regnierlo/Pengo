/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import Ressources.Positions;
import java.awt.event.KeyEvent;

/**
 *
 * @author loisr
 */
public class P_Snobees extends Personnage
                        implements KeyListener{
    
    public P_Snobees(boolean joueur, Positions p){
        try {
            this.joueur=joueur;
            this.pos=p;
            this.vitesse=3;
            this.img = ImageIO.read(getClass().getResource("/Images//snobeesVertFace.png"));
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        //Ajout de l'IA -> doit lire un fichier externe .class
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

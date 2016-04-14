/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import GestionJeux.GameEngine;
import Ressources.Coordonnees;
import java.awt.event.KeyEvent;

/**
 *
 * @author loisr
 */
public class SnoBees extends Personnage{
    
    public SnoBees(Coordonnees c, boolean joueur, GameEngine g){
        super("",c,joueur,3,g);
    }

    @Override
    public void run() {
        while(!stop){
            ge.action(this, Directions.dirDroite, Actions.bouger);
            
            try {
                Thread.sleep(1000-(1*this.vitesse));
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

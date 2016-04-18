/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import GestionJeux.GameEngine;
import Ressources.Coordonnees;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loisr
 */
public class SnoBees extends Personnage{
    
    private boolean paralyse;
    private boolean vaMourirParBloc;
    
    public SnoBees(Coordonnees c, boolean joueur, GameEngine g){
        super("",c,joueur,3,g);
        paralyse = false;
        vaMourirParBloc = false;
    }

    @Override
    public synchronized void run() {
        
        while(!stop){
            
            if(!paralyse){
                ge.action(this, Directions.dirDroite, Actions.bouger);
                try {
                    Thread.sleep(1000-(1*this.vitesse));
                } catch (Exception e) {
                }
            }
            else{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SnoBees.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void setVaMourirParBloc(boolean b){
        vaMourirParBloc = b;
    }
    
    public boolean getVaMourirParBloc(){
        return vaMourirParBloc;
    }
    
    public void setParalyse(boolean p){
        paralyse = p;
    }
    
    public boolean getParlyse(){
        return paralyse;
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

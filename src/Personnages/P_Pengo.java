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
public class P_Pengo extends Personnage{

    private int vie;
    
    public P_Pengo(Coordonnees c, boolean joueur, GameEngine g){
        
        super("/Images/Pengo_Face_Pied_Droit.png", c, joueur, 2, g);
        
    }
    
    @Override
    public void run() {
        
    }

    /**
     * Optimiser pour les coordonnées
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Z)){
            ancienneDirection = directionActuel ;
            if(directionActuel.equals(Directions.dirHaut)&& direction==false)
                direction = true ;
            else
                direction = false ;
            directionActuel = Directions.dirHaut;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_S)){
            ancienneDirection = directionActuel ;
            if(directionActuel.equals(Directions.dirBas)&& direction==false)
                direction = true ;
            else
                direction = false ;
            directionActuel = Directions.dirBas;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_D )){
            ancienneDirection = directionActuel ;
            if(directionActuel.equals(Directions.dirDroite) && direction==false)
                direction = true ;
            else
                direction = false ;
            directionActuel = Directions.dirDroite;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Q)){
            ancienneDirection = directionActuel ;
            if(directionActuel.equals(Directions.dirGauche) && direction==false )
                direction = true ;
            else
                direction = false ;
            directionActuel = Directions.dirGauche;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_SPACE)){
            this.ge.action(this, directionActuel, Actions.pousser_detruire);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_CONTROL)){
            this.ge.afficheInfo();
        }
    }
    
    public void setPousse(Coordonnees c){
        this.getCoordonnees().setX(c.getX());
        this.getCoordonnees().setY(c.getY());
    }
    
    public int getVie(){
        return this.vie;
    }
    
    public void addVie(){
        this.vie++;
    }
    
    public void delVie(){
        this.vie--;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}

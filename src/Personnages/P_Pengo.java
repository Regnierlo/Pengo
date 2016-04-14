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
        
        String[][] t = new String[4][2];
        
        t[0][0] = "/Images/Pengo_Face_Pied_Droit.png";
        t[0][1] = "/Images/Pengo_Face_Pied_Gauche.png";
        t[1][0] = "/Images/Pengo_Derriere_Pied_Droit.png";
        t[1][1] = "/Images/Pengo_Derriere_Pied_Gauche.png";
        t[2][0] = "/Images/Pengo_Droit_Pieds_Ecartes.png";
        t[2][1] = "/Images/Pengo_Droit_Pieds_Ensembles.png";
        t[3][0] = "/Images/Pengo_Gauche_Pieds_Ecartes.png";
        t[3][1] = "/Images/Pengo_Gauche_Pieds_Ensembles.png";
        
        for(int i=0;i<4;i++){
            for(int k=0;i<2;i++){
                
                    animationMouvement[i][k] = t[i][k];
            }
        }
        //this.col = col;
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
            directionActuel = Directions.dirHaut;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_S)){
            directionActuel = Directions.dirBas;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_D )){
            directionActuel = Directions.dirDroite;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Q)){
            directionActuel = Directions.dirGauche;
            this.ge.action(this, directionActuel, Actions.bouger);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_SPACE)){
            this.ge.action(this, directionActuel, Actions.pousser_detruire);
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

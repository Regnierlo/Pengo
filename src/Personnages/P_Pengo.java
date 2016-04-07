/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Ressources.Coordonnees;
import GestionJeux.MapEngine;
import java.awt.event.KeyEvent;

/**
 *
 * @author loisr
 */
public class P_Pengo extends Personnage{

    private int vie;
    private MapEngine mapEngine;
    
    public P_Pengo(Coordonnees c, boolean joueur){
        super("/Images/Pengo_Face_D.png", c, joueur, 2);
        //this.col = col;
        
    }
    
    public void setMapTestC(MapEngine c){
        this.mapEngine = c;
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
       
        Boolean collision;
        
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Z)){
            //Verifier bord
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX(), (this.getCoordonnees().getY()+(-1*this.vitesse)));
            
            collision = mapEngine.collisionDetect(this, nc);
            this.directionActuel = Directions.dirHaut;
            if(!collision)
                this.Move(0, (-1*this.vitesse));
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_S)){
            
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX(), (this.getCoordonnees().getY()+(1*this.vitesse)));
            
            collision = mapEngine.collisionDetect(this, nc);
            this.directionActuel = Directions.dirBas;
            if(!collision)
                this.Move(0, (1*this.vitesse));
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_D)){
            
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX()+(1*this.vitesse), (this.getCoordonnees().getY()));
            
            collision = mapEngine.collisionDetect(this, nc);
            this.directionActuel = Directions.dirDroite;
            if(!collision)
                this.Move((1*this.vitesse), 0);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Q)){
            
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX()+(-1*this.vitesse), (this.getCoordonnees().getY()));
            
            collision = mapEngine.collisionDetect(this, nc);
            this.directionActuel = Directions.dirGauche;
            if(!collision)
                this.Move((-1*this.vitesse), 0);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_SPACE)){
            
            //mapEngine.detruire(this);
            mapEngine.pousser(this);
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

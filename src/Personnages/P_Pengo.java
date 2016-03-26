/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Ressources.Coordonnees;
import Ressources.MapTestC;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author loisr
 */
public class P_Pengo extends Personnage{

    private int vie;
    private MapTestC col;
    
    public P_Pengo(Coordonnees c, boolean joueur){
        super("/Images/Pengo_Face_D.png", c, joueur, 2);
        //this.col = col;
    }
    
    public void setMapTestC(MapTestC c){
        this.col = c;
    }
    
    @Override
    public void run() {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        Boolean collision;
        
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Z)){
            //Verifier bord
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX(), (this.getCoordonnees().getY()+(-1*this.vitesse)));
            
            collision = col.collisionDetect(this, nc);
            if(!collision)
                this.Move(0, (-1*this.vitesse));
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_S)){
            
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX(), (this.getCoordonnees().getY()+(1*this.vitesse)));
            
            collision = col.collisionDetect(this, nc);
            if(!collision)
                this.Move(0, (1*this.vitesse));
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_D)){
            
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX()+(1*this.vitesse), (this.getCoordonnees().getY()));
            
            collision = col.collisionDetect(this, nc);
            if(!collision)
                this.Move((1*this.vitesse), 0);
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Q)){
            
            Coordonnees nc = new Coordonnees(this.getCoordonnees().getX()+(-1*this.vitesse), (this.getCoordonnees().getY()));
            
            collision = col.collisionDetect(this, nc);
            if(!collision)
                this.Move((-1*this.vitesse), 0);
        }
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

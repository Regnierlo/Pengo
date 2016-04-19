/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

import Personnages.Personnage;

/**
 *
 * @author loisr
 */
public class Bloc {
    protected boolean enMouvement;
    protected Coordonnees coord;
    protected Personnage.Directions dir;
    protected MyImage img;
    
    public Bloc(Coordonnees c){
        coord = c;
        enMouvement = false;
        dir = Personnage.Directions.dirHaut;
    }
    
    public void setCoordonnees(Coordonnees c){
        coord = c;
    }
    
    public Coordonnees getCoordonnees(){
        return this.coord;
    }
    
    public void setEnMouvement(boolean b){
        enMouvement = b;
    }
    
    public boolean getEnMouvement(){
        return this.enMouvement;
    }
    
    public Personnage.Directions getDirection(){
        return dir;
    }
    
    public void setDirection(Personnage.Directions d){
        dir = d;
    }
    
    public MyImage getMyImage(){
        return img;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

/**
 *
 * @author loisr
 */
public class Mur {
    private boolean tremble;
    private Coordonnees coord;
    private MyImage imgStatique ;
    private MyImage imgTremble ;
    
    public Mur(Coordonnees c){
        tremble=false;
        coord = c;
        imgStatique = new MyImage("/Images/mur_de_droite.JPG", c) ;
    }
    
    public Coordonnees getCoordonnees(){
        return coord;
    }
    
    public void setTremble(boolean t){
        tremble = t;
    }
    
    public boolean getTremble(){
        return tremble;
    }
    
    public MyImage getMyImage(){
        return imgStatique ;
    }
}

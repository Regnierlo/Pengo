/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

public class Mur {
    private boolean tremble;
    private Coordonnees coord;
    
    public Mur(Coordonnees c){
        this.tremble=false;
        this.coord = c;
    }
    
    public Coordonnees getCoordonnees(){
        return coord;
    }
    
    public void setTremble(boolean t){
        tremble = t;
    }
    
    public boolean getTremble(){
        return this.tremble;
    }
    
}

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
public class Bloc {
    private boolean enMouvement;
    private Coordonnees coord;
    
    public Bloc(Coordonnees c){
        coord = c;
        enMouvement = false;
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

    public Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

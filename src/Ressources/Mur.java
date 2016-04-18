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
    boolean tremble;
    
    public Mur(){
        tremble=false;
    }
    
    public void setTremble(boolean t){
        tremble = t;
    }
    
    public boolean getTremble(){
        return tremble;
    }
}

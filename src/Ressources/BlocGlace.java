/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BlocGlace extends Bloc{
    
    private boolean contientSnobees;
    private boolean detruitParNaissance;
    private int destruction;
    private int finDestruction;
    
    public BlocGlace(Coordonnees c, boolean snobees){
        super(c);
        contientSnobees = snobees;
        detruitParNaissance = false;
    }
    
    public void incrementeDestruction(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(BlocGlace.class.getName()).log(Level.SEVERE, null, ex);
        }
        destruction++;
    }
    
    public int getDestruction(){
        return destruction;
    }
    
    public int getFinDestruction(){
        return finDestruction;
    }
    
    public boolean getContientSnobees(){
        return contientSnobees;
    }
    
    public void setContientSnoBees(boolean b){
        contientSnobees = b;
    }
    
    public void vaEtreDetruitParNaissance(boolean b){
        detruitParNaissance = b;
    }

    public boolean NaissanceSnobeeDestructionBloc() {
        return detruitParNaissance;
    }
}

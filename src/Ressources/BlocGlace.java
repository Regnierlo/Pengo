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
public class BlocGlace extends Bloc{
    
    private boolean contientSnobees;
    private boolean detruitParNaissance;
    
    public BlocGlace(Coordonnees c, boolean snobees){
        super(c);
        contientSnobees = snobees;
        detruitParNaissance = false;
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

    public boolean getUnSnoBeesVaNaitre() {
        return detruitParNaissance;
    }
}

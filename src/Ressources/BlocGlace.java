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
    
    public BlocGlace(Coordonnees c, boolean snobees){
        super(c);
        contientSnobees = snobees;
    }
    
    public boolean getContientSnobees(){
        return contientSnobees;
    }
}

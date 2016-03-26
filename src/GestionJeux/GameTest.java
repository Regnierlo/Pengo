/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.P_Pengo;
import Ressources.Coordonnees;
import Ressources.MapTestC;
import Vue.EcranTest;
import Vue.MapTest;
import java.awt.event.KeyListener;

/**
 *
 * @author loisr
 */
public class GameTest {
    
    private Boolean jeuFini = false;
    private EcranTest ec;
    private P_Pengo[] threadPengo;
    private MapTestC mtc;
    
    public GameTest(){
        newGame();
    }
    
    public void newGame(){
        
        
        threadPengo = new P_Pengo[2];
        threadPengo[0]=new P_Pengo(new Coordonnees(150, 150), true);
        threadPengo[1]=new P_Pengo(new Coordonnees(100, 100), false);
        
        mtc = new MapTestC(threadPengo);
        for(int i=0;i<threadPengo.length;i++){
            threadPengo[i].setMapTestC(mtc);
        }
        
        afficheCarte(threadPengo);
        
        do{
            update();
        }while(!jeuFini);
    }
    
    private void update(){
        for(P_Pengo t : threadPengo){
            if(t!=null)
                ec.afficheMap(t);
        }
        
        ec.repaint();
    }
    
    public void afficheCarte(KeyListener[] k){
        ec = new EcranTest(k);
    }
}

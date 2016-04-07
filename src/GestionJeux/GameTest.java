/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.P_Pengo;
import Ressources.Coordonnees;
import Vue.EcranTest;
import Vue.MapTest;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loisr
 */
public class GameTest {
    
    private Boolean jeuFini = false;
    private EcranTest ec;
    private P_Pengo[] threadPengo;
    private MapEngine mapEngine;
    
    public GameTest(){
        newGame();
    }

    public EcranTest getEc() {
        return ec;
    }
    
    
    
    public void newGame(){
        
        
        threadPengo = new P_Pengo[4];
        threadPengo[0]=new P_Pengo(new Coordonnees(100, 100+16), true);
        threadPengo[1]=new P_Pengo(new Coordonnees(100, 100), false);
        threadPengo[2]=new P_Pengo(new Coordonnees(100, 100-17), false);
        threadPengo[3]=new P_Pengo(new Coordonnees(100-17, 100), false);
        
        mapEngine = new MapEngine(threadPengo);
        
        for(int i=0;i<threadPengo.length;i++){
            if(this.threadPengo[i] != null)
                
                threadPengo[i].setGametest(this);
                threadPengo[i].setMapTestC(mapEngine);
        }
        
        
        

        ec = new EcranTest(threadPengo);
        for (int i = 0; i < 4; i++) {
          ec.getM().personages.add(threadPengo[i]);
        }
        
        update();
    }
    
    public void update(){

        ec.getM().paintComponent(this.getEc().getM().getGraphics());

        if(mapEngine.getNouvellesInformations()){
            this.threadPengo = this.mapEngine.getTabP_Pengo();
            this.mapEngine.informationsRecupere();
        }
        
        
        ec.repaint();
    }
    

    public void clearBlock(Coordonnees coord) {
        ec.clearBlock(coord);
    }
   
    
}

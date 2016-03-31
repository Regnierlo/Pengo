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
    
    public void newGame(){
        
        
        threadPengo = new P_Pengo[4];
        threadPengo[0]=new P_Pengo(new Coordonnees(100, 100+16), true);
        threadPengo[1]=new P_Pengo(new Coordonnees(100, 100), false);
        threadPengo[2]=new P_Pengo(new Coordonnees(100, 100-17), false);
        threadPengo[3]=new P_Pengo(new Coordonnees(100-17, 100), false);
        
        mapEngine = new MapEngine(threadPengo);
        
        for(int i=0;i<threadPengo.length;i++){
            if(this.threadPengo[i] != null)
                threadPengo[i].setMapTestC(mapEngine);
        }
        
        afficheCarte(threadPengo);
        repaintGT();
        do{
            update();
        }while(!jeuFini);
    }
    
    private void update(){
        
        if(mapEngine.getNouvellesInformations()){
            this.threadPengo = this.mapEngine.getTabP_Pengo();
            this.mapEngine.informationsRecupere();
        }
        
        for(P_Pengo t : threadPengo){
            if(t!=null)
                ec.afficheMap(t);
        }
        
        //ec.repaint();
    }
    
    /**
     * Appelle la fonction repaint toute les 25millisecondes (évite au maximum le scintillement)
     */
    public void repaintGT(){
        Thread t = new Thread(){
            public void run(){
                while(!jeuFini){
                    try {
                        Thread.sleep(25);
                        ec.repaint();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
    }
    
    public void afficheCarte(KeyListener[] k){
        ec = new EcranTest(k);
    }
}
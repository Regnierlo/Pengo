/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;
//import java.awt.Color ;
//import java.awt.Graphics ;
import GestionJeux.GameEngine;
import java.awt.Color ;
import java.awt.event.KeyListener;
import javax.swing.JFrame ;
import javax.swing.JPanel ;


/**
 *
 * @author Marie
 */
public class Fenetre extends JFrame{
    
    private Carte map  ;
    private Calcul_Vue cv ;
    
    public Fenetre(GameEngine gameEngine, KeyListener[] keyListener){
        super("Pengo") ;
        build() ;
        
        
        for(int i = 0 ; i < keyListener.length ; i++){
            this.addKeyListener(keyListener[i]);
        }
        /*
        PressEntry pe = new PressEntry();
        pe.setSize(400,750);
        this.getContentPane().add(pe);*/
        
        lancerJeu(gameEngine);
       // map.setBackground(Color.black);
        
        
    }
    public void setCarte(String[][] carteString){
       map.setCalcul((cv.setCoordonnees(carteString))) ;
    }
    
    private void build(){
        this.setSize(400,750) ;
        this.setLocationRelativeTo(null) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setResizable(false) ;
        this.setAlwaysOnTop(true) ;
       // this.setBackground(Color.BLACK);
    }
    
    private void lancerJeu(GameEngine gameEngine){
        map = new Carte() ;
        cv = new Calcul_Vue(gameEngine) ;
        this.getContentPane().add(map);
        this.setVisible(true) ;
    }
}

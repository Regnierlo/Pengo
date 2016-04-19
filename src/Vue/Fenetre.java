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
import javax.swing.JFrame ;
import javax.swing.JPanel ;


/**
 *
 * @author Marie
 */
public class Fenetre extends JFrame{
    
    private Carte map  ;
    
    public Fenetre(GameEngine gameEngine){
        super("Pengo") ;
        build() ;
        
        map = new Carte(gameEngine) ;
        map.setSize(320,512);
        map.setBackground(Color.black);
        this.getContentPane().add(map);
        this.setVisible(true) ;
        
        
    }
    
    
    public void setCarte(String[][] carteString){
        map.setImages(carteString) ;
    }
    
    private void build(){
        this.setSize(400,700) ;
        this.setLocationRelativeTo(null) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setResizable(false) ;
        this.setAlwaysOnTop(true) ;
        this.setBackground(Color.BLACK);
    }
}

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
    
    public Fenetre(){
        
        this.setTitle("Pengo") ;
        this.setSize(400,700) ;
        this.setLocationRelativeTo(null) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        this.setResizable(false) ;
        this.setAlwaysOnTop(true) ;
        this.setBackground(Color.BLACK);
        //this.setUndecorated(true) ;
        
        /*Hud hud = new Hud() ;
        hud.setSize(400,100);
        hud.setBackground(Color.BLACK);
        
        this.setContentPane(hud) ;
        */
        //GameEngine ge = new GameEngine();
        //Carte carte = new Carte(ge) ;
        //carte.setSize(320,512);
        
        
        this.setVisible(true) ;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;
//import java.awt.Color ;
//import java.awt.Graphics ;
import GestionJeux.GameEngine;
import java.awt.BorderLayout;
import java.awt.Color ;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame ;
import javax.swing.JLabel;
import javax.swing.JPanel ;
import java.awt.GridLayout;

public class Fenetre extends JFrame{
    
    private Carte map  ;
    private Calcul_Vue cv ;
    private Hud hud ;
    private JLabel border ;
    private JPanel borderWest;
    
   
   public Fenetre(GameEngine gameEngine, KeyListener[] keyListener){
        build() ;
        
        for (KeyListener keyListener1 : keyListener) {
            this.addKeyListener(keyListener1);
        }
        
        lancerJeu(gameEngine);
      
    }
   
    public void setCarte(String[][] carteString){
       map.setCalcul((cv.setCoordonnees(carteString))) ;
    }
    
    public void setScore(String score){
        hud.setScore(score);
    }
    
    public void setHighScore(String score){
        hud.setHighScore(score);
    }
    
    public void setNiveau(int n){
        hud.setNiveau(n);
    }
    
    public void setNbVie(int c){
        hud.setNbVie(c);
    }
    
    private void build(){
        this.setSize(400,750) ;
        this.setLocationRelativeTo(null) ;
        this.setLayout(new BorderLayout());
        
        this.setResizable(false) ;
        this.setAlwaysOnTop(true) ;
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        
        
        borderWest = new JPanel();
        borderWest.setLayout(new GridLayout(1,1));
        border = new JLabel();
        hud = new Hud() ;
        map = new Carte() ;
        map.setSize(512,320);
        map.setBackground(Color.BLACK);
        
        try{
           border.setIcon(new ImageIcon(Picture.chemin+"src/Images/border.png"));
        }catch(NullPointerException e){
        }
        borderWest.add(border);
        borderWest.setBackground(Color.BLACK);
        
        this.getContentPane().add(borderWest,BorderLayout.WEST);
        
        this.getContentPane().add(hud,BorderLayout.NORTH) ;
        this.getContentPane().add(map,BorderLayout.CENTER);
       
    }
    
    
    private void lancerJeu(GameEngine gameEngine){
        cv = new Calcul_Vue(gameEngine) ;
        this.setVisible(true) ;
    }
}

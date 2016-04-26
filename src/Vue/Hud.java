/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color ;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import javax.swing.JPanel;


public class Hud extends JPanel{
    
    private JPanel joueur ;
    private JPanel highScore ;
    private JPanel nbVie ;
    private String highscore="5000";
    private int niveau = 1 ;
    private int coeur = 1 ;
    
    private LabelHud labelScore ;
    
    public Hud(){
        
        joueur = new JPanel() ;
        joueur.setLayout(new BoxLayout(joueur, BoxLayout.LINE_AXIS));
       
        highScore = new JPanel() ;
        highScore.setLayout(new BoxLayout(highScore, BoxLayout.LINE_AXIS));
        
        nbVie = new JPanel() ;
        nbVie.setLayout(new BoxLayout(nbVie, BoxLayout.LINE_AXIS));
        
        
        this.setLayout(new GridLayout(4,2));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 30));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        
        joueur.setBackground(Color.BLACK);
        joueur.add(new LabelHud(1,"1P  "));
        
        labelScore = new LabelHud(2, "0") ;
        joueur.add(labelScore);
        this.add(joueur);
        
        highScore.setBackground(Color.BLACK);
        highScore.add(new LabelHud(3,"HI  "));
        highScore.add(new LabelHud(2, highscore));
        this.add(highScore);
        
        nbVie.setBackground(Color.BLACK);
        nbVie.add(new LabelHud(1, "VIE   "));
        
        
        this.add(nbVie);
        this.add(new JLabel("NIVEAUX"));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
    
        
    }
    
    public void setHighScore(String txt){
        highscore = txt ;
    }
    
    public void setScore(String txt){
        labelScore.setScore(txt);
    }
    
    public void setNiveau(int n){
        niveau = n ;
    }
    
    public void setNbVie(int c){
        coeur = c ;
    }
    
}

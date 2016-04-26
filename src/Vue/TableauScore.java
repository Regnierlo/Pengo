/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import GestionJeux.Score;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableauScore extends JFrame {
    
    String[][] tableau = new String[5][2] ;
    String chemin="";
    
    JPanel tab = new JPanel(new GridLayout(6,3));
    
    public TableauScore(){
        tableau = Score.getTopScore() ;
        constructionFenetre();
       
        
    }
    
    private void constructionFenetre(){
        
        this.setSize(400,700);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(new JLabel(new ImageIcon(chemin+"src/Images/etoile.png")),BorderLayout.NORTH);
        
        this.setBackground(Color.BLACK);
        
        tab.setBorder(BorderFactory.createLineBorder(Color.black, 30));
        tab.setBackground(Color.BLACK);
        
        tab.add(new JLabel(""));
        tab.add(new JLabel(new ImageIcon(chemin+"src/Images/name_label.png")));
        tab.add(new JLabel(new ImageIcon(chemin+"src/Images/score_label.png")));
        
        tab.add(new LabelScore("",2)) ;
        tab.add(new LabelScore(tableau[0][0],0));
        tab.add(new LabelScore(tableau[0][1],1));
        
        tab.add(new LabelScore("",3)) ;
        tab.add(new LabelScore(tableau[1][0],0));
        tab.add(new LabelScore(tableau[1][1],1));
        
        tab.add(new LabelScore("",4)) ;
        tab.add(new LabelScore(tableau[2][0],0));
        tab.add(new LabelScore(tableau[2][1],1));
        
        tab.add(new LabelScore("",5)) ;
        tab.add(new LabelScore(tableau[3][0],0));
        tab.add(new LabelScore(tableau[3][1],1));
        
        
        tab.add(new LabelScore("",6)) ;
        tab.add(new LabelScore(tableau[4][0],0));
        tab.add(new LabelScore(tableau[4][1],1));
        
        
        
        this.add(tab,BorderLayout.CENTER);
        this.setVisible(true);
        
    }
   
}

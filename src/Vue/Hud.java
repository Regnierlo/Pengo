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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;


public class Hud extends JPanel{
    
    private JPanel joueur ;
    private JPanel highScore ;
    private JPanel nbVie ;
    private JPanel nbNiveau ;
   
    
    
    private LabelHud labelScore ;
    private LabelHud labelHighScore ;
    
    private JLabel vieUne ;
    private JLabel vieDeux ;
    private JLabel vieTrois ;
    
    private JLabel niveau ;
    
    public Hud(){
        
        this.setLayout(new GridLayout(4,2));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 30));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        
        
        joueur = new JPanel() ;
        joueur.setLayout(new BoxLayout(joueur, BoxLayout.LINE_AXIS));
        joueur.setBackground(Color.BLACK);
        joueur.add(new LabelHud(1,"     1P  "));
        labelScore = new LabelHud(2, "0") ;
        joueur.add(labelScore);
        
        this.add(joueur);
        
        ///////
        
        
        highScore = new JPanel() ;
        highScore.setLayout(new BoxLayout(highScore, BoxLayout.LINE_AXIS));
        
        highScore.setBackground(Color.BLACK);
        highScore.add(new LabelHud(3,"  HI  "));
        labelHighScore=new LabelHud(2,"");
        highScore.add(labelHighScore);
        
        this.add(highScore);
        
        //////
        
        nbVie = new JPanel() ;
        nbVie.setLayout(new BoxLayout(nbVie, BoxLayout.LINE_AXIS));
        
        nbVie.setBackground(Color.BLACK);
        nbVie.add(new LabelHud(1, "     VIE   "));
        try{
            vieUne = new JLabel(new ImageIcon(Picture.chemin+"src/Images/coeur.png"));
            vieDeux = new JLabel(new ImageIcon(Picture.chemin+"src/Images/coeur.png"));
            vieTrois = new JLabel(new ImageIcon(Picture.chemin+"src/Images/coeur.png"));
        } catch(NullPointerException e){}
        nbVie.add(vieUne);
        nbVie.add(vieDeux);
        nbVie.add(vieTrois);
        
        this.add(nbVie);
        
        ///////////////////
        
        
        nbNiveau = new JPanel();
        nbNiveau.setLayout(new BoxLayout(nbNiveau,BoxLayout.LINE_AXIS));
        nbNiveau.setBackground(Color.BLACK);
        nbNiveau.add(new LabelHud(1,"  NIVEAUX  "));
        
        try{
            niveau = new JLabel(new ImageIcon(Picture.chemin+"src/Images/niveau1.png"));
        }catch(NullPointerException e){}
        nbNiveau.add(niveau);
        
        this.add(nbNiveau);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        
    }
    
    public void setHighScore(String txt){
        labelHighScore.setScore(txt);
    }
    
    public void setScore(String txt){
        labelScore.setScore(txt);
    }
    
    public void setNiveau(int n){
        if(n==(-2))
            try{
                niveau.setIcon(new ImageIcon(Picture.chemin+"src/Images/niveau2.png"));
            }catch(NullPointerException e){}
        else
            if(n==(-3))
                try{
                niveau.setIcon(new ImageIcon(Picture.chemin+"src/Images/niveau3.png"));
            }catch(NullPointerException e){}
    }
    
    public void setNbVie(int c){
        System.out.println("nombre vie "+c);
        if(c==2){
            vieTrois.setVisible(false);
        }
        if(c==1)
            vieDeux.setVisible(false);
        if(c==0)
            vieUne.setVisible(false);
    }
    
}

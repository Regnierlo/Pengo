/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author loisr
 */
public class LabelMenu extends JLabel implements MouseListener{
    
    private ImageIcon img1 ;
    private ImageIcon img2;
    String chemin ="";
    
    public LabelMenu(int type){
        this.addMouseListener(this);
        switch (type) {
            case 2:
                constructionLabelScore();
                break;
            case 1:
                constructionLabelJouer();
                break;
            case 3:
                constructionLabelExit();
                break;
            default:
                break;
        }
    }
    
    
    private void constructionLabelJouer(){
        try{
            img1 = new ImageIcon(chemin+"src/Images/jouer1.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        try{
            img2 = new ImageIcon(chemin+"src/Images/jouer2.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        
        this.setIcon(img1);
    }
    private void constructionLabelScore(){
        try{
            img1 = new ImageIcon(chemin+"src/Images/score1.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        try{
            img2 = new ImageIcon(chemin+"src/Images/score2.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        
        this.setIcon(img1);
    }
    
    private void constructionLabelExit(){
        try{
            img1 = new ImageIcon(chemin+"src/Images/exit1.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        try{
            img2 = new ImageIcon(chemin+"src/Images/exit2.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        
        this.setIcon(img1);
    }
    @Override
    public void mouseClicked(MouseEvent event){
        
    }
    
    @Override
    public void mouseExited(MouseEvent event){
        this.setIcon(img1);
    }
    
    @Override
    public void mouseEntered(MouseEvent event){
       this.setIcon(img2);
    }
    
    @Override
    public void mouseReleased(MouseEvent event){
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    
    
}

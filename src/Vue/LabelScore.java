/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelScore extends JLabel{
    
    private ImageIcon img ;
    
    public LabelScore(String txt, int type){
        super(txt);
        switch(type){
            case 0 : constructionName();
                break ;
            case 1 :  constructionScore(); 
                break ;
            case 2 : constructionIcon1ST();
                break ;
            case 3 : constructionIcon2ND();
                break ;
            case 4 : constructionIcon3RD();
                break ;
            case 5 : constructionIcon4TH();
                break ;
            case 6 : constructionIcon5TH();
                break ;
        } 
    }
    
    private void constructionName(){
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalAlignment(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
    }
    
    private void constructionScore(){
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalAlignment(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.YELLOW);
        this.setOpaque(true);
    }
    
    private void constructionIcon1ST(){
        try{
            img = new ImageIcon(Picture.chemin+"src/Images/premier.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        this.setIcon(img);
    }
    
    private void constructionIcon2ND(){
        try{
            img = new ImageIcon(Picture.chemin+"src/Images/deuxieme.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        this.setIcon(img);
    }
    
     private void constructionIcon3RD(){
        try{
            img = new ImageIcon(Picture.chemin+"src/Images/troisieme.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        this.setIcon(img);
    }
     
      private void constructionIcon4TH(){
        try{
            img = new ImageIcon(Picture.chemin+"src/Images/quatrieme.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        this.setIcon(img);
    }
      
       private void constructionIcon5TH(){
        try{
            img = new ImageIcon(Picture.chemin+"src/Images/cinquieme.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        this.setIcon(img);
    }
    
}

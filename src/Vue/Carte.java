package Vue;

import GestionJeux.GameEngine;
import GestionJeux.Map;
import Personnages.*;
import Ressources.*;
import java.awt.Graphics ;
import java.awt.Graphics2D ;

import java.awt.Image;
import java.io.File;

import java.io.IOException;
import javax.imageio.ImageIO;


import javax.swing.JPanel;

public class Carte extends JPanel{
    private Image[][] tabImage ;
    private Image image ;
    private int[][] tabCoordonnees ;
    private int nbCoordonnees = 0 ;
    private Picture tabPictures ;
    private int x ;
    private int abscisse = 40 ;
    private int ordonnee = 40 ;
    private int l = 0 ;
    
    public Carte(){
        tabCoordonnees = new int[2][160] ;
        reinitTabCoordonnees();
        tabPictures = new Picture();

    }
    
    private void reinitTabCoordonnees(){
        
        for(int i = 0 ; i < 160 ; i++){
            tabCoordonnees[0][i]= 1 ;
            tabCoordonnees[1][i]= 9 ;
        }
    }
    
    void setCalcul(int[][] nouvellesCoordonnees){
        tabCoordonnees = nouvellesCoordonnees ;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        l = 0 ;
        
       /* g2.drawImage(tabPictures.getPicture(0,0),32, 32,this);
        g2.drawImage(tabPictures.getPicture(1,0),32*2, 32,this);
        g2.drawImage(tabPictures.getPicture(0,1),32*3, 32,this);
        g2.drawImage(tabPictures.getPicture(1,1),32*4, 32,this);
        g2.drawImage(tabPictures.getPicture(0,2),32*5, 32,this);
        g2.drawImage(tabPictures.getPicture(1,2),32*6, 32,this);
        g2.drawImage(tabPictures.getPicture(0,3),32*7, 32,this);
        g2.drawImage(tabPictures.getPicture(1,3),32*8, 32,this);
        
        g2.drawImage(tabPictures.getPicture(0,4),32, 32*2,this);
        g2.drawImage(tabPictures.getPicture(1,4),32*2, 32*2,this);
        g2.drawImage(tabPictures.getPicture(0,5),32*3, 32*2,this);
        g2.drawImage(tabPictures.getPicture(1,5),32*4, 32*2,this);
        g2.drawImage(tabPictures.getPicture(0,6),32*5, 32*2,this);
        g2.drawImage(tabPictures.getPicture(1,6),32*6, 32*2,this);
        g2.drawImage(tabPictures.getPicture(0,7),32*7, 32*2,this);
        g2.drawImage(tabPictures.getPicture(1,7),32*8, 32*2,this);
        
        g2.drawImage(tabPictures.getPicture(0,8),32, 32*3,this);
        g2.drawImage(tabPictures.getPicture(1,8),32*2, 32*3,this);
        g2.drawImage(tabPictures.getPicture(0,9),32*3, 32*3,this);
        g2.drawImage(tabPictures.getPicture(1,9),32*4, 32*3,this);
        g2.drawImage(tabPictures.getPicture(0,10),32*5, 32*3,this);
       // g2.drawImage(tabPictures.getPicture(1,10),32*6, 32*3,this);
        g2.drawImage(tabPictures.getPicture(0,11),32*7, 32*3,this);
        g2.drawImage(tabPictures.getPicture(1,11),32*8, 32*3,this);
        g2.drawImage(tabPictures.getPicture(0,12),32*9, 32*3,this);
        g2.drawImage(tabPictures.getPicture(1,12),32*10, 32*3,this);
        
       // g2.drawImage(tabPictures.getPicture(0,13),32, 32*4,this);
       // g2.drawImage(tabPictures.getPicture(1,13),32*2, 32*4,this);
        g2.drawImage(tabPictures.getPicture(0,14),32*3, 32*4,this);
        g2.drawImage(tabPictures.getPicture(1,14),32*4, 32*4,this);
        g2.drawImage(tabPictures.getPicture(0,15),32*5, 32*4,this);
        g2.drawImage(tabPictures.getPicture(1,15),32*6, 32*4,this);
        
        */
        
        
       for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                    g2.drawImage(tabPictures.getPicture(tabCoordonnees[0][l],tabCoordonnees[1][l]),j*32, i*32,this);
                    //System.out.println("("+tabCoordonnees[0][l]+" "+tabCoordonnees[1][l]+")");
                    //System.out.println("l = " + l );
                    l++;
                
            }
        }
       // reinitTabCoordonnees();
    }
    
    
}

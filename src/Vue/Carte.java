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
        tabCoordonnees = new int[2][300] ;
        reinitTabCoordonnees();
        tabPictures = new Picture();

    }
    
    private void reinitTabCoordonnees(){
        
        for(int i = 0 ; i < 300 ; i++){
            tabCoordonnees[0][i]= 1 ;
            tabCoordonnees[1][i]= 32 ;
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
        
       
        
       /// g2.drawImage(tabPictures.getPicture(0,13),32, 32*4,this);
       ///g2.drawImage(tabPictures.getPicture(1,13),32*2, 32*4,this);
      
       for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                    g2.drawImage(tabPictures.getPicture(tabCoordonnees[0][l],tabCoordonnees[1][l]),j*32, i*32,this);
                    l++;    
            }
        }
       // reinitTabCoordonnees(); 
       //g2.drawImage(tabPictures.getPicture(1,11),32*8, 32*3,this);
    }
    
    
}

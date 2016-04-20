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
    private GameEngine jesus ;
    private Image[][] tabImage ;
    private Image image ;
    
    public Carte(GameEngine gameEngine){
        jesus = gameEngine ;
        tabImage = new Image[16][10] ;

    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
       for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                if(tabImage[i][j]!=null){
                    System.out.println("qfkjlm");
                    g2.drawImage(tabImage[i][j],i*32, j*32, this);
                }
            }
        }
    }
    
    
    public void setImages(String[][] s){
        for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                switch (s[i][j]){
                    case "P" : {
                        if(jesus.getPengo() instanceof P_Pengo){
                            try{
                                image= ImageIO.read(new File("src/Images/Pengo_Derriere_Pied_Droit.png")) ;
                                tabImage[i][j] = image ;
                            }catch(IOException e){
                                e.printStackTrace() ;
                            }
                        }
                    } break ;
                    case "E" : {
                        if(jesus.getSnoBees(new Coordonnees(i,j)) instanceof SnoBees){
                           try{
                                image= ImageIO.read(new File("src/Images/SnoBees_Droit.png")) ;
                                tabImage[i][j] = image ;
                            }catch(IOException e){
                                e.printStackTrace() ;
                            }
                        }
                    } break ;
                    case "M" : {
                        if(jesus.getMur(new Coordonnees(i,j)) instanceof Mur){
                           try{
                                image= ImageIO.read(new File("src/Images/mur_de_gauche.jpg")) ;
                                tabImage[i][j] = image ;
                            }catch(IOException e){
                                e.printStackTrace() ;
                            }
                        }
                        
                    } break ;
                    case "G" : {
                        if(jesus.getBloc(new Coordonnees(i,j)) instanceof BlocGlace){
                            try{
                                image= ImageIO.read(new File("src/Images/bloc_normal.png")) ;
                                tabImage[i][j] = image ;
                            }catch(IOException e){
                                e.printStackTrace() ;
                            }
                        }
                    } break ;
                    case "S":
                        if(jesus.getBloc(new Coordonnees(i, j)) instanceof BlocSpecial){
                            try{
                                image= ImageIO.read(new File("src/Images/companion_cube.png")) ;
                                tabImage[i][j] = image ;
                            }catch(IOException e){
                                e.printStackTrace() ;
                            }
                        }
                        break;
                }
            }
        }
    repaint();   
    }
}

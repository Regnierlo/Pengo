package Vue;

import GestionJeux.GameEngine;
import GestionJeux.Map;
import Personnages.*;
import Ressources.*;
import java.awt.Graphics ;
import java.awt.Graphics2D ;

import java.awt.Font ;
import java.awt.Color ;


import javax.swing.JPanel;

public class Carte extends JPanel{
    private GameEngine jesus ;
    private MyImage[][] tabImage ;
    
    public Carte(GameEngine gameEngine){
        jesus = gameEngine ;
        tabImage = new MyImage[16][10] ;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        g2.drawImage(tabImage[0][0].getImg(),0, 0, tabImage[0][0].getObserver());
        for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                if(tabImage[i][j]!=null){
                    g2.drawImage(tabImage[i][j].getImg(),i*32, j*32, tabImage[i][j].getObserver());
                }
            }
        }
    }
    
    public void setImages(String[][] s){
        for(int i = 0 ; i < s.length ; i++){
            for(int j = 0 ; j < s[0].length ; j++){
                switch (s[i][j]){
                    case "P" : {
                        if(jesus.getPengo() instanceof P_Pengo){
                            
                            tabImage[i][j] = jesus.getPengo().getMyImage() ;
                        }
                    } break ;
                    case "E" : {
                        if(jesus.getSnoBees(new Coordonnees(i,j)) instanceof SnoBees){
                            tabImage[i][j] = jesus.getSnoBees(new Coordonnees(i,j)).getMyImage() ;
                        }
                    } break ;
                    case "M" : {
                        if(jesus.getMur(new Coordonnees(i,j)) instanceof Mur){
                            tabImage[i][j] = jesus.getMur(new Coordonnees(i,j)).getMyImage() ;
                        }
                        
                    } break ;
                    case "G" : {
                        if(jesus.getBloc(new Coordonnees(i,j)) instanceof BlocGlace){
                            tabImage[i][j] = jesus.getBloc(new Coordonnees(i,j)).getMyImage();
                        }
                    } break ;
                    case "S":
                        if(jesus.getBloc(new Coordonnees(i, j)) instanceof BlocSpecial){
                            tabImage[i][j] = jesus.getBloc(new Coordonnees(i, j)).getMyImage();
                        }
                        break;
                }
            }
        }
       
    }
}

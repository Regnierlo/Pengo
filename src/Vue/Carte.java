package Vue;

import java.awt.FlowLayout;
import java.awt.Graphics ;
import java.awt.Graphics2D ;


import javax.swing.JPanel;

public class Carte extends JPanel{
    private int[][] tabCoordonnees ;
    private final Picture tabPictures ;
    private int l = 0 ;
    
    public Carte(){
        this.setLayout(new FlowLayout());
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
    
    /// Nouvelles coordonnées données via le tableau de String de GameEngine et calculées par Calcul_Vue
    void setCalcul(int[][] nouvellesCoordonnees){
        tabCoordonnees = nouvellesCoordonnees ;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        l = 0 ;
        
      //// affichage des images
       for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                    g2.drawImage(tabPictures.getPicture(tabCoordonnees[0][l],tabCoordonnees[1][l]),j*32, i*32,this);
                    l++;    
            }
        }
    }
}

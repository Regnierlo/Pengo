/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Entry extends JPanel{
    
    private final String chemin = "";
    Image img;
    
    public Entry(String txt){
        
        try{
            img = ImageIO.read(new File(chemin+txt));
          
        }catch (IOException e){
                System.out.println("Pas de première fenêtre");
        } 

    }
    
    
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(img, 0, 0, this) ;
        
    }
}

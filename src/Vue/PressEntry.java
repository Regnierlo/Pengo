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
import javax.imageio.ImageIO ;
import javax.swing.JPanel;

/**
 *
 * @author loisr
 */
public class PressEntry extends JPanel {
    
    private final String chemin = "";
    
    public void paintComponent(Graphics g){
        
        try{
            Image img1 = ImageIO.read(new File(chemin+"src/Images/press_entry_1.png"));
            g.drawImage(img1,0,0,this);
                
        }catch (IOException e){
                System.out.println("Pas de première fenêtre");
        }
        
    }
    
}

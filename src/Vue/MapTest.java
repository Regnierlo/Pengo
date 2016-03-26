/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Personnages.P_Pengo;
import Ressources.MyImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author loisr
 */
public class MapTest extends JPanel{
    
    public void paintComponent(Graphics g1, Object o){
        
        Graphics2D g = (Graphics2D)g1;
        
        if(o instanceof P_Pengo){
            P_Pengo p = (P_Pengo) o;
            g.setColor(Color.red);
            g.drawImage(p.getMyImage().getImg(), p.getCoordonnees().getX(), p.getCoordonnees().getY(), this);
        }
    }
}

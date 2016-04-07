/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Personnages.P_Pengo;
import Personnages.Personnage;
import Ressources.MyImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author loisr
 */
public class MapTest extends JPanel {

    
    public List<Personnage> personages = new ArrayList<Personnage>();

    
    
    
    @Override
    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D)gr;
        
        for (Personnage p : personages) {
            //g.drawRect(p.getCoordonnees().getX(), p.getCoordonnees().getY(), 16, 16);
            g.drawImage(p.getMyImage().getImg(), p.getCoordonnees().getX(), p.getCoordonnees().getY(), this);
        }
          
    }
    
}

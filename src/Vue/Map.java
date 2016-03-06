/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Personnages.P_Pengo;
import Ressources.Positions;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.JPanel;

/**
 *
 * @author loisr
 */
public class Map extends JPanel{
    /**
     * Fonction appelé pour que l'image reste sur la fenetre (quand on la place sur le bord par exemple) et repaint la fenetre
     * @param g 
     */
    public void paintComponent(Graphics g, Positions p, Rectangle r){
        g.drawImage(P_Pengo.img, p.getX(), p.getY(), this);
        repaint();
    }
    
    public void paintComponent(Graphics g, Positions p){
        g.drawImage(P_Pengo.img, p.getX(), p.getY(), this);
        repaint();
    }
}

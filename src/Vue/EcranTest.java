/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Personnages.Personnage;
import Ressources.Coordonnees;
import Ressources.MyImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author loisr
 */
public class EcranTest extends JFrame{
    
    private MapTest m;
    
    public EcranTest(KeyListener[] k){
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        for (int i=0;i<k.length;i++) {
            this.addKeyListener(k[i]);
        }
        m=new MapTest();
        this.add(m);
        this.validate(); // because you added panel after setVisible was called
        this.repaint(); // because you added panel after setVisible was called
        this.setVisible(true);

        
          
        
    }
    
    
    public void clearBlock(Coordonnees c) {
        Graphics2D g = (Graphics2D) m.getGraphics();
        g.clearRect(c.getX(), c.getY(), 16, 16);
    }

    
    
    
    public MapTest getM() {
        return m;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Ressources.Coordonnees;
import Ressources.MyImage;
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
        this.setVisible(true);
    }
    
    public void afficheMap(Object o){
        m.paintComponent(this.getGraphics(), o);
    }
    
}

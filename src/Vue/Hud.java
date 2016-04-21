/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Graphics ;
import java.awt.Font ;
import java.awt.Color ;

import javax.swing.JPanel;

/**
 *
 * @author Marie
 */
public class Hud extends JPanel{
    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        System.out.println("Je suis vivant, mwhahaha");
        Font font = new Font("Courier", Font.BOLD,20);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Appuyez sur Entrée", this.getWidth()/3,(this.getHeight()/4)*3) ;
       
        
    }
}

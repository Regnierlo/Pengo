/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import GestionJeux.GameEngine;

import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class PressEntry extends JFrame implements KeyListener{
    
   
    int sortie = 0 ;
    private Entry panel ;
    
    public PressEntry(){
        this.construction();
    }
  
    private void construction(){
        
        this.setSize(400,685);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,1));
        panel = new Entry();
        this.getContentPane().add(panel);
        this.setVisible(true);
       
        addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
    }
    

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            sortie = 1 ;
            this.setVisible(false);
            new GameEngine();
           
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}

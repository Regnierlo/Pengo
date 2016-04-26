/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import GestionJeux.GameEngine;
import java.awt.CardLayout;

import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PressEntry extends JFrame implements KeyListener{
    
   
    int sortie = 0 ;
    CardLayout c1 = new CardLayout();
    String[] listContent = {"CARD_1","CARD_2"};
    int indice = 0 ;
    
    private JPanel content ;
    private Entry panel1 ;
    private Entry panel2;
    private Menu menu ;
    
    public PressEntry(){
        this.construction();
    }
  
    private void construction(){
        
        this.setSize(400,685);
        this.setLocationRelativeTo(null);
        this.setLayout(new CardLayout());
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        content = new JPanel();
        
        panel1 = new Entry("src/Images/press_entry_1.png");
        panel2 = new Entry("src/Images/press_entry_2.png");
        content = panel1 ;
        
        this.getContentPane().add(content);
        this.setVisible(true);
       
        addKeyListener(this);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
        }
        content = panel2 ;
        
    }
    

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            sortie = 1 ;
            this.setVisible(false);
            menu = new Menu();
           
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.CardLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class PressEntry extends JFrame implements KeyListener{
    
  
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
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        panel1 = new Entry(Picture.chemin+"src/Images/press_entry_1.png");
        panel2 = new Entry(Picture.chemin+"src/Images/press_entry_2.png");
        
        this.getContentPane().add(panel1);
        this.setVisible(true);
       
        addKeyListener(this);

        
    }
    

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.setVisible(false);
            menu = new Menu();
           
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}

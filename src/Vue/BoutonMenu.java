/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BoutonMenu extends JButton implements MouseListener {
    
    public BoutonMenu(String txt){
        super(txt);
        this.addMouseListener(this);
        setFont(new Font("Courier", Font.BOLD, 28));
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setOpaque(true);
        setContentAreaFilled(true);
        
        setBorderPainted(false);
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.LEFT);
        setHorizontalTextPosition(SwingConstants.LEFT);
    }
    
    @Override
    public void mouseClicked(MouseEvent event){

    }
    
    @Override
    public void mouseExited(MouseEvent event){
        this.setForeground(Color.WHITE);
        setFont(new Font("Courier", Font.BOLD, 28));
    }
    
    @Override
    public void mouseEntered(MouseEvent event){
        
        this.setForeground(Color.GREEN);
        this.setFont(new Font("Courier", Font.ITALIC,28));
        
    }
    
    @Override
    public void mouseReleased(MouseEvent event){
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(Color.BLACK);
    }
    
}

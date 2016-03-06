/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Ressources.Positions;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author loisr
 */
public class P_Pengo extends Personnage
                    implements KeyListener{

    public P_Pengo(boolean joueur, Positions p){
        try {
            this.joueur = joueur;
            this.pos = p;
            this.vitesse=2;
            this.img = ImageIO.read(getClass().getResource("/Images//pengoFace.png"));
            this.imgWidth = img.getWidth(this.observer);
            this.imgHeight = img.getHeight(this.observer);
            
            } catch (IOException ex) {
            Logger.getLogger(P_Pengo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        
    }
    
    public KeyListener getKeyListener(){
        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(joueur && (e.getKeyCode()==KeyEvent.VK_Z)){
            this.pos.setY(this.pos.getY()-this.vitesse);
            this.move = true;
        }
        if(joueur && (e.getKeyCode()==KeyEvent.VK_S)){
            this.pos.setY(this.pos.getY()+this.vitesse);
            this.move = true;
        }
        if(joueur && (e.getKeyCode()==KeyEvent.VK_Q)){
            this.pos.setX(this.pos.getX()-this.vitesse);
            this.move = true;
        }
        if(joueur && (e.getKeyCode()==KeyEvent.VK_D)){
            this.pos.setX(this.pos.getX()+this.vitesse);
            this.move = true;
        }
        if(joueur && (e.getKeyCode()==KeyEvent.VK_SPACE)){
            System.out.println("Action bloc");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}

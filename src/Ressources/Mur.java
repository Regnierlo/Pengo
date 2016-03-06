/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author loisr
 */
public class Mur {
    private Image img;

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    private Positions pos;
    
    public Mur(Positions p){
        try {
            this.pos=p;
            this.img=ImageIO.read(getClass().getResource("/Images//mur.png"));
        } catch (IOException ex) {
            Logger.getLogger(Mur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the pos
     */
    public Positions getPositions() {
        return pos;
    }
    
    
}

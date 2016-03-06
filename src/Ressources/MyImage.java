/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author loisr
 */
public class MyImage {
    
    private ImageObserver observer;
    private Image img;
    private int width;
    private int height;
    
    /**
     * Constructeur de MyImage, passe l'URL de l'image, l'URL doit etre un lien dans le projet.
     * 
     * @param urlImageRessource 
     */
    public MyImage(String urlImageRessource){
        try {
            img = ImageIO.read(getClass().getResource(urlImageRessource));
            width = img.getWidth(this.observer);
            height = img.getHeight(this.observer);
        } catch (IOException ex) {
            Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retourne l'observer de l'image.
     * 
     * @return the observer
     * @version 1.0
     */
    public ImageObserver getObserver() {
        return observer;
    }

    /**
     * Retourne l'image.
     * 
     * @return the img
     * @version 1.0
     */
    public Image getImg() {
        return img;
    }

    /**
     * Retourne la largeur de l'image.
     * 
     * @return the width
     * @version 1.0
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur de l'image.
     * 
     * @return the height
     * @version 1.0
     */
    public int getHeight() {
        return height;
    }
}

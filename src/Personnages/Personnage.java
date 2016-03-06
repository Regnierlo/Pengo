/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Ressources.Positions;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 *
 * @author loisr
 */
public abstract class Personnage extends Thread{
    
    /**
     * Position du personnage sur un axe 2D (X/Y)
     */
    protected Positions pos;
    /**
     * Vitesse du personnage
     */
    protected int vitesse;
    /**
     * Indique si le personnage est controlle par un joueur ou par l'ordinateur
     */
    protected boolean joueur;
    /**
     * Permet de savoir si le thread est fini et de faire un arret propre avec la fonction @see arreter
     * @see arreter
     */
    protected boolean fini = false;
    /**
     * Permet de savoir si le personnage a bougé
     */
    protected boolean move = false;
    /**
     * Image de la classe
     */
    public static Image img;
    /**
     * Observer
     */
    protected ImageObserver observer;
    /**
     * Hauteur de l'image
     */
    protected int imgHeight;
    /**
     * Largeur de l'image
     */
    protected int imgWidth;
    
    public abstract void run();
    
    /**
     * Permet de detruire un bloc
     */
    public void detruireBloc(){
        
    }
    
    /**
     * Remet a false la variable move (utilise pour savoir si l'information a été prise)
     */
    public void informationMoveTaken(){
        move = false;
    }
    /**
     * Retourne move (utilisé pour savoir si le personnage a bougé)
     */
    public boolean getMove(){
        return move;
    }
    
    /**
     * Arrete les processus de facon propre
     */
    public void arreter(){
        fini = true;
    }
    
    /**
     * Retourne la position du personnage
     * @return Positions
     */
    public Positions getPositions(){
        return pos;
    }
    
    /**
     * Retourne la source de l'image
     * @return la source
     */
    public ImageProducer getSource() {
        return img.getSource();
    }

    /**
     * 
     * @return 
     */
    public Graphics getGraphics() {
        return img.getGraphics();
    }

    /**
     * 
     * @param name
     * @param observer
     * @return 
     */
    public Object getProperty(String name, ImageObserver observer) {
        return img.getProperty(name, observer);
    }

    /**
     * @return the imgHeight
     */
    public int getImgHeight() {
        return imgHeight;
    }

    /**
     * @return the imgWidth
     */
    public int getImgWidth() {
        return imgWidth;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import Ressources.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author loisr
 */
public abstract class Personnage extends Thread
                        implements KeyListener{

    /**
     * Image utilisé par le personnage.
     */
    protected MyImage img;
    /**
     * Coordonnees du personnage.
     */
    protected Coordonnees coord;
    /**
     * Vitesse du personnage.
     */
    protected int vitesse;
    /**
     * Permet de savoir si le thread est controle par un joueur ou non.
     */
    protected boolean joueur;
    /**
     * Permet de savoir si le thread doit continuer a utiliser le run.
     */
    protected boolean fini=false;
    /**
     * Permet d'orienter le personnage.
     */
    protected Orientation orientation;
    
    protected Personnage(String urlImageRessource, Coordonnees c, boolean j, int v){
        this.coord = c;
        this.img = new MyImage(urlImageRessource, this.coord);
        this.vitesse = v;
        this.joueur = j;
    }
    
    protected void Move(int x, int y){
        int nx = this.coord.getX()+x;
        int ny = this.coord.getY()+y;
        
        this.coord.setX(nx);
        this.coord.setY(this.coord.getY()+y);
        this.getMyImage().Move(new Coordonnees(nx, ny));
    }
    
    /**
     * Fonction run du thread.
     */
    public abstract void run();
    
    /**
     * Fonction appelée lorsqu'une touche est enfoncé
     * @param e 
     */
    @Override
    public abstract void keyPressed(KeyEvent e);
    
    /**
     * Permet d'arreter le thread de façon propre lorsqu'il utilise un run.
     */
    public void arreter(){
        fini=true;
    }
    
    /**
     * 
     * @return les coordonnées
     */
    public Coordonnees getCoordonnees(){
        return coord;
    }
    
    /**
     * 
     * @return l'instance de MyImage
     */
    public MyImage getMyImage(){
        return img;
    }
    
    public Boolean getJoueur(){
        return joueur;
    }
}

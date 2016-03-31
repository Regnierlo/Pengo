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
     * Enumeration pour les directions
     */
    protected enum Directions {
        //Liste des énumérations
        dirHaut ("HAUT"),
        dirBas ("BAS"),
        dirDroite ("DROITE"),
        dirGauche ("GAUCHE");
        
        //Utilisé pour le constructeur
        private final String name;       
        
        //Constructeurs
        private Directions(String s) {
            name = s;
        }
        
        //Comparateur
        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
           return this.name;
        }
    }
    
    protected Directions directionActuel;
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
        this.directionActuel = Directions.dirHaut;
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
     * @return La direction du personnage (où il regarde)
     */
    public Directions getDirectionActuel(){
        return this.directionActuel;
    }
    
    /**
     * 
     * @return La vitesse de déplacement du personnage
     */
    public int getVitesse(){
        return this.vitesse;
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

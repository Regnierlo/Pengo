/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import GestionJeux.GameTest;
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

    
    private GameTest gametest;
    public void setGametest(GameTest gametest) {
        this.gametest = gametest;
    }
    
    
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
    public enum Directions {
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
    /**
     * Tableau contenant les images pour les animations
     */
    protected MyImage[] animation;
    
    protected Personnage(String urlImageRessource, Coordonnees c, boolean j, int v){
        this.coord = c;
        this.img = new MyImage(urlImageRessource, this.coord);
        this.directionActuel = Directions.dirHaut;
        this.vitesse = v;
        this.joueur = j;
    }
    
    /**
     * 
     * @version 2.0 Permet de se déplacer d'un point a un autre automatiquement
     * @param x
     * @param y 
     */
    protected void Move(int x, int y){
        int nx = this.coord.getX()+x;
        int ny = this.coord.getY()+y;
        int reste;
        if((this.coord.getX()-nx) != 0)
            reste = x;
        else
            reste = y;
        if(reste < 0)
            reste *= (-1);
        
        for(int i = 0;i<this.getMyImage().getHeight();i++){
            
            gametest.clearBlock(this.coord);
            
            
            this.coord.setX(this.coord.getX()+(x/reste));
            this.coord.setY(this.coord.getY()+(y/reste));
            this.getMyImage().Move(new Coordonnees(this.coord.getX(), this.coord.getY()));
            //gametest.getEc().revalidate();
            System.out.println(this.coord);            
            gametest.update();
            
            try {
                Thread.sleep(16*this.vitesse);
            } catch (Exception e) {
            }
        }
        
        /*
        //Ancienne version
        this.coord.setX(nx);
        this.coord.setY(this.coord.getY()+y);
        this.getMyImage().Move(new Coordonnees(nx, ny));
        */
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

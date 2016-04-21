package Personnages;

import GestionJeux.GameEngine;
import Ressources.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Personnage extends Thread
                        implements KeyListener{

    protected boolean stop;
    protected boolean pousseDetruit;

    /**
     * Coordonnees du personnage.
     */
    protected Coordonnees coord;
    
    public enum Actions {
        bouger ("BOUGER"),
        pousser_detruire ("POUSSER_DETRUIRE");
        
        private final String name;
        
        private Actions(String s){
            name = s;
        }
        
        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        @Override
        public String toString() {
           return this.name;
        }
    }
    
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
    protected Directions ancienneDirection ;
    protected boolean direction ;
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
    protected String[][] animationMouvement;
    protected int currentImage;
    protected boolean pause;
    
    protected final GameEngine ge;
    protected int pousse ;
    protected Personnage(String urlImageRessource, Coordonnees c, boolean j, int v, GameEngine g){
        this.stop = false;
        this.pause = false;
        this.coord = c;
        this.directionActuel = Directions.dirDroite;
        this.ancienneDirection = Directions.dirHaut ;
        direction = false ;
        this.vitesse = v;
        this.joueur = j; 
        this.ge = g;
        currentImage = 0;
        animationMouvement = new String[4][2];
        pousseDetruit = false;
        pousse = 0 ;
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
    
    public void pause(){
        pause = true;
    }
    
    public void reprise(){
        pause = false;
    }
    
    /**
     * 
     * @return La direction du personnage (où il regarde)
     */
    public Directions getDirectionActuel(){
        return this.directionActuel;
    }
    public boolean getBoolDirection(){
        return this.direction ;
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
    
    
    public boolean getJoueur(){
        return joueur;
    }
    
    public void stopper(){
        this.stop = true;
    }
    
    public boolean getStopper(){
        return stop;
    }
    
    public Directions getAncienneDirection(){
        return ancienneDirection ;
    }
    
    public void setPousseDetruire(boolean b){
        
        pousseDetruit = b;
        pousse++;
    }
    public int getPousseInt(){
        return pousse ;
    }
    
    public boolean getPousseDetruire(){
        return pousseDetruit;
    }
}

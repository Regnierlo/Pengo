/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

public class Coordonnees {
    private int x;
    private int y;
    
    /**
     * Constructeur par defaut, mets les coordonnees en [0;0].
     * 
     * @version 1.0
     */
    public Coordonnees(){
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Constructeur avec parametres.
     * 
     * @param x Coordonnees en x
     * @param y Coordonnees en y
     * @version 1.0
     */
    public Coordonnees(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnee en x.
     * 
     * @return the x
     * @verion 1.0
     */
    public int getX() {
        return x;
    }

    /**
     * Modifie la coordonnee en x.
     * 
     * @param x the x to set
     * @verion 1.0
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnee en y.
     * 
     * @return the y
     * @verion 1.0
     */
    public int getY() {
        return y;
    }

    /**
     * Modifie la coordonnee y.
     * 
     * @param y the y to set
     * @verion 1.0
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public void setCoordonnees(Coordonnees c){
        this.x = c.getX();
        this.y = c.getY();
    }
    
    public boolean comp(Coordonnees c){
        boolean r = false;
        
        if(this.x == c.getX())
            if(this.y == c.getY())
                r=true;
        
        return r;
    }
    
    @Override
    public String toString(){
        return "["+this.x+";"+this.y+"]";
    }
}

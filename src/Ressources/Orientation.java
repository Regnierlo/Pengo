/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

/**
 *
 * @author loisr
 */
public class Orientation {
    
    private char droite='D';
    private char gauche='G';
    private char haut='H';
    private char bas='B';
    
    private char orientationActuelle;
    
    public Orientation(){
        this.orientationActuelle = this.getBas();
    }

    /**
     * Retourne le char quand le personnage regarde vers la droite.
     * 
     * @return the droite
     * @version 1.0
     */
    public char getDroite() {
        return droite;
    }

    /**
     * Retourne le char quand le personnage regarde vers la gauche.
     * 
     * @return the gauche
     * @version 1.0
     */
    public char getGauche() {
        return gauche;
    }

    /**
     * Retourne le char quand le personnage regarde vers le haut.
     * 
     * @return the haut
     * @version 1.0
     */
    public char getHaut() {
        return haut;
    }

    /**
     * Retourne le char quand le personnage regarde vers le bas.
     * 
     * @return the bas
     * @version 1.0
     */
    public char getBas() {
        return bas;
    }

    /**
     * Retourne le char de l'orientation Actuelle.
     * 
     * @return the orientationActuelle
     * @version 1.0
     */
    public char getOrientationActuelle() {
        return orientationActuelle;
    }

    /**
     * Mets à jour l'orientation actuelle.
     * 
     * @param orientationActuelle the orientationActuelle to set
     * @version 1.0
     */
    public void setOrientationActuelle(char orientationActuelle) {
        this.orientationActuelle = orientationActuelle;
    }
    
} 

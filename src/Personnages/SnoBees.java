/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personnages;

import GestionJeux.GameEngine;
import Ressources.Coordonnees;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loisr
 */
public class SnoBees extends Personnage{
    
    private boolean paralyse;
    private boolean vaMourirParBloc;
    private boolean cacheDansBloc;
    private int naissance;
    private int naissanceFini;
    private boolean nait;
    
    public SnoBees(Coordonnees c, boolean joueur, GameEngine g, boolean cache){
        super("",c,joueur,3,g);
        paralyse = false;
        vaMourirParBloc = false;
        cacheDansBloc = cache;
        naissance = 1;
        naissanceFini = 7;
        nait = true;
    }

    @Override
    public synchronized void run() {
        if(nait){
            naissance();
            nait = false;
        }
        while(!stop){
            if(!paralyse){
                ancienneDirection=Directions.dirBas ;
                directionActuel=Directions.dirDroite;
                //ge.action(this, Directions.dirDroite, Actions.bouger);
                try {
                    Thread.sleep(1000-(1*this.vitesse));
                } catch (Exception e) {
                }
            }
            else{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SnoBees.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void setNait(boolean b){
        nait = b;
    }
    
    public void setVaMourirParBloc(boolean b){
        vaMourirParBloc = b;
    }
    
    public boolean getVaMourirParBloc(){
        return vaMourirParBloc;
    }
    
    public void setParalyse(boolean p){
        paralyse = p;
    }
    
    public boolean getParlyse(){
        return paralyse;
    }
    
    public void setCacheDansBloc(boolean b){
        cacheDansBloc = b;
    }
    
    public boolean getCacheDansBloc(){
        return cacheDansBloc;
    }
    public int getNaissanceFinie(){
        return naissanceFini ;
    }
    
    private void naissance(){
        for(int i=0;i<naissanceFini;i++){
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(SnoBees.class.getName()).log(Level.SEVERE, null, ex);
            }
            naissance++;
            //this.ge.majAfficheCarte();
        }
    }
    
    public int getNaissance(){
        return naissance;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Z)){
            bougeHaut();
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_S)){
            bougeBas();
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_D )){
            bougeDroite();
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_Q)){
            bougeGauche();
        }
        if(joueur && (e.getKeyCode() == KeyEvent.VK_SPACE)){
            detruire();
        }
    }
    
    private void bougeHaut(){
        ancienneDirection = directionActuel;
        if(directionActuel.equals(Directions.dirHaut) && !direction)
            direction = true;
        else
            direction = false;
        directionActuel = Directions.dirHaut;
        this.ge.action(this, directionActuel, Actions.bouger);
    }
    
    private void bougeBas(){
        ancienneDirection = directionActuel;
        if(directionActuel.equals(Directions.dirBas) && !direction)
            direction=true;
        else
            direction=false;
        directionActuel=Directions.dirBas;
        this.ge.action(this, directionActuel, Actions.bouger);
    }
    
    private void bougeDroite(){
        ancienneDirection = directionActuel;
        if(directionActuel.equals(Directions.dirDroite)&& !direction)
            direction = true ;
        else
            direction = false ;
        directionActuel=Directions.dirDroite;
        this.ge.action(this, directionActuel, Actions.bouger);
    }

    private void bougeGauche(){
        ancienneDirection = directionActuel;
        if(directionActuel.equals(Directions.dirGauche) && !direction)
            direction = true;
        else
            direction=false;
        directionActuel=Directions.dirGauche;
        this.ge.action(this, directionActuel, Actions.bouger);
    }
    
    private void detruire(){
        this.ge.action(this, directionActuel, Actions.pousser_detruire);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

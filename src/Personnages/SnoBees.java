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

public class SnoBees extends Personnage{
    
    private boolean paralyse;
    private boolean vaMourirParBloc;
    private boolean cacheDansBloc;
    private int naissance;
    private final int itsAlive;
    private boolean nait ;
    
    public enum typeSnobees{
        
        normal("normal"),
        miRamboMiIdiot("miRambo"),
        rambo("rambo");
        
        private final String name;
        
        private typeSnobees(String s){
            name = s;
        }
        
        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }
        
        public String toString() {
           return this.name;
        }
    }
    
    private typeSnobees ts;
    
    public SnoBees(Coordonnees c, boolean joueur, GameEngine g, boolean cache, typeSnobees type){
        super("",c,joueur,100,g);
        paralyse = false;
        vaMourirParBloc = false;
        cacheDansBloc = cache;
        naissance = 1;
        itsAlive = 7;
        nait = true;
        ts = type;
    }

    @Override
    public void run() {
        int sommeil = 1000 ;
        if(nait){
            naissance();
            nait = false;
        }
        
        while(!stop){
            if(!paralyse){/*
                ancienneDirection=Directions.dirBas ;
                directionActuel=Directions.dirDroite;
                //ge.action(this, Directions.dirDroite, Actions.bouger);
                */
                switch (ts) {
                    case normal :
                        sommeil = 500 ;
                        algoIdiot() ; 
                        break ;
                    case miRamboMiIdiot : 
                        sommeil = 350 ;
                        algoSnobo(2); 
                        break ;
                    case rambo : 
                        sommeil = 200 ;
                        algoSnobo(7); 
                        break ;
                }
                ge.majAfficheCarte();
                try {
                    Thread.sleep(sommeil);
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
    
    private void algoIdiot(){
        /// se promène sans rien détruire, dans la boolDirection qu'il aime bien.
        boolean fait = false ;
        int random ; 
            random = (int)(Math.random()*16+1);
            switch (random) {
                case 1 : case 2 : case 3 : case 4 : fait = bougeBas() ; break ;
                case 5 : case 6 : case 7 : case 8 : fait = bougeHaut() ; break ;
                case 9 : case 10 : case 11 : case 12 : fait = bougeGauche() ; break ;
                case 13 : case 14 : case 15 : case 16 : fait = bougeDroite() ; break ;
            } 
    }
    
    
    
    private void algoSnobo(int detection){
        boolean fait =false   ;
        Coordonnees coordPengo = null ;
        char possible ='P' ;
        int ligne ;
        int colonne ;
        
            /// On regarde si Pengo est dans le périmètre du snobee dans un rayon de "detection"
            coordPengo = ge.PengoDetected(detection, this.coord);
            /// Si Pengo trouvé
            if(coordPengo!=null){
               // System.out.println("PENGO DETECTED. EXTERMINATE, EXTERMINATE");
                ligne = this.coord.getY() - coordPengo.getY() ;
                colonne = this.coord.getX() - coordPengo.getX() ;
                if(ligne >0){///Pengo au dessus de Snobee
                    possible = ge.AvancerDetruire(this.coord, Directions.dirHaut);      System.out.println("possible "+possible);
                    if(possible=='D'){ /// On détruit le bloc
                        detruire(Directions.dirHaut);
                    }
                    else if(possible=='A') /// on avance
                        bougeHaut();
                }
                else if(ligne <0){ /// Pengo au dessous
                    possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirBas);           System.out.println("possible "+possible);
                    if(possible=='D'){
                        detruire(Directions.dirBas);
                    }
                    else if(possible=='A')
                        bougeBas();
                }
                if(colonne > 0 && ligne==0){
                            possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirGauche);     System.out.println("possible "+possible);
                            if(possible=='D'){
                                detruire(Directions.dirGauche);
                            }
                            else if(possible=='A')
                                bougeGauche();
                    }
                else 
                    if(colonne<0 && ligne==0)
                        if(possible!='A' && possible!='D'){
                            possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirDroite);          System.out.println("possible "+possible);
                            if(possible=='D'){
                                detruire(Directions.dirDroite);
                            }
                            else if(possible=='A')
                                bougeDroite();
                    }
            }
            else{
                if(detection>5){ /// Si Snobee très méchant : détruit tout ce qu'il peut
                    possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirDroite);
                    if(possible =='D')
                        fait = detruire(Directions.dirDroite);
                    else {
                        possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirGauche);
                        if(possible =='D')
                            fait = detruire(Directions.dirGauche);
                        else{
                            possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirHaut);
                            if(possible=='D')
                                fait = detruire(Directions.dirHaut);
                            else{
                                possible = ge.AvancerDetruire(this.coord, Personnage.Directions.dirBas);
                                if(possible=='D')
                                    fait = detruire(Directions.dirBas);
                            }       
                        }     
                    }
                    if(!fait)
                        algoIdiot();
                }
                else /// si Snobee pas trop méchant ne va pas intentionnellement détruire tout sur son passage
                    algoIdiot();
            }
    
    }
    
    public void setNait(boolean b){
        nait = b;
    }
    public boolean getNait(){
        return nait ;
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
    
    public boolean getParalyse(){
        return paralyse;
    }
    
    public void setCacheDansBloc(boolean b){
        cacheDansBloc = b;
    }
    
    public boolean getCacheDansBloc(){
        return cacheDansBloc;
    }
    public int getNaissanceFinie(){
        return itsAlive ;
    }
    
    private void naissance(){
        for(int i=0;i<itsAlive;i++){
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(SnoBees.class.getName()).log(Level.SEVERE, null, ex);
            }
            naissance++;
            ge.progressNaissante(i+1, coord, ts);
            //this.ge.majAfficheCarte();
        }
        setNait(false);
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
            detruire(directionActuel);
        }
    }
    
   
    private boolean bougeHaut(){
        boolean moveOk = this.ge.action(this, Directions.dirHaut, Actions.bouger) ;
        if (moveOk) {
            this.ancienneDirection = this.directionActuel;
            this.boolDirection = this.ancienneDirection.equals(Directions.dirHaut) && !boolDirection ;
            this.setDirection(Directions.dirHaut) ;
          //  this.directionActuel = Directions.dirHaut;
        }
        return moveOk ;
    }
    
    private boolean bougeBas(){
        boolean moveOk = this.ge.action(this, Directions.dirBas, Actions.bouger) ;
        if (moveOk) {
            this.ancienneDirection = this.directionActuel;
            this.boolDirection = ancienneDirection.equals(Directions.dirBas) && !boolDirection ;
            this.setDirection(Directions.dirBas);
            
        }
        return moveOk ;
    }
    
    private boolean bougeDroite(){
        boolean moveOk = this.ge.action(this, Directions.dirDroite, Actions.bouger) ;
        if (moveOk) {
            ancienneDirection = directionActuel;
            boolDirection = directionActuel.equals(Directions.dirDroite) && !boolDirection ;
            directionActuel = Directions.dirDroite;
        }
        return moveOk ;
    }

    private boolean bougeGauche(){
        boolean moveOk = this.ge.action(this, Directions.dirGauche, Actions.bouger) ;
        if (moveOk) {
            ancienneDirection = directionActuel;
            boolDirection = directionActuel.equals(Directions.dirGauche) && !boolDirection ;
            directionActuel = Directions.dirGauche;
        }
        return moveOk ;
    }
    
    private boolean detruire(Directions dir){
        boolean destructionOk = this.ge.action(this, dir, Actions.pousser_detruire) ;
        if(destructionOk){
            directionActuel = dir ;
            switch (directionActuel){
                case dirHaut : bougeHaut() ; break ;
                case dirBas : bougeBas() ; break ;
                case dirGauche : bougeGauche() ; break ;
                case dirDroite : bougeDroite() ; break ;
            }
        }
        return destructionOk ;
    }
    
    public void setComportement(typeSnobees t){
        ts = t;
    }
    
    public typeSnobees getComportement(){
        return ts;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setDirection(Directions d){
        this.directionActuel = d ;
    }
}

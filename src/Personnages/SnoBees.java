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
    private boolean nait;
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
        super("",c,joueur,3,g);
        paralyse = false;
        vaMourirParBloc = false;
        cacheDansBloc = cache;
        naissance = 1;
        itsAlive = 7;
        nait = true;
        ts = type;
       // ancienneDirection=Directions.dirHaut ;
    }

    @Override
    public void run() {
        
        int sommeil = 700 ;
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
                        algoIdiot() ; 
                        break ;
                    case miRamboMiIdiot : 
                        algoMiIdiotMiSnobo(); 
                        break ;
                    case rambo : 
                        sommeil = 500 ;
                        algoSnobo(); 
                        break ;
                }
                try {
                    Thread.sleep(sommeil-(1*this.vitesse));
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
        do{
            random = (int)(Math.random()*16+1);
            switch (random) {
                case 1 : case 2 : case 3 : case 4 : fait = bougeBas() ; break ;
                case 5 : case 6 : case 7 : case 8 : fait = bougeHaut() ; break ;
                case 9 : case 10 : case 11 : case 12 : fait = bougeGauche() ; break ;
                case 13 : case 14 : case 15 : case 16 : fait = bougeDroite() ; break ;
            } 
        } while(fait!=true);
    }
    
    private void algoMiIdiotMiSnobo(){
        /// Il est insouciant mais quand Pengo rentre dans son périmètre de sécurité il se sent menacé et commence à détruire des blocs
        boolean fait = false ;
        
        
        
        
    }
    
    private void algoSnobo(){
        boolean fait = false  ;
        Coordonnees coordPengo = null ;
        int ligne = 0 ;
        int colonne = 0 ;
        int deplacementSnobo ;
        do{
            coordPengo = ge.PengoDetected(1, this.coord);
            
            if(coordPengo!=null){
                ligne = this.coord.getY() - coordPengo.getY() ;
                colonne = this.coord.getX() - coordPengo.getX() ;
                if(ligne==0) deplacementSnobo = 2 ;
                else 
                    if(colonne==0) deplacementSnobo = 0 ;
                    else 
                        if(ligne>=colonne) deplacementSnobo = 1 ;
                        else
                            deplacementSnobo = 3 ;
                
                if(!fait && deplacementSnobo<2){
                        if(ligne>0){
                            fait = detruire(Directions.dirHaut) ;
                            if(!fait)
                                fait = bougeHaut() ;
                            if(!fait)
                                deplacementSnobo =3 ;
                        }
                        else {
                            fait = detruire(Directions.dirBas) ;
                            if(!fait)
                                fait = bougeBas() ;
                            if(!fait)
                                deplacementSnobo =3 ;
                        }
                    /// Va soit à gauche soit à droite
                }
                if(!fait && deplacementSnobo>1){    
                    if(colonne>0){
                        fait = detruire(Directions.dirGauche) ;
                        if(!fait)
                            fait = bougeGauche() ;
                    }
                    else {
                        fait = detruire(Directions.dirDroite) ;
                        if (!fait)
                            fait = bougeDroite();
                    }
                } 
            }
            while(!fait){
                deplacementSnobo = (int)(Math.random()*4+1);
                switch (deplacementSnobo) {
                    case 1 : if(!fait) fait = detruire(Directions.dirBas) ; 
                    case 2:  if(!fait) fait = detruire(Directions.dirHaut) ; 
                    case 3 : if(!fait) fait = detruire(Directions.dirGauche) ; 
                    case 4 : if(!fait) fait = detruire(Directions.dirDroite) ; break ;
                }
                if(!fait)
                    switch (deplacementSnobo) {
                        case 1 : if(!fait) fait = bougeBas() ;  break ;
                        case 2: if(!fait) fait = bougeHaut() ;  break ; 
                        case 3 : if(!fait) fait = bougeGauche() ; break ;
                        case 4 : if(!fait) fait = bougeDroite() ;  break ;
                    }
            }
            
        } while(!fait) ;
        
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
        ancienneDirection = directionActuel;
        directionActuel = Directions.dirHaut;
        return this.ge.action(this, directionActuel, Actions.bouger);
    }
    
    private boolean bougeBas(){
        ancienneDirection = directionActuel;
        directionActuel=Directions.dirBas;
        return this.ge.action(this, directionActuel, Actions.bouger);
    }
    
    private boolean bougeDroite(){
        ancienneDirection = directionActuel;
        directionActuel=Directions.dirDroite;
        return this.ge.action(this, directionActuel, Actions.bouger);
    }

    private boolean bougeGauche(){
        ancienneDirection = directionActuel;
        directionActuel=Directions.dirGauche;
        return this.ge.action(this, directionActuel, Actions.bouger);
    }
    
    private boolean detruire(Directions directionAnctuel){
        return this.ge.action(this, directionActuel, Actions.pousser_detruire);
    }
    
    /*private boolean bougeHaut(){
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
            switch (dir){
                case dirHaut : bougeHaut() ; break ;
                case dirBas : bougeBas() ; break ;
                case dirGauche : bougeGauche() ; break ;
                case dirDroite : bougeDroite() ; break ;
            }
        }
        return destructionOk ;
    }*/
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import GestionJeux.GameEngine;
import Personnages.P_Pengo;
import Personnages.Personnage;
import Personnages.SnoBees;
import Ressources.BlocGlace;
import Ressources.BlocSpecial;
import Ressources.Coordonnees;

public class Calcul_Vue {
    private int nbCoordonnees ;
    private int[][] tabCoordonnees;
    GameEngine jesus ;
    int naissance ;
    
    public Calcul_Vue(GameEngine gameEngine){
        nbCoordonnees = 0 ;
        tabCoordonnees = new int[2][300] ;
        
            for(int j = 0 ; j< 300 ; j++){
                tabCoordonnees[0][j] = 1 ;
                tabCoordonnees[1][j] = 32 ;
            }
        jesus = gameEngine ;
    }
    
    public synchronized int[][] setCoordonnees(String[][] s){
        nbCoordonnees = 0 ;
        for(int i = 0 ; i < s.length ; i++){
            for(int j = 0 ; j < s[0].length ; j++){
                
                /// SI MUR 
                
                if(i==0 || i==15 || j==0 ||j==9){
                    
                    //// SI MUR TREMBLE
                    
                    if(s[i][j].equals("W")){
                        if(i==0 && j>0 && j<9){
                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                            tabCoordonnees[1][nbCoordonnees]= 37 ;
                        }
                        else{
                            if(i==15 && j>0 && j<9){
                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                tabCoordonnees[1][nbCoordonnees]= 35 ;
                            }
                            else{
                                if(i>0 && i<15 && j==0){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 38 ;
                                }
                                else{
                                    if(j==9 && i>0 && i<15){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 36 ;
                                    }
                                }
                            }
                        }
                    }
                    
                    /// SI MUR NE TREMBLE PAS
                    
                    else{
                        if(i==0){
                            if(j==0){
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 40 ;    
                            }
                            else {
                                if(j==9){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 40 ;
                                }
                                else{
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 37 ;
                                }
                            }
                        }
                        else{
                            if(j==0){
                                if(i==15){
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 39 ;
                                }
                                else{ 
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 38 ;
                                }
                            }

                            else {
                                if(i==15){
                                    if(j==9){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 39 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 35 ;
                                    }
                                }
                                else{
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 36 ;
                                }
                            }
                        }
                    }   
                }
                
                /// SI PAS MUR 
                else {
                    switch (s[i][j]){
                        /// SI Pengo Pousse Après 
                        case "A" : {
                            switch (jesus.getPengo(new Coordonnees(j,i)).getDirectionActuel()) {
                                case dirHaut:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 4 ;
                                    break;
                                case dirDroite:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 5 ;
                                    break;
                                case dirBas:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 6 ;
                                    break;
                                case dirGauche:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 7 ;
                                    break;  
                                default:
                                    break;
                            }
                        } break ;
                        
                        /// SI Pengo pousse (pendant)
                        case "D" : {
                            switch (jesus.getPengo(new Coordonnees(j,i)).getDirectionActuel()) {
                                case dirHaut:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 4 ;
                                    break;
                                case dirDroite:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 5 ;
                                    break;
                                case dirBas:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 6 ;
                                    break;
                                case dirGauche:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 7 ;
                                    break;  
                                default:
                                    break;
                            }
                        } break ;
                        
                        //// PENGO NORMAL 
                        case "P" : {
                                P_Pengo pengo = jesus.getPengo(new Coordonnees(j,i));
                                switch (pengo.getDirectionActuel()) {
                                    case dirHaut:
                                        if(pengo.getAncienneDirection().equals(pengo.getDirectionActuel()) 
                                                && pengo.getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 0 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 0 ;
                                        }
                                        break;
                                    case dirDroite:
                                        if(pengo.getAncienneDirection().equals(pengo.getDirectionActuel())
                                            && pengo.getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 1 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 1 ;
                                        }
                                        break;
                                    case dirBas:
                                        if(pengo.getAncienneDirection().equals(pengo.getDirectionActuel())
                                                && pengo.getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 2 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 2 ;
                                        }
                                        break;
                                    case dirGauche:
                                        if(pengo.getAncienneDirection().equals(pengo.getDirectionActuel()) && 
                                                pengo.getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 3 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 3 ;
                                        }
                                        break;
                                    default:
                                        break;
                                }      
                        } break ;
                        
                        /// SI PENGO EST MORT
                            case "O" : {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 8 ;
                            } break ;

                            case "Q" : {
                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                tabCoordonnees[1][nbCoordonnees]= 8 ;
                            } break ;

                        
                        //// SNO BEE
                        //// Naissance :
                        
                            case "1" : {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 9 ;
                            } break ;

                            case "2" : {
                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                tabCoordonnees[1][nbCoordonnees]= 9 ;
                            } break ;
                        
                            case "3" : {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 10 ;
                            } break ;

                            case "4" : {
                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                tabCoordonnees[1][nbCoordonnees]= 10 ;
                            } break ;
                            
                            case "5" : {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 11 ;
                            } break ;

                            case "6" : {
                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                tabCoordonnees[1][nbCoordonnees]= 11 ;
                            } break ;
                        
                        
                        
                        case "E" : {
                        /// Si le snobee va en haut
                            SnoBees sb = jesus.getSnobee(new Coordonnees(j,i));
                            System.out.println("------> Direction du snobee : " + sb.getDirectionActuel());
                            switch (sb.getDirectionActuel()) {
                                
                            /// FIN si va en haut
                                case dirHaut:
                                    if(sb.getAncienneDirection().equals(sb.getDirectionActuel()) 
                                            && sb.getBoolDirection() 
                                            && !sb.getVaMourirParBloc()){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 12 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 12 ;
                                    }
                                    break;
                            /// FIN si va à droite
                                case dirDroite:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirDroite)
                                            && jesus.getSnobee(new Coordonnees(j,i)).getBoolDirection()
                                            && jesus.getSnobee(new Coordonnees(j,i)).getVaMourirParBloc()!=true){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 13 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 13 ;
                                    }
                                    break;
                            /// FIN si va en bas
                                case dirBas:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirBas)
                                            && jesus.getSnobee(new Coordonnees(j,i)).getBoolDirection() 
                                            && jesus.getSnobee(new Coordonnees(j,i)).getVaMourirParBloc()!=true){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 14 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 14 ;
                                    }
                                    break;
                            /// FIN si va à gauche
                                case dirGauche:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirGauche)
                                            && jesus.getSnobee(new Coordonnees(j,i)).getBoolDirection()
                                            && jesus.getSnobee(new Coordonnees(j,i)).getVaMourirParBloc()!=true){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 15 ;

                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 15 ;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } break ;
                        
                        /// SI snobee paralysé
                        case "K" : {
                            double random = Math.random()*521;
                            if(random < 255){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 18 ;
                            }
                            else {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 18 ;
                            }
                        } break ;
                        
                        // Si poussé
                        case "Y" : {
                            if(jesus.getSnobee(new Coordonnees(j,i))!=null)
                            switch (jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel()) {
                            /// FIN si va en haut
                                case dirHaut:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 16 ;
                                    break;
                            /// FIN si va à droite
                                case dirDroite:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 16 ;
                                    break;
                            /// FIN si va en bas
                                case dirBas:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 17 ;
                                    break;
                            /// FIN si va à gauche
                                case dirGauche:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 17 ;
                                    break;
                                default:
                                    break;
                            }   
                        } break ;    
                        
                        /// SI SNOBEE MI IDIOT MI RAMBO
                        case "I" : { /// Si va en haut
                            switch (jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel()) {
                            /// FIN si va en haut
                                case dirHaut:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirHaut)){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 19 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 19 ;
                                    }
                                    break;
                            /// FIN si va à droite
                                case dirDroite:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirDroite)){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 20 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 20 ;
                                    }
                                    break;
                            /// FIN si va en bas
                                case dirBas:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirBas)){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 21 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 21 ;
                                    }
                                    break;
                            /// FIN si va à gauche
                                case dirGauche:
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(Personnage.Directions.dirGauche)){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 22 ;

                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 22 ;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } break ;
                        
                        case "J" : {
                            double random = Math.random();
                            if(random < 0.5){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 25 ;
                            }
                            else {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 25 ;
                            }
                        } break ;
                        
                        // Si poussé
                        case "V" : {
                            switch (jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel()) {
                            /// FIN si va en haut
                                case dirHaut:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 23 ;
                                    break;
                            /// FIN si va à droite
                                case dirDroite:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 23 ;
                                    break;
                            /// FIN si va en bas
                                case dirBas:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 24 ;
                                    break;
                            /// FIN si va à gauche
                                case dirGauche:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 24 ;
                                    break;
                                default:
                                    break;
                            }   
                        } break ;    
                        
                        
                        case "R" : {
                            switch (jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel()) {
                                case dirHaut:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 27 ;
                                    break;
                                case dirDroite:
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 26 ;
                                    break;
                                case dirBas:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 27 ;
                                    break;
                                case dirGauche:
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 26 ;
                                    break;
                                default:
                                    break;
                            }
                        } break ;
                        
                        case "L" : {
                            double random = Math.random();
                            if(random < 0.5){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 30 ;
                            }
                            else {
                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                tabCoordonnees[1][nbCoordonnees]= 30 ;
                            }
                        } break ;
                        
                        // Si poussé
                        case "T" : {
                            //if(jesus.isSnobees(new Coordonnees(j, i))){
                                switch (jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel()) {
                                /// FIN si va en haut
                                    case dirHaut:
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 28 ;
                                        break;
                                /// FIN si va à droite
                                    case dirDroite:
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 28 ;
                                        break;
                                /// FIN si va en bas
                                    case dirBas:
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 29 ;
                                        break;
                                /// FIN si va à gauche
                                    case dirGauche:
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 29 ;
                                        break;
                                    default:
                                        break;
                                }   
                            } break ;    
                        //}
                        
                        case "G" : {
                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                            tabCoordonnees[1][nbCoordonnees]= 31 ;
                        } break ;
                        
                        case "S" :{
                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                            tabCoordonnees[1][nbCoordonnees]= 32 ;
                        }break;
                        
                        case "C" :{
                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                            tabCoordonnees[1][nbCoordonnees]= 31 ;
                        } break ;
                        
                        case " " :{
                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                            tabCoordonnees[1][nbCoordonnees]= 32 ;
                        } break ;
                        
                        
                        /// DESTRUCTION BLOC
                        
                        case "7" : {
                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                            tabCoordonnees[1][nbCoordonnees]= 33 ;
                        } break ;
                        case "8" : {
                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                            tabCoordonnees[1][nbCoordonnees]= 33 ;
                        } break ;
                        
                        case "9" : {
                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                            tabCoordonnees[1][nbCoordonnees]= 34 ;
                        } break ;
                        
                    }
                }
                nbCoordonnees++;
            }
        }  
    return tabCoordonnees ;
    } /// Fin set Images
    
    
}

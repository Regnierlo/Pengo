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
                tabCoordonnees[1][j] = 9 ;
            }
        jesus = gameEngine ;
    }
    
    public int[][] setCoordonnees(String[][] s){
        nbCoordonnees = 0 ;
        for(int i = 0 ; i < s.length ; i++){
            for(int j = 0 ; j < s[0].length ; j++){
                if(i==0 || i==15 || j==0 ||j==9){
                    if(s[i][j].equals("W")){
                        if(i==0 && j>0 && j<9){
                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                            tabCoordonnees[1][nbCoordonnees]= 12 ;
                        }
                        else{
                            if(i==15 && j>0 && j<9){
                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                tabCoordonnees[1][nbCoordonnees]= 10 ;
                            }
                            else{
                                if(i>0 && i<15 && j==0){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 13 ;
                                }
                                else{
                                    if(j==9 && i>0 && i<15){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 11 ;
                                    }
                                }
                            }
                        }
                    }
                    else{
                        if(i==0){
                            if(j==0){
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 15 ;    
                            }
                            else {
                                if(j==9){
                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                    tabCoordonnees[1][nbCoordonnees]= 15 ;
                                }
                                else{
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 12 ;
                                }
                            }
                        }
                        else{
                            if(j==0){
                                if(i==15){
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 14 ;
                                }
                                else{ 
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 13 ;
                                }
                            }

                            else {
                                if(i==15){
                                    if(j==9){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 14 ;
                                    }
                                    else{
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 10 ;
                                    }
                                }
                                else{
                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                    tabCoordonnees[1][nbCoordonnees]= 11 ;
                                }
                            }
                        }
                    }   
                }
                else {
                    switch (s[i][j]){
                        case "P" : {
                            if(jesus.getPengo() instanceof P_Pengo){
                                
                                
                                /*if(jesus.getPengo().getPousseDetruire()==true && jesus.getPengo().getFinPousseDetruit()==false){
                                    
                                    if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirHaut)){
                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                        tabCoordonnees[1][nbCoordonnees]= 19 ;
                                    }
                                    else{
                                        if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirBas)){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 22 ;
                                        }
                                        else{
                                            if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirDroite)){
                                                
                                                tabCoordonnees[0][nbCoordonnees]= 1 ;
                                                tabCoordonnees[1][nbCoordonnees]= 20 ;
                                            }
                                            else{
                                                if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirDroite)){
                                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                                    tabCoordonnees[1][nbCoordonnees]= 21 ;
                                                }  
                                            }
                                        }
                                    }
                                }
                                else {*/
                                    /// Si Pengo va en haut
                                    if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirHaut)){
                                        if(jesus.getPengo().getPousseDetruire() && ((jesus.getPengo().getPousseInt()%2)==1)){
                                            System.out.println("ghjgjkgg");
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 19 ;
                                        } 
                                
                                        else{
                                            if(jesus.getPengo().getPousseDetruire() && (jesus.getPengo().getPousseInt()%2==0)){
                                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                                tabCoordonnees[1][nbCoordonnees]= 19 ;
                                            }
                                            else{
                                                if(jesus.getPengo().getAncienneDirection().equals(jesus.getPengo().getDirectionActuel()) && jesus.getPengo().getBoolDirection()==true){
                                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                                    tabCoordonnees[1][nbCoordonnees]= 0 ;
                                                }
                                                else{
                                                    tabCoordonnees[0][nbCoordonnees]= 0 ;
                                                    tabCoordonnees[1][nbCoordonnees]= 0 ;
                                                }  
                                            }
                                             
                                        }
                                        
                                       
                                    } /// FIN si va en haut 

                                    /// Si Pengo va à droite
                                    if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirDroite)){
                                        if(jesus.getPengo().getAncienneDirection().equals(jesus.getPengo().getDirectionActuel())&& jesus.getPengo().getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 1 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 1 ;
                                        }  
                                    } /// FIN si va à droite

                                    /// Si Pengo va en bas
                                    if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirBas)){
                                        if(jesus.getPengo().getAncienneDirection().equals(jesus.getPengo().getDirectionActuel())&& jesus.getPengo().getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 2 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 2 ;
                                        }
                                    } /// FIN si va en bas

                                    /// Si Pengo va à gauche
                                    if(jesus.getPengo().getDirectionActuel().equals(Personnage.Directions.dirGauche)){
                                        if(jesus.getPengo().getAncienneDirection().equals(jesus.getPengo().getDirectionActuel())&& jesus.getPengo().getBoolDirection()==true){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 3 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 3 ;
                                        }
                                    } /// FIN si va à gauche
                                }
                            //}
                        } break ;
                        case "E" : {
                                if(jesus.getSnobee(new Coordonnees(j,i)).getNaissance()<7){
                                    naissance = jesus.getSnobee(new Coordonnees(j,i)).getNaissance() ;
                                    System.out.println(naissance);
                                    if(naissance == 1){
                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                        tabCoordonnees[1][nbCoordonnees]= 16 ; 
                                    }
                                    else{
                                        if(naissance==2){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 16 ;
                                        }
                                        else{
                                            if(naissance==3){
                                                tabCoordonnees[0][nbCoordonnees]= 0 ;
                                                tabCoordonnees[1][nbCoordonnees]= 17 ;
                                            }
                                            else{
                                                if(naissance==4){
                                                    tabCoordonnees[0][nbCoordonnees]= 1 ;
                                                    tabCoordonnees[1][nbCoordonnees]= 17 ;
                                                }
                                                else{
                                                    if(naissance==5){
                                                        tabCoordonnees[0][nbCoordonnees]= 0 ;
                                                        tabCoordonnees[1][nbCoordonnees]= 18 ;
                                                    }
                                                    else{
                                                        tabCoordonnees[0][nbCoordonnees]= 1 ;
                                                        tabCoordonnees[1][nbCoordonnees]= 18 ;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    
                                }
                                else{
                                    /// Si le snobee va en haut
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel().equals(Personnage.Directions.dirHaut)){
                                       
                                        if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel())){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 4 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 4 ;
                                        }
                                    } /// FIN si va en haut 

                                    /// Si le snobee va à droite
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel().equals(Personnage.Directions.dirDroite)){

                                        if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel())){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 5 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 5 ;
                                        }
                                    } /// FIN si va à droite

                                    /// Si le snobee va en bas
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel().equals(Personnage.Directions.dirBas)){
                                        if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel())){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 6 ;
                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 6 ;
                                        }
                                    } /// FIN si va en bas

                                    /// Si le snobee va à gauche
                                    if(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel().equals(Personnage.Directions.dirGauche)){
                                        if(jesus.getSnobee(new Coordonnees(j,i)).getAncienneDirection().equals(jesus.getSnobee(new Coordonnees(j,i)).getDirectionActuel())){
                                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                                            tabCoordonnees[1][nbCoordonnees]= 7 ;

                                        }
                                        else{
                                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                                            tabCoordonnees[1][nbCoordonnees]= 7 ;
                                        }
                                    } /// FIN si va à gauche
                            }
                        } break ;
                        
                        case "G" : {
                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                            tabCoordonnees[1][nbCoordonnees]= 8 ;
                        } break ;
                        
                        case "S" :{
                            tabCoordonnees[0][nbCoordonnees]= 0 ;
                            tabCoordonnees[1][nbCoordonnees]= 9 ;
                        }break;
                        
                        case " " :{
                            tabCoordonnees[0][nbCoordonnees]= 1 ;
                            tabCoordonnees[1][nbCoordonnees]= 9 ;
                        }
                    }
                }
                nbCoordonnees++;
            }
        }  
    return tabCoordonnees ;
    } /// Fin set Images
    
    
}

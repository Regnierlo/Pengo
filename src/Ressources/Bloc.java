package Ressources;

import Personnages.Personnage;

public class Bloc {
    protected boolean enMouvement;
    protected Coordonnees coord;
    protected Personnage.Directions dir;
    
    public Bloc(Coordonnees c){
        coord = c;
        enMouvement = false;
        dir = Personnage.Directions.dirHaut;
    }
    
    public void setCoordonnees(Coordonnees c){
        coord = c;
    }
    
    public Coordonnees getCoordonnees(){
        return this.coord;
    }
    
    public void setEnMouvement(boolean b){
        enMouvement = b;
    }
    
    public boolean getEnMouvement(){
        return this.enMouvement;
    }
    
    public Personnage.Directions getDirection(){
        return dir;
    }
    
    public void setDirection(Personnage.Directions d){
        dir = d;
    }
}

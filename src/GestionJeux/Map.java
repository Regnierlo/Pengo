/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.P_Pengo;
import Personnages.Personnage;
import Personnages.SnoBees;
import Ressources.Bloc;
import Ressources.BlocGlace;
import Ressources.BlocSpecial;
import Ressources.Coordonnees;
import java.util.ArrayList;
import java.util.List;

/**  p.add(new P_Pengo(new Coordonnees(), true));
        }
    }
 *
 * @author loisr
 */
public class Map {
    
    private int niveauActuel;
    private String[][] carteActuelle;
    private int nbPengo;
    private int nbSnoBees;
    private Score score;
    private Niveaux niveau;

    public enum ResultatMouvement{
        deplacementOK("OK"),
        deplacementKO("KO"),
        recontreAvecSnoBeesParalyse("SnoBeesMort"),
        rencontreAvecSnoBees("SnoBeesPleinDeVie"),
        snoBeesRencontreAvecPengo("IlsOntTuePengo");
        
        private final String name;
        
        private ResultatMouvement(String s){
            name = s;
        }
        
        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
           return this.name;
        }
    }
    
    
    public Map(int niv, Niveaux n){
        this.niveauActuel = niv;
        this.niveau = n;
        carteActuelle = niveau.getMap(niv);
        update();
    }
    
    /**
     * Change le niveau
     * 
     * @param i Permet de choisir le niveau voulu
     */
    public void setNiveau(int i){
        niveauActuel = i;
        carteActuelle = niveau.getMap(niveauActuel);
        update();
    }
    
    /**
     * Met à jour le nombre de pengo et de snobees
     */
    public void update(){
        nbPengo = niveau.majPengo(carteActuelle);
        nbSnoBees = niveau.majSnowBees(carteActuelle);
    }
    
    /**
     * 
     * @return le nombre de pengo sur la carte
     */
    public int getNbPengo(){
        return nbPengo;
    }
    
    /**
     * Initialise la liste de bloc (de glace et spéciaux) dans une liste
     * 
     * @return La liste générée contenant les blocs de glace et spéciaux
     */
    public List initBloc(){
        List<Ressources.Bloc> l = new ArrayList<>();
        
        for(int i=0;i<carteActuelle.length;i++){
            for(int j=0;j<carteActuelle[0].length;j++){
                if("BG".equals(carteActuelle[i][j]))
                    l.add(new BlocGlace(new Coordonnees(j, i)));
                if("BS".equals(carteActuelle[i][j]))
                    l.add(new BlocSpecial(new Coordonnees(j, i)));
            }
        }
        
        return l;
    }
    
    /**
     * Initialise la lise de thread (pengo et snobees) dans une liste
     * 
     * @param ge GameEngine nécessaire pour créer les threads
     * @return La liste générée contenant les thread de snobees et pengo
     */
    public List initThread(GameEngine ge){
        List<Personnages.Personnage> l = new ArrayList<>();
        
        for(int i=0;i<carteActuelle.length;i++){
            for(int j=0;j<carteActuelle[0].length;j++){
                if("P".equals(carteActuelle[i][j]))
                    l.add(new P_Pengo(new Coordonnees(j, i), true, ge));
                if("SB".equals(carteActuelle[i][j]))
                    l.add(new SnoBees(new Coordonnees(j, i), false, ge));
            }
        }
        
        return l;
    }
    
    /**
     * Met à jour la carte selon les mouvements effectuées
     * 
     * @version 1.0
     * @param o Instance de l'objet qui effectue le mouvement
     * @param anciennesCoord Les anciennes coordonnées de l'objet
     * @param nouvellesCoord Les nouvelles coordonnées demandées par l'objet
     * @return Un boolean indiquant si le déplacement c'est effectué correctement ou non
     */
    public synchronized boolean move(Object o, Coordonnees anciennesCoord, Coordonnees nouvellesCoord) {
        
        boolean valideMove = false;
        
        if(o instanceof P_Pengo){
            if(" ".equals(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "P";
                valideMove = true;
            }
            else if("SB".equals(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                System.out.println("MOURU");
            }
        }
        else if(o instanceof SnoBees){
            if(" ".equals(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "SB";
                valideMove = true;
            }
            else if("P".equals(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                System.out.println("Ils l'ont eut !!");
            }
        }
        else if(o instanceof BlocGlace){
            if(" ".equals(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "BG";
                valideMove = true;
            }
        }
        else if(o instanceof BlocSpecial){
            if(" ".equals(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "BS";
                valideMove = true;
            }
        }
        
        return valideMove;
    }
    
    /**
     * Détruit le bloc à l'emplacement indiqué.
     * 
     * @param c Coordonnées du bloc à détruire
     * @param g GameEngine nécessaire à la mis à jour de la carte
     */
    public void detruireBloc(Coordonnees c, GameEngine g){
        carteActuelle[c.getY()][c.getX()] = " ";
        g.majAfficheCarte();
    }
    
    /**
     * Pousse le bloc.
     * 
     * @param b Bloc que l'on souhaite pousser
     * @param c Emplacement du bloc à pousser
     * @param dir Direction dans lequel le bloc va se décaler
     * @param g GameEngine nécessaire à la mis à jour de la carte
     */
    public void pousseBloc(Bloc b, Coordonnees c, Personnage.Directions dir, GameEngine g) {
        b.setEnMouvement(true);
        Thread t = new Thread(){
            @Override
            public void run(){
                Coordonnees coord;
                boolean moveOK = true;
                switch(dir){
                    case dirHaut:
                        while(moveOK){
                            coord = new Coordonnees(c.getX(), c.getY()-1);
                            moveOK = move(b, c, coord);
                            c.setY(c.getY()-1);
                            if(moveOK){
                                b.getCoordonnees().setCoordonnees(coord);
                                g.majAfficheCarte();
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                            }
                            //System.out.println(b.getCoordonnees().toString());
                        }
                        break;
                }
            }
        };
        t.start();
    }
    
    /**
     * Vérifie si un bloc se situe à l'emplacement demandé
     * 
     * @param c Coordonnées testées
     * @return 
     */
    public boolean objet(final Coordonnees c) {
        boolean res = false;
        
        if("BG".equals(carteActuelle[c.getY()][c.getX()])
            || "BS".equals(carteActuelle[c.getY()][c.getX()])){
            res = true;
        }
        
        return res;
    }
    
    @Override
    public String toString(){
        String r="";
        
        for (String[] carteActuelle1 : carteActuelle) {
            for (int j = 0; j<carteActuelle[0].length; j++) {
                r += carteActuelle1[j];
            }
            r+="\n";
        }
        
        return r;
    }
}

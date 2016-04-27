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
import Ressources.Mur;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*  p.add(new P_Pengo(new Coordonnees(), true));
        }
    }
*/
public class Map {
    
    private int niveauActuel;
    private String[][] carteActuelle;
    private int nbPengo;
    private int nbSnoBees;
    private Score score;
    private Niveaux niveau;
    private boolean pause;

    public enum elementCarte{
        pengoPousseApres("A"),      // fait
        pengo("P"),                  // fait
        pengoMort2("O"),               // fait
        pengoMort("Q"),             // fait
        pengoPoussePendant("D"),    // fait
        
        snoBees("E"),               // fait
        snobeesMiRambo("I"),        // fait
        snoBeesMiRamboPousse("V"),  // fait
        snoBeesRambo("R"),          //fait
        snoBeesMiRamboParalyse("J"), // fait
        SnoBeesParalyse("K"),       // fait
        SnoBeesRamboParalyse("L"),  // fait
        snoBeesRamboPousse("T"),    // fait
        snoBeesPousse("Y"),         // fait
        blocGlace("G"),             // fait
        blocAvecSnoBees("C"),
        blocSpecial("S"),
        
        
        mur("M"),                    // fait
        murTremble("W"),             // fait
        rien(" "),
        
        naissanceSnoobees1("1"),             // fait
        naissanceSnoobees2("2"),             // fait
        naissanceSnoobees3("3"),             // fait
        naissanceSnoobees4("4"),             // fait
        naissanceSnoobees5("5"),             // fait
        naissanceSnoobees6("6"),             // fait
        destructionBloc1("7"),
        destructionBloc2("8"),
        destructionBloc3("9");
        
        private final String name;
        
        private elementCarte(String s){
            name = s;
        }
        
        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
           return this.name;
        }
    }
    
    
    public Map(int niv){
        pause = false;
        this.niveauActuel = niv;
        this.niveau = new Niveaux();
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
    
    public int getNiveau(){
        return niveauActuel;
    }
    
    public String[][] getCarte(){
        return carteActuelle;
    }
    
    /**
     * Met à jour le nombre de pengo et de snobees
     */
    public void update(){
        nbPengo = niveau.majPengo(carteActuelle);
        nbSnoBees = niveau.majSnobees(carteActuelle);
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
                if(elementCarte.blocGlace.equalsName(carteActuelle[i][j]))
                    l.add(new BlocGlace(new Coordonnees(j, i), false));
                if(elementCarte.blocSpecial.equalsName(carteActuelle[i][j]))
                    l.add(new BlocSpecial(new Coordonnees(j, i)));
                if(elementCarte.blocAvecSnoBees.equalsName((carteActuelle[i][j]))){
                    l.add(new BlocGlace(new Coordonnees(j, i), true));
                    carteActuelle[i][j] = elementCarte.blocGlace.toString();
                }
            }
        }
        
        return l;
    }
    
    public List initMur(){
        List<Ressources.Mur> m = new ArrayList<>() ;
        
        for(int i = 0 ; i < carteActuelle.length ; i++){
            for(int j = 0 ; j < carteActuelle[0].length ; j++){
                if(elementCarte.mur.equalsName(carteActuelle[i][j]))
                    m.add(new Mur(new Coordonnees(j,i)));
            }
        }
        return m ;
    }
    
    public void changeCarte(elementCarte e, Coordonnees c){
        carteActuelle[c.getY()][c.getX()] = e.toString();
    }
    
    /**
     * Initialise la liste de thread (pengo et snobees) dans une liste
     * 
     * @param ge GameEngine nécessaire pour créer les threads
     * @return La liste générée contenant les thread de snobees et pengo
     */
    public List initThread(GameEngine ge){
        List<Personnages.Personnage> l = new ArrayList<>();
        
        for(int i=0;i<carteActuelle.length;i++){
            for(int j=0;j<carteActuelle[0].length;j++){
                if(elementCarte.pengo.equalsName(carteActuelle[i][j]))
                    l.add(new P_Pengo(new Coordonnees(j, i), true, ge));
                if(elementCarte.snoBees.equalsName(carteActuelle[i][j]))
                    l.add(new SnoBees(new Coordonnees(j, i), false, ge, false, SnoBees.typeSnobees.normal));
                if(elementCarte.snobeesMiRambo.equalsName(carteActuelle[i][j]))
                    l.add((new SnoBees(new Coordonnees(j, i), false, ge, false, SnoBees.typeSnobees.miRamboMiIdiot)));
                if(elementCarte.snoBeesRambo.equalsName(carteActuelle[i][j]))
                    l.add(new SnoBees(new Coordonnees(j, i), false, ge, false, SnoBees.typeSnobees.rambo));
                if(elementCarte.blocAvecSnoBees.equalsName(carteActuelle[i][j]))
                    l.add(new SnoBees(new Coordonnees(j, i), false, ge, true, SnoBees.typeSnobees.normal));
            }
        }
        
        return l;
    }
    
    /**
     * Met à jour la carte selon les mouvements effectuées
     * 
     * @version 1.0
     * @param o Instance de l'isBlockOrIsMur qui effectue le mouvement
     * @param anciennesCoord Les anciennes coordonnées de l'isBlockOrIsMur
     * @param nouvellesCoord Les nouvelles coordonnées demandées par l'isBlockOrIsMur
     * @param g GameEngine utilisé pour les évènements
     * @return Un boolean indiquant si le déplacement c'est effectué correctement ou non
     */
    public synchronized boolean move(Object o, Coordonnees anciennesCoord, Coordonnees nouvellesCoord, GameEngine g) {
        boolean valideMove = false;
        //Au cas ou l'objet se fait détruire entre temps
        if(o != null){
            if(o instanceof P_Pengo){
                System.out.print("Pengo ");
                System.out.print("Anciennes coord : " + anciennesCoord);
                System.out.println("\tNouvelles coord :" + nouvellesCoord);

                if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.pengo.toString();
                    valideMove = true;
                }
                else if(elementCarte.SnoBeesParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesMiRamboParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.SnoBeesRamboParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    g.snobeeMort(g.getSnobee(nouvellesCoord));
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.pengo.toString();
                    valideMove = true;
                }
                else if(elementCarte.snoBees.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snobeesMiRambo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesRambo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    g.pengoIsDead();
                }
            }
            else if(o instanceof SnoBees){
                    System.out.print("SNobee ");
                    System.out.print("Anciennes coord : " + anciennesCoord);
                    System.out.println("\tNouvelles coord :" + nouvellesCoord);
                    SnoBees sb = (SnoBees)o;
                    if(sb.getVaMourirParBloc()){
                        System.out.println("va mourir par bloc");

                        if(null != sb.getComportement())
                            switch (sb.getComportement()) {
                                case normal:
                                    if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                        //carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.blocGlace.toString();
                                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.snoBeesPousse.toString();
                                        valideMove=true;
                                    }   break;
                                case miRamboMiIdiot:
                                    if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                      //  carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.snoBeesMiRamboPousse.toString();
                                        valideMove = true;
                                    }   break;
                                case rambo:
                                    if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                     //   carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.snoBeesRamboPousse.toString();
                                        valideMove=true;
                                    }   break;
                                default:
                                    break;
                            }
                    }
                    else if(null != sb.getComportement())
                        switch (sb.getComportement()) {
                            case normal:
                                if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.snoBees.toString();
                                    valideMove = true;
                                }
                                else if(elementCarte.pengo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                                        || elementCarte.pengoPousseApres.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                                        || elementCarte.pengoPoussePendant.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                    g.pengoIsDead();
                                }   break;
                            case miRamboMiIdiot:
                                if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.snobeesMiRambo.toString();
                                    valideMove = true;
                                }
                                else if(elementCarte.pengo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                                        || elementCarte.pengoPousseApres.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                                        || elementCarte.pengoPoussePendant.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                    g.pengoIsDead();
                                }   break;
                            case rambo:
                                if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.snoBeesRambo.toString();
                                    valideMove = true;
                                }
                                else if(elementCarte.pengo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                                        || elementCarte.pengoPousseApres.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                                        || elementCarte.pengoPoussePendant.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                                    g.pengoIsDead();
                                }   break;
                            default:
                                break;
                    }

            }
            else if(o instanceof BlocGlace){
                BlocGlace bg = (BlocGlace) o;
                System.out.print("BG ");
                System.out.print("Anciennes coord : " + anciennesCoord);
                System.out.println("\tNouvelles coord :" + nouvellesCoord);
                if(!bg.NaissanceSnobeeDestructionBloc()){

                    if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                        carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocGlace.toString();
                        valideMove = true;
                    }
                    else if(elementCarte.snoBees.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                            || elementCarte.SnoBeesParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                            || elementCarte.snoBeesPousse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                        //System.out.println("bloc pousse un snobee");
                        if(nouvellesCoord.getY()==1)
                            g.snobeePousseParBloc(anciennesCoord, bg.getDirection());
                        else
                            g.snobeePousseParBloc(nouvellesCoord, bg.getDirection());
                        valideMove = true;
                        carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();

                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocGlace.toString();
                    }
                    else if(elementCarte.snobeesMiRambo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesMiRamboParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesMiRamboPousse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                        g.snobeePousseParBloc(nouvellesCoord, bg.getDirection());
                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocGlace.toString();
                        carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                        valideMove = true;
                    }
                    else if(elementCarte.snoBeesRambo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                            || elementCarte.SnoBeesRamboParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                            || elementCarte.snoBeesRamboPousse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                        g.snobeePousseParBloc(nouvellesCoord, bg.getDirection());
                        carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocGlace.toString();
                        carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                        valideMove = true;
                    }
                }
                else{
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                }
            }
            else if(o instanceof BlocSpecial){
                System.out.print("Bloc spécial ");
                System.out.print("Anciennes coord : " + anciennesCoord);
                System.out.println("\tNouvelles coord :" + nouvellesCoord);
                if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocSpecial.toString();
                    valideMove = true;
                }
                else if(elementCarte.snoBees.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.SnoBeesParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesPousse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    BlocSpecial bs = (BlocSpecial)o;
                    g.snobeePousseParBloc(nouvellesCoord, bs.getDirection());
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocSpecial.toString();
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                    valideMove = true;
                }
                else if(elementCarte.snobeesMiRambo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesMiRamboParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesMiRamboPousse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    BlocSpecial bs = (BlocSpecial)o;
                    g.snobeePousseParBloc(nouvellesCoord, bs.getDirection());
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocSpecial.toString();
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                    valideMove = true;
                }
                else if(elementCarte.snoBeesRambo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.SnoBeesRamboParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])
                        || elementCarte.snoBeesRamboPousse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    BlocSpecial bs = (BlocSpecial)o;
                    g.snobeePousseParBloc(nouvellesCoord, bs.getDirection());
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = elementCarte.blocSpecial.toString();
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = elementCarte.rien.toString();
                    valideMove = true;
                }
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
        if(!(elementCarte.snoBees.equalsName(carteActuelle[c.getY()][c.getX()]))){
            try {
                carteActuelle[c.getY()][c.getX()] = elementCarte.destructionBloc1.toString();
                g.majAfficheCarte();
                Thread.sleep(300);
                carteActuelle[c.getY()][c.getX()] = elementCarte.destructionBloc2.toString();
                g.majAfficheCarte();
                Thread.sleep(300);
                carteActuelle[c.getY()][c.getX()] = elementCarte.destructionBloc3.toString();
                g.majAfficheCarte();
                Thread.sleep(300);
                carteActuelle[c.getY()][c.getX()] = elementCarte.rien.toString();
            } catch (InterruptedException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        g.majAfficheCarte();
    }
    
    /**
     * Fait devenir un snobees paralysé ou le deparalyse
     * 
     * @param c
     * @param e
     * @param t 
     */
    public void changeSnoBees(Coordonnees c, elementCarte e, SnoBees.typeSnobees t){
        switch(t){
            case normal:
                if(e.equals(elementCarte.SnoBeesParalyse))
                    carteActuelle[c.getY()][c.getX()] = elementCarte.SnoBeesParalyse.toString();
                else if(e.equals(elementCarte.snoBees))
                    carteActuelle[c.getY()][c.getX()] = elementCarte.snoBees.toString();
                break;
            case miRamboMiIdiot:
                if(e.equals(elementCarte.snoBeesMiRamboParalyse))
                    carteActuelle[c.getY()][c.getX()] = elementCarte.snoBeesMiRamboParalyse.toString();
                else if(e.equals(elementCarte.snobeesMiRambo))
                    carteActuelle[c.getY()][c.getX()] = elementCarte.snobeesMiRambo.toString();
                break;
            case rambo:
                if(e.equals(elementCarte.SnoBeesRamboParalyse))
                    carteActuelle[c.getY()][c.getX()] = elementCarte.SnoBeesRamboParalyse.toString();
                else if(e.equals(elementCarte.snoBeesRambo))
                    carteActuelle[c.getY()][c.getX()] = elementCarte.snoBeesRambo.toString();
                break;
        }
    }
    
    public void faireTremblerMur(Personnage.Directions dir, GameEngine g){
        Thread t = new Thread(){
            @Override
            public void run(){
                int tpsAttente = 800;
                
                switch(dir){
                    case dirHaut:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[0][j] = elementCarte.murTremble.toString();
                                g.murTremble(new Coordonnees(0,j),true);
                                g.snobeeParalyse(new Coordonnees(1, j), elementCarte.SnoBeesParalyse);
                            }
                            g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[0][j] = elementCarte.mur.toString();
                                g.murTremble(new Coordonnees(0,j),false);
                            }
                            g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle[0].length;j++){
                            g.snobeeParalyse(new Coordonnees(1, j), elementCarte.snoBees);
                        }
                        g.majAfficheCarte();
                        break;
                    case dirBas:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[15][j] = elementCarte.murTremble.toString();
                                g.murTremble(new Coordonnees(15,j),true);
                                g.snobeeParalyse(new Coordonnees(14, j), elementCarte.SnoBeesParalyse);
                            }
                            g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[15][j] = elementCarte.mur.toString();
                                g.murTremble(new Coordonnees(15,j),false);
                            }
                            g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle[0].length;j++){
                            g.snobeeParalyse(new Coordonnees(14, j), elementCarte.snoBees);
                        }
                        g.majAfficheCarte();
                        break;
                    case dirDroite:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle.length;j++){
                                System.out.println(j);
                                carteActuelle[j][9] = elementCarte.murTremble.toString();
                                g.murTremble(new Coordonnees(j,9),true);
                                g.snobeeParalyse(new Coordonnees(j, 8), elementCarte.SnoBeesParalyse);
                            }
                            g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle.length;j++){
                                carteActuelle[j][9] = elementCarte.mur.toString();
                                g.murTremble(new Coordonnees(j,9),false);
                            }
                            g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle.length;j++){
                            g.snobeeParalyse(new Coordonnees(j, 8), elementCarte.snoBees);
                        }
                        g.majAfficheCarte();
                        break;
                    case dirGauche:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle.length;j++){
                                carteActuelle[j][0] = elementCarte.murTremble.toString();
                                g.murTremble(new Coordonnees(j,0),true);
                                g.snobeeParalyse(new Coordonnees(j, 1), elementCarte.SnoBeesParalyse);
                            }
                            g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle.length;j++){
                                carteActuelle[j][0] = elementCarte.mur.toString();
                                g.murTremble(new Coordonnees(j,0),false);
                            }
                            g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle.length;j++){
                            g.snobeeParalyse(new Coordonnees(j, 1), elementCarte.snoBees);
                        }
                        g.majAfficheCarte();
                        break;
                }
            }
        };
        t.start();
    }
    public boolean isBlocDestructible(Coordonnees c){
        return elementCarte.blocGlace.equalsName(carteActuelle[c.getY()][c.getX()]);
    }
    
    public boolean isMur(Coordonnees c, Personnage.Directions dir){
        boolean res;
        
        res = elementCarte.mur.equalsName(carteActuelle[c.getY()][c.getX()]);
        if(!res)
            res = elementCarte.murTremble.equalsName(carteActuelle[c.getY()][c.getX()]);
        
        
        return res;
    }
    
    public void transformBlocGlaceEnSnoBees(Coordonnees c){
        carteActuelle[c.getY()][c.getX()] = elementCarte.naissanceSnoobees1.toString();
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
                int tpsAttente = 500;
                switch(dir){
                    case dirHaut:
                        do{
                            b.setDirection(Personnage.Directions.dirHaut);
                            coord = new Coordonnees(c.getX(), c.getY()-1);
                            
                            moveOK = move(b, c, coord,g);
                            if(moveOK){
                                if(b instanceof BlocGlace){
                                    BlocGlace bg = (BlocGlace)b;
                                    if(bg.getContientSnobees()){
                                        SnoBees sb = g.getSnobee(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                               // b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                g.majAfficheCarte();
                                try {
                                    Thread.sleep(tpsAttente);
                                } catch (Exception e) {
                                }
                                c.setY(coord.getY());
                                if(c.getY()==1)
                                    moveOK=false;
                            }
                            else{
                                b.setEnMouvement(false);
                            }
                        }while(moveOK);
                        b.setEnMouvement(false);
                        System.out.println("mouvement arrete");
                        break;
                    case dirBas:
                        while(moveOK){
                            b.setDirection(Personnage.Directions.dirBas);
                            coord = new Coordonnees(c.getX(), c.getY()+1);
                            moveOK = move(b, c, coord,g);
                            c.setY(c.getY()+1);
                            if(moveOK){
                                if(b instanceof BlocGlace){
                                    BlocGlace bg = (BlocGlace)b;
                                    if(bg.getContientSnobees()){
                                        SnoBees sb = g.getSnobee(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                g.majAfficheCarte();
                                try {
                                    Thread.sleep(tpsAttente);
                                } catch (Exception e) {
                                }
                            }
                            else{
                                b.setEnMouvement(false);
                            }
                        }
                        b.setEnMouvement(false);
                        break;
                    case dirDroite:
                        while(moveOK){
                            b.setDirection(Personnage.Directions.dirDroite);
                            coord = new Coordonnees(c.getX()+1, c.getY());
                            moveOK = move(b, c, coord,g);
                            c.setX(c.getX()+1);
                            if(moveOK){
                                if(b instanceof BlocGlace){
                                    BlocGlace bg = (BlocGlace)b;
                                    if(bg.getContientSnobees()){
                                        SnoBees sb = g.getSnobee(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                g.majAfficheCarte();
                                try {
                                    Thread.sleep(tpsAttente);
                                } catch (Exception e) {
                                }
                            }
                            else{
                                b.setEnMouvement(false);
                            }
                        }
                        break;
                    case dirGauche:
                        while(moveOK){
                            b.setDirection(Personnage.Directions.dirGauche);
                            coord = new Coordonnees(c.getX()-1, c.getY());
                            moveOK = move(b, c, coord,g);
                            c.setX(c.getX()-1);
                            if(moveOK){
                                if(b instanceof BlocGlace){
                                    BlocGlace bg = (BlocGlace)b;
                                    if(bg.getContientSnobees()){
                                        SnoBees sb = g.getSnobee(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                g.majAfficheCarte();
                                try {
                                    Thread.sleep(tpsAttente);
                                } catch (Exception e) {
                                }
                            }
                            else{
                                b.setEnMouvement(false);
                            }
                        }
                        break;
                    default:
                        break;
                }
                if(b instanceof BlocSpecial){
                    if(blocsSpeciauxAlignes()){
                        g.blocsSpeciauxAlignes();
                    }
                }
            }
        };
        t.start();
       
    }
    
    private boolean blocsSpeciauxAlignes(){
        boolean res = false;
        
        for (int i=0;i<carteActuelle[0].length;i++) {
            for (int j = 0; j<carteActuelle.length; j++) {
                //Verification horizontal à partir du premier bloc spécial trouvé
                if(elementCarte.blocSpecial.equalsName(carteActuelle[j][i])){
                    if((i+2)<carteActuelle[0].length){
                        if(elementCarte.blocSpecial.equalsName(carteActuelle[j][i+1])){
                            if(elementCarte.blocSpecial.equalsName(carteActuelle[j][i+2])){
                                res = true;
                            }
                        }
                    }
                    if((j+2)<carteActuelle.length){
                        if(elementCarte.blocSpecial.equalsName(carteActuelle[j+1][i])){
                            if(elementCarte.blocSpecial.equalsName(carteActuelle[j+2][i])){
                                res = true;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
    
    public boolean verifBlocContreMur(){
        boolean res = false;
        
        for (int i=0;i<carteActuelle[0].length;i++) {
            for (int j = 0; j<carteActuelle.length; j++) {
                //Verification horizontal à partir du premier bloc spécial trouvé
                if(elementCarte.blocSpecial.equalsName(carteActuelle[j][i])){
                    if((i+2)<carteActuelle[0].length){
                        if(elementCarte.blocSpecial.equalsName(carteActuelle[j][i+1])){
                            if(elementCarte.blocSpecial.equalsName(carteActuelle[j][i+2])){
                                if(elementCarte.mur.equalsName(carteActuelle[j][i+3])
                                    || elementCarte.mur.equalsName(carteActuelle[j][i-1])
                                    || elementCarte.mur.equalsName(carteActuelle[j+1][i])
                                    || elementCarte.mur.equalsName(carteActuelle[j-1][i])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j][i+3])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j][i-1])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j+1][i])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j-1][i])){
                                    res = true;
                                }
                            }
                        }
                    }
                    if((j+2)<carteActuelle.length){
                        if(elementCarte.blocSpecial.equalsName(carteActuelle[j+1][i])){
                            if(elementCarte.blocSpecial.equalsName(carteActuelle[j+2][i])){
                                if(elementCarte.mur.equalsName(carteActuelle[j+3][i])
                                    || elementCarte.mur.equalsName(carteActuelle[j-1][i])
                                    || elementCarte.mur.equalsName(carteActuelle[j][i+1])
                                    || elementCarte.mur.equalsName(carteActuelle[j][i-1])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j+3][i])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j-1][i])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j][i+1])
                                    || elementCarte.murTremble.equalsName(carteActuelle[j][i-1])){
                                    res = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return res;
    }
    
    /**
     * Vérifie si un bloc se situe à l'emplacement demandé
     * 
     * @param c Coordonnées testées
     * @return 
     */
    public boolean isBlockOrIsMur(final Coordonnees c) {
        boolean res = false;
        
        if(elementCarte.blocGlace.equalsName(carteActuelle[c.getY()][c.getX()])
            || elementCarte.blocSpecial.equalsName(carteActuelle[c.getY()][c.getX()])
            || elementCarte.mur.equalsName(carteActuelle[c.getY()][c.getX()])
            || elementCarte.murTremble.equalsName(carteActuelle[c.getY()][c.getX()])){
            
            res = true;
        }
        
        return res;
    }
    
    public void pause(){
        pause = true;
    }
    
    public void reprise(){
        pause = false;
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

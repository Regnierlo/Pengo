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
    private boolean pause;

    public enum elementCarte{
        pengo("P"),
        pengoPousse("D"),
        snoBees("E"),
        SnoBeesParalyse("3"),
        blocGlace("G"),
        blocAvecSnoBees("C"),
        blocSpecial("S"),
        mur("M"),
        murTremble("W"),
        rien(" ");
        
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
    
    
    public Map(int niv, Niveaux n){
        pause = false;
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
                    l.add(new SnoBees(new Coordonnees(j, i), false, ge, false));
                if(elementCarte.blocAvecSnoBees.equalsName(carteActuelle[i][j]))
                    l.add(new SnoBees(new Coordonnees(j, i), false, ge, true));
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
     * @param g GameEngine utilisé pour les évènements
     * @return Un boolean indiquant si le déplacement c'est effectué correctement ou non
     */
    public synchronized boolean move(Object o, Coordonnees anciennesCoord, Coordonnees nouvellesCoord, GameEngine g) {
        
        boolean valideMove = false;
        
        if(o instanceof P_Pengo){
            if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "P";
                valideMove = true;
            }
            else if(elementCarte.SnoBeesParalyse.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                g.snoBeesMort(nouvellesCoord);
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "P";
                valideMove = true;
            }
            else if(elementCarte.snoBees.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                g.pengoIsDead();
            }
        }
        else if(o instanceof SnoBees){
            if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "E";
                valideMove = true;
            }
            else if(elementCarte.pengo.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                System.out.println("Ils l'ont eut !!");
            }
        }
        else if(o instanceof BlocGlace){
            BlocGlace bg = (BlocGlace) o;
            if(!bg.getUnSnoBeesVaNaitre()){
                if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "G";
                    valideMove = true;
                }
                else if(elementCarte.snoBees.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                    g.snobeesPousserParBloc(nouvellesCoord, bg.getDirection());
                    carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "G";
                    carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                    valideMove = true;
                }
            }
            else{
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
            }
        }
        else if(o instanceof BlocSpecial){
            if(elementCarte.rien.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "S";
                valideMove = true;
            }
            else if(elementCarte.snoBees.equalsName(carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()])){
                BlocSpecial bs = (BlocSpecial)o;
                g.snobeesPousserParBloc(nouvellesCoord, bs.getDirection());
                carteActuelle[nouvellesCoord.getY()][nouvellesCoord.getX()] = "S";
                carteActuelle[anciennesCoord.getY()][anciennesCoord.getX()] = " ";
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
        if(!(elementCarte.snoBees.equalsName(carteActuelle[c.getY()][c.getX()]))){
            carteActuelle[c.getY()][c.getX()] = Map.elementCarte.rien.toString();
        }
        //g.majAfficheCarte();
    }
    
    public void changeSnoBees(Coordonnees c, elementCarte e){
        if(e.equals(elementCarte.SnoBeesParalyse))
            carteActuelle[c.getY()][c.getX()] = elementCarte.SnoBeesParalyse.toString();
        else if(e.equals(elementCarte.snoBees))
            carteActuelle[c.getY()][c.getX()] = elementCarte.snoBees.toString();
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
                                carteActuelle[0][j] = "W";
                                g.murTremble(new Coordonnees(0,j),true);
                                g.snobeesParalyse(new Coordonnees(1, j), elementCarte.SnoBeesParalyse);
                            }
                            //g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[0][j] = "M";
                                g.murTremble(new Coordonnees(0,j),false);
                            }
                            //g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle[0].length;j++){
                            g.snobeesParalyse(new Coordonnees(1, j), elementCarte.snoBees);
                        }
                        //g.majAfficheCarte();
                        break;
                    case dirBas:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[15][j] = "W";
                                g.murTremble(new Coordonnees(15,j),true);
                                g.snobeesParalyse(new Coordonnees(14, j), elementCarte.SnoBeesParalyse);
                            }
                            //g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle[0].length;j++){
                                carteActuelle[15][j] = "M";
                                g.murTremble(new Coordonnees(15,j),false);
                            }
                            //g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle[0].length;j++){
                            g.snobeesParalyse(new Coordonnees(14, j), elementCarte.snoBees);
                        }
                        //g.majAfficheCarte();
                        break;
                    case dirDroite:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle.length;j++){
                                System.out.println(j);
                                carteActuelle[j][9] = "W";
                                g.murTremble(new Coordonnees(j,9),true);
                                g.snobeesParalyse(new Coordonnees(j, 8), elementCarte.SnoBeesParalyse);
                            }
                            //g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle.length;j++){
                                carteActuelle[j][9] = "M";
                                g.murTremble(new Coordonnees(j,9),false);
                            }
                            //g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle.length;j++){
                            g.snobeesParalyse(new Coordonnees(j, 8), elementCarte.snoBees);
                        }
                        //g.majAfficheCarte();
                        break;
                    case dirGauche:
                        for(int i=0;i<3;i++){
                            for(int j=0;j<carteActuelle.length;j++){
                                carteActuelle[j][0] = "W";
                                g.murTremble(new Coordonnees(j,0),true);
                                g.snobeesParalyse(new Coordonnees(j, 1), elementCarte.SnoBeesParalyse);
                            }
                            //g.majAfficheCarte();
                            try {
                                Thread.sleep(tpsAttente);
                            } catch (Exception e) {
                            }
                            for(int j=0;j<carteActuelle.length;j++){
                                carteActuelle[j][0] = "M";
                                g.murTremble(new Coordonnees(j,0),false);
                            }
                            //g.majAfficheCarte();
                        }
                        for(int j=0;j<carteActuelle.length;j++){
                            g.snobeesParalyse(new Coordonnees(j, 1), elementCarte.snoBees);
                        }
                        //g.majAfficheCarte();
                        break;
                }
            }
        };
        t.start();
    }
    
    public boolean isMur(Coordonnees c, Personnage.Directions dir){
        boolean res;
        
        res = elementCarte.mur.equalsName(carteActuelle[c.getY()][c.getX()]);
        if(!res)
            res = elementCarte.murTremble.equalsName(carteActuelle[c.getY()][c.getX()]);
        
        
        return res;
    }
    
    public void transformBlocGlaceEnSnoBees(Coordonnees c){
        carteActuelle[c.getY()][c.getX()] = elementCarte.snoBees.toString();
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
                        while(moveOK){
                            b.setDirection(Personnage.Directions.dirHaut);
                            coord = new Coordonnees(c.getX(), c.getY()-1);
                            moveOK = move(b, c, coord,g);
                            c.setY(c.getY()-1);
                            if(moveOK){
                                if(b instanceof BlocGlace){
                                    BlocGlace bg = (BlocGlace)b;
                                    if(bg.getContientSnobees()){
                                        SnoBees sb = g.getSnobees(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                //g.majAfficheCarte();
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
                                        SnoBees sb = g.getSnobees(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                //g.majAfficheCarte();
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
                                        SnoBees sb = g.getSnobees(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                //g.majAfficheCarte();
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
                                        SnoBees sb = g.getSnobees(b.getCoordonnees());
                                        if(sb != null)
                                            sb.getCoordonnees().setCoordonnees(coord);
                                    }
                                }
                                b.setEnMouvement(true);
                                b.getCoordonnees().setCoordonnees(coord);
                                //g.majAfficheCarte();
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
                    if(blocSpeAlligne()){
                        g.blocsSpeciauxAligne();
                    }
                }
            }
        };
        t.start();
    }
    
    private boolean blocSpeAlligne(){
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
    public boolean objet(final Coordonnees c) {
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

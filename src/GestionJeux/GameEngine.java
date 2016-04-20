/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.*;
import Ressources.*;
import Vue.Carte;
import Vue.Fenetre;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author loisr
 */
public class GameEngine {
    
    // Déclaration des variables
    private List<Personnage> p;
    private List<Bloc> b;
    private List<Mur> mur ;
    private final Niveaux n;
    private final Map m;
    private final Score s;
    private int nbSnoBeesActif;
    private int nbSnoBeesCache;
    private boolean bonusBlocsSpeciaux;
    private long chronometre;
    private String name;
    private Fenetre fenetre_principale ;
    
    private boolean niveauFini;
    
    public GameEngine(){
        //Instanciation des variables
        nbSnoBeesActif=0;
        nbSnoBeesCache=0;
        p = new ArrayList<>();
        b = new ArrayList<>();
        mur = new ArrayList<>() ;
        n = new Niveaux();
        m = new Map(-1, n);
        s = new Score();
        chronometre = 0;
        KeyListener[] kl = getKeyListener();
        fenetre_principale = new Fenetre(this,kl) ;
        
        //Initialisation de la carte
        
        init() ;
    }
    
    private KeyListener[] getKeyListener(){
        int nbJoueur = 0 ;
        for(int i = 0 ; i < p.size() ; i++){
            if(p.get(i).getJoueur())
                nbJoueur++ ;
        }
        KeyListener[] georges = new KeyListener[nbJoueur];
        for(int i = 0 ; i < p.size() ; i++){
            if(p.get(i).getJoueur()){
                georges[nbJoueur] = p.get(i) ;
                nbJoueur-- ;
            }
        }
        return georges ;
    }
    
    public void afficheInfo(){
        System.out.println("--------------------- INFORMATIONS CARTE ---------------------");
        System.out.println("Niveau : "+m.getNiveau());
        System.out.println("Temps(millisec) : "+((java.lang.System.currentTimeMillis())-chronometre));
        System.out.println("Temps(sec) : "+Integer.toString(tempsSec())+" sec");
        System.out.println("Temps(min/sec) : "+temps());
        System.out.println("Nombre de thread : "+p.size());
        System.out.println("Nombre de Pengo : "+m.getNbPengo());
        System.out.println("Nombre de SnoBees : "+(p.size()-m.getNbPengo()));
        System.out.println("Nombre de SnoBees cachés : "+nbSnoBeesCache);
        System.out.println("Nombre de SnoBees actif : "+nbSnoBeesActif);
        System.out.println("Nombre de Bloc : "+b.size());
        System.out.println("Bloc Speciaux alligné : "+bonusBlocsSpeciaux);
        System.out.println("--------------------- INFORMATIONS SCORE ---------------------");
        System.out.println("Score : "+Integer.toString(s.getScore()));
        System.out.println("");
    }
    
    private int tempsSec(){
        long r_long = ((java.lang.System.currentTimeMillis())-chronometre);
        int sec;
        
        sec = (int)r_long/(1000);
        
        return sec;
    }
    
    private String temps(){
        long r_long = ((java.lang.System.currentTimeMillis())-chronometre);
        int min;
        int sec;
        String r;
        
        sec = (int)r_long/(1000);
        min = (sec/60);
        sec = sec%60;
        
        r = Integer.toString(min)+"'"+Integer.toString(sec);
        
        return r;
    }
    
    /**
     * Permet d'initialiser le niveau (création des threads de Pengo et de SnoBees et des différents blocs).
     */
    private void init(){
        /*try (Scanner scan = new Scanner(System.in)) {
            name = scan.nextLine();
        }*/
        p = m.initThread(this);
        b = m.initBloc();
        mur = m.initMur() ;
        niveauFini = false;
        bonusBlocsSpeciaux = false;
        
        for(int i=0;i<p.size();i++){
            if(p.get(i) instanceof SnoBees){
                SnoBees sb = (SnoBees)p.get(i);
                if(!sb.getCacheDansBloc()){
                    sb.start();
                    nbSnoBeesActif++;
                }
                else{
                    nbSnoBeesCache++;
                }
            }
            else
                p.get(i).start();
        }
        chronometre = java.lang.System.currentTimeMillis();

        for (int i=0;i<p.size();i++)
            if(p.get(i).getJoueur())
                fenetre_principale.addKeyListener(p.get(i));
           
        
        ///////////////
        majAfficheCarte();
    }
    
    private void finNiveau(){
        for(int i=0;i<p.size();i++){
            p.get(i).arreter();
            if(p.get(i).getJoueur())
                fenetre_principale.removeKeyListener(p.get(i));
        }
        s.pointFinNiveau(tempsSec(), name);
        System.out.println("\n\n\n\n\n\n\n\n\n\nVous avez vaincus "+name+" !");
        System.out.println("Fini en : "+temps());
        System.out.println("Avec un score total de : "+s.getScore());
    }
    
    public void snobeesPousserParBloc(Coordonnees c, Personnage.Directions dir) {
        Coordonnees cn;// = new Coordonnees(c.getX()-1, c.getY());
        
        switch(dir){
            case dirHaut:
                cn = new Coordonnees(c.getX(), c.getY()-1);
                break;
            case dirBas:
                cn = new Coordonnees(c.getX(), c.getY()+1);
                break;
            case dirDroite:
                cn = new Coordonnees(c.getX()+1, c.getY());
                break;
            case dirGauche:
                cn = new Coordonnees(c.getX()-1, c.getY());
                break;
            default:
                cn = new Coordonnees(c.getX(), c.getY());
        }
        
        int i_sno =0;
        for(int i=0;i<p.size();i++){
            if(p.get(i).getCoordonnees().comp(c)){
                i_sno = i;
            }
        }
        
        p.get(i_sno).stopper();
        SnoBees sb = (SnoBees) p.get(i_sno);
        if(m.move(p.get(i_sno), c, new Coordonnees(cn.getX(), cn.getY()), this)){
            p.get(i_sno).getCoordonnees().setCoordonnees(cn);
            sb.setVaMourirParBloc(true);
        }
        else{
            snoBeesMort(p.get(i_sno).getCoordonnees());
            p.remove(i_sno);
            snobeesEcrase();
        }
    }
    
    private void gameOver(){
        for(int i=0;i<p.size();i++){
            p.get(i).stopper();
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nGAME OVER");
    }
    
    public void blocsSpeciauxAligne(){
        if(!bonusBlocsSpeciaux){
            if(m.verifBlocContreMur())
                s.pointBlocSpeciaux(true);
            else
                s.pointBlocSpeciaux(false);
            bonusBlocsSpeciaux = true;
        }
    }
    
    public void snobeesEcrase(){
        s.pointSnobeesEcrase();
        checkFinJeu();
    }
    
    private void checkFinJeu(){
        if((nbSnoBeesActif+nbSnoBeesCache) <= 0){
            niveauFini=true;
            finNiveau();
        }
    }
    
    public void snoBeesMort(Coordonnees c){
        for(int i=0;i<p.size();i++){
            if(p.get(i) instanceof SnoBees){
                SnoBees sn = (SnoBees)p.get(i);
                if(p.get(i).getCoordonnees().comp(c)){
                    p.get(i).arreter();
                    if(sn.getCacheDansBloc()){
                        nbSnoBeesCache--;
                        p.remove(i);
                    }
                    else if(sn.getParlyse()){
                        p.remove(i);
                    }
                    else if(!sn.getCacheDansBloc()){
                        nbSnoBeesActif--;
                        if(nbSnoBeesCache>0){
                            naissanceSnobees();
                            nbSnoBeesCache--;
                            nbSnoBeesActif++;
                        }
                    }
                    checkFinJeu();
                }
            }
        }
    }
    
    private void naissanceSnobees(){
        boolean uneNaissance=false;
        for(int i=0;i<b.size();i++){
            if(!uneNaissance){
                if(b.get(i) instanceof BlocGlace){
                    BlocGlace bg = (BlocGlace)b.get(i);
                    if(bg.getContientSnobees()){
                        m.transformBlocGlaceEnSnoBees(bg.getCoordonnees());
                        for(int j=0;j<p.size();j++){
                            if(p.get(j) instanceof SnoBees){
                                SnoBees sb = (SnoBees)p.get(j);
                                if(sb.getCoordonnees().comp(bg.getCoordonnees())){
                                    uneNaissance=true;
                                    sb.setCacheDansBloc(false);
                                    sb.setNait(true);
                                    sb.start();
                                    bg.setContientSnoBees(false);
                                    bg.vaEtreDetruitParNaissance(true);
                                    destructionBloc(bg.getCoordonnees());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void pengoIsDead(){
        int i_pen=0;
        
        for (int i = 0; i < p.size(); i++) {
            
            if(p.get(i) instanceof P_Pengo){
                P_Pengo pen = (P_Pengo) p.get(i);
                pen.delVie();
                i_pen = i;
                if(pen.getVie() < 0)
                    gameOver();
            }
            else{
                p.get(i).pause();
            }
        }
        try {
            Thread.sleep(2000);
            for(int i=0;i<p.size();i++){
                p.get(i).reprise();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Affiche la carte actuelle en console.
     * Evolution à venir -> retourner la carte afin d'effectuer un affichage graphique
     * 
     */
    public void majAfficheCarte(){
        if(!niveauFini){
            //System.out.print("\033[2J\033[1;1H"); // Clear console
            System.out.println(m);
            fenetre_principale.setCarte(m.getCarte());
        }
    }
    
    /**
     * Permet la destruction du bloc à la coordonnée passé en paramètre.
     * 
     * @param c Coordonnées du bloc à détruire
     */
    private void destructionBloc(Coordonnees c){
        int i_b=-1;
        for (int i=0;i<b.size();i++) {
            if(b.get(i).getCoordonnees().comp(c)){
                if(b.get(i) instanceof BlocGlace){
                    BlocGlace bg = (BlocGlace)b.get(i);
                    if(!bg.getContientSnobees()){
                        for(int j=0;j<bg.getFinDestruction();j++)
                            majAfficheCarte();
                    }
                    m.detruireBloc(c, this);
                    i_b = i;
                    if(bg.getContientSnobees()){
                        snoBeesMort(c);
                    }
                }
            }
        }
        if(i_b!=-1){
            b.remove(i_b);
        }
    }
    
    /**
     * Le bloc à la coordonnée passé en paramètre va être déplacé juqu'à un obstacle
     * 
     * @param c Coordonnées du bloc à déplacer
     * @param dir direction actuel du personnage qui va pousser afin de savoir quel bloc sera déplacé
     */
    private void bougerBloc(Coordonnees c, Personnage.Directions dir){
        b.stream().filter((lb) -> (lb.getCoordonnees().comp(c))).forEach((lb) -> {
            m.pousseBloc(lb, c, dir, this);
        });
    }
    
    
    
    /**
     * Permet aux Pengo, aux Snobees d'effectuer des actions
     * 
     * @param o Instance de l'objet qui va effectuer l'action
     * @param dir Direction actuel du personnage pour savoir où effectuer l'action
     * @param act L'action qui sera effectuer en fonction de l'objet et de la direction
     * @return 
     */
    public boolean action(Object o, Personnages.Personnage.Directions dir, Personnages.Personnage.Actions act){
        //On part du principe que le déplacement est bon
        boolean moveOk = true;
        //Coordonnées qui va etre teste
        Coordonnees c;
        //Selon le bloc, la destruction sera possible ou non
        boolean destructionOK = false;
        
        //Si l'objet est un P_Pengo
        if(o instanceof P_Pengo){
            //On part du principe qu'aucune vie n'est perdu
            boolean vieEnMoins = false;
            //Cast de l'objet passé en paramètre pour l'utiliser
            P_Pengo pen = (P_Pengo) o;
            
            switch(dir){
                case dirHaut :
                    c = new Coordonnees(pen.getCoordonnees().getX(), pen.getCoordonnees().getY()-1);
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        pen.setPousseDetruire(true);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.objet(c)){
                                if(m.objet(new Coordonnees(c.getX(), c.getY()-1))){
                                    destructionBloc(c);
                                }
                                else{
                                    bougerBloc(c, dir);
                                }
                            }
                        }
                        else{
                            m.faireTremblerMur(dir, this);
                        }
                        try {
                            Thread.sleep(750);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pen.setPousseDetruire(true);
                    }
                    
                    break;
                case dirBas :
                    c = new Coordonnees(pen.getCoordonnees().getX(), pen.getCoordonnees().getY()+1);
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        pen.setPousseDetruire(true);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.objet(c)){
                                if(m.objet(new Coordonnees(c.getX(), c.getY()+1))){
                                    destructionBloc(c);
                                }
                                else{
                                    bougerBloc(c, dir);
                                }
                            }
                        }
                        else{
                            m.faireTremblerMur(dir, this);
                        }
                        try {
                            Thread.sleep(750);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pen.setPousseDetruire(true);
                    }
                    
                    break;
                case dirDroite :
                    c = new Coordonnees(pen.getCoordonnees().getX()+1, pen.getCoordonnees().getY());

                    if(act.equals(Personnage.Actions.bouger)){
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        pen.setPousseDetruire(true);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.objet(c)){
                                if(m.objet(new Coordonnees(c.getX()+1, c.getY()))){
                                    destructionBloc(c);
                                }
                                else{
                                    bougerBloc(c, dir);
                                }
                            }
                        }
                        else{
                            m.faireTremblerMur(dir, this);
                        }
                        try {
                            Thread.sleep(750);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pen.setPousseDetruire(true);
                    }
                    
                    break;
                case dirGauche :
                    c = new Coordonnees(pen.getCoordonnees().getX()-1, pen.getCoordonnees().getY());
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        pen.setPousseDetruire(true);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.objet(c)){
                                if(m.objet(new Coordonnees(c.getX()-1, c.getY()))){
                                    destructionBloc(c);
                                }
                                else{
                                    bougerBloc(c, dir);
                                }
                            }
                        }
                        else{
                            m.faireTremblerMur(dir, this);
                        }
                        pen.setPousseDetruire(true);
                    }
                    
                    break;
                default:
                    moveOk = false;
            }
            checkFinJeu();
        }
        //Si l'objet est un SnoBees
        else if (o instanceof SnoBees){
            SnoBees sb = (SnoBees) o;
            
            switch(dir){
                case dirHaut :
                    c = new Coordonnees(sb.getCoordonnees().getX(), sb.getCoordonnees().getY()-1);
                    if(m.move(sb, sb.getCoordonnees(), c, this))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirBas :
                    c = new Coordonnees(sb.getCoordonnees().getX(), sb.getCoordonnees().getY()+1);
                    if(m.move(sb, sb.getCoordonnees(), c, this))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirDroite :
                    c = new Coordonnees(sb.getCoordonnees().getX()+1, sb.getCoordonnees().getY());
                    if(m.move(sb, sb.getCoordonnees(), c, this))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirGauche :
                    c = new Coordonnees(sb.getCoordonnees().getX()-1, sb.getCoordonnees().getY());
                    if(m.move(sb, sb.getCoordonnees(), c, this))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                default:
                    moveOk = false;
            }
        }
        
        this.majAfficheCarte();
        
        return moveOk;
    }

    public void snobeesParalyse(Coordonnees coord, Map.elementCarte e) {
        Coordonnees c = new Coordonnees(coord.getY(), coord.getX());
        for(int i=0;i<p.size();i++){
            
            if(p.get(i).getCoordonnees().comp(c)){
                if(p.get(i) instanceof SnoBees){
                    SnoBees sn = (SnoBees)p.get(i);
                    if(!sn.getCacheDansBloc()){
                        if(e.equals(Map.elementCarte.SnoBeesParalyse)){
                            sn.setParalyse(true);
                            m.changeSnoBees(c, Map.elementCarte.SnoBeesParalyse);
                        }
                        else if(e.equals(Map.elementCarte.snoBees)){
                            sn.setParalyse(false);
                            m.changeSnoBees(c, Map.elementCarte.snoBees);
                        }
                        }
                }
            }
        }
    }
    
    public P_Pengo getPengo(){
        P_Pengo r = null ;
        for(int i = 0 ; i < p.size() ; i++){
            if(p.get(i) instanceof P_Pengo){
                r = (P_Pengo)p.get(i) ;
                
            }
        }
        return r ;
    }
    public SnoBees getSnobees(Coordonnees c){
        SnoBees r=null;
        
        for(int i=0;i<p.size();i++){
            if(p.get(i).getCoordonnees().comp(c))
                r=(SnoBees)p.get(i);
        }
        
        return r;
    }
    
    public Mur getMur(Coordonnees c){
        Mur m = null ;
        for(int i = 0 ; i < mur.size() ; i++){
            if(mur.get(i).getCoordonnees().comp(c))
                m=mur.get(i);
        }
        return m ; 
    }
    
    public void murTremble(Coordonnees c,boolean b){
        for(int i = 0 ; i < mur.size() ; i++) {
            if(mur.get(i).getCoordonnees().comp(c)){
                mur.get(i).setTremble(b);
                majAfficheCarte();
            }
        }    
    }
    
    public Bloc getBloc(Coordonnees c){
        Bloc bc = null;
        for(int i=0;i<b.size();i++){
            if(b.get(i).getCoordonnees().comp(c)){
                bc=b.get(i);
            }
        }
        return bc;
    }
}

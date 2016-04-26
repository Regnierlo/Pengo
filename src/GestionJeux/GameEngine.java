/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.*;
import Ressources.*;
import Vue.EntrerPseudo;
import Vue.Fenetre;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
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
    private final Map m;
    private final Score s;
    private int nbSnoBeesActif;
    private int nbSnoBeesCache;
    private boolean bonusBlocsSpeciaux;
    private long chronometre;
    private String name;
    private boolean scoreAttribue;
    private final Fenetre fenetre_principale ;
    private Thread tChecker ;
    private int niveau;
    private EntrerPseudo pseudo;
    private final JFrame Menu;
    
    private boolean niveauFini ;
    
    public GameEngine(JFrame me){
        Menu=me;
        //Instanciation des variables
        nbSnoBeesActif=0;
        nbSnoBeesCache=0;
        p = new ArrayList<>();
        b = new ArrayList<>();
        mur = new ArrayList<>() ;
        m = new Map(-1);
        s = new Score();
        chronometre = 0;
        KeyListener[] kl = getKeyListener();
        fenetre_principale = new Fenetre(this,kl) ;
        scoreAttribue = false;
        niveau = 1;
        
        fenetre_principale.setVisible(true);
        //Initialisation de la carte
        init() ;
        ThreadCheckEndGame();
    }
    
    private int getNbSnoBees(){
        return (nbSnoBeesActif+nbSnoBeesCache) ;
    }
    
    private void ThreadCheckEndGame(){
        tChecker=new Thread(){
            @Override
            public void run(){
                do{
                    System.out.println("\t\t\t\t\tNB SNOBEES : "+(getNbSnoBees()));
                    if(getNbSnoBees()== 0 )
                        niveauFini=true;
                        
                    
                    //System.out.println("\t\t\t\t\tNB SNOBEES : "+(nbSnoBeesActif+nbSnoBeesCache));
                }while(!niveauFini);
                System.out.println("FIN DU JEU");
                finNiveau();
            }
        };
        tChecker.start();
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
        p = m.initThread(this) ;
        b = m.initBloc() ;
        mur = m.initMur() ;
        niveauFini = false ;
        bonusBlocsSpeciaux = false ;
        
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
           
        majAfficheCarte();
    }
    
    private synchronized void finNiveau(){
        if(!scoreAttribue){
            scoreAttribue=true;
            for(int i=0;i<p.size();i++){
                p.get(i).arreter();
                if(p.get(i).getJoueur())
                    fenetre_principale.removeKeyListener(p.get(i));
            }
            majAfficheCarte();
            s.pointFinNiveau(tempsSec());
            fenetre_principale.setVisible(false);
            pseudo = new EntrerPseudo();
            while(!pseudo.getEtat()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            name = pseudo.getPseudo();
            s.writeScore(name);
            System.out.println("\n\n\n\n\n\n\n\n\n\nVous avez vaincus "+name+" !");
            System.out.println("Fini en : "+temps());
            System.out.println("Avec un score total de : "+s.getScore());
            Menu.setVisible(true);
        }
    }
    
    public void snobeePousseParBloc(Coordonnees c, Personnage.Directions dir) {
        Coordonnees cn;// = new Coordonnees(c.getX()-1, c.getY());
        
        switch(dir){
            
            case dirHaut:
                cn = new Coordonnees(c.getX(), c.getY()-1);
                c = cn;
                cn.setCoordonnees(new Coordonnees(c.getX(), c.getY()-1));
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
                break ;
        }
        
        int i_sno =0;
        for(int i=0;i<p.size();i++){
            if(p.get(i).getCoordonnees().comp(c)){
                i_sno = i;
            }
        }
        SnoBees sb = (SnoBees) p.get(i_sno);
        sb.setDirection(dir);
        sb.stopper();
        sb.setParalyse(false);
        
        sb.setVaMourirParBloc(true);
        
        
        if(m.move(sb, c, cn, this)){
            sb.getCoordonnees().setCoordonnees(cn);
            
        }
        else{
            snobeeMort(sb);
            snobeeEcrase();
            checkNombreSnobees();
        }
    }
    
    private void gameOver(){
        for(int i=0;i<p.size();i++){
            p.get(i).stopper();
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nGAME OVER");
    }
    
    public void blocsSpeciauxAlignes(){
        if(!bonusBlocsSpeciaux){
            if(m.verifBlocContreMur())
                s.pointBlocSpeciaux(true);
            else
                s.pointBlocSpeciaux(false);
            bonusBlocsSpeciaux = true;
            fenetre_principale.setScore(String.valueOf(s.getScore()));
        }
    }
    
    public void snobeeEcrase(){
        s.pointSnobeesEcrase();
        fenetre_principale.setScore(String.valueOf(s.getScore()));
        //checkFinJeu();
    }
    
    private void checkFinJeu(){
        if((nbSnoBeesActif+nbSnoBeesCache) <= 0){
            niveauFini=true;
            finNiveau();
        }
    }
    
    public void snobeeMort(SnoBees sb){
        if(sb.getCacheDansBloc()){
            nbSnoBeesCache--;
            p.remove(sb);
        }
        else if(sb.getParalyse()){
            p.remove(sb);
            if(nbSnoBeesCache >0){
                nbSnoBeesCache--;
                naissanceSnobees();
            }
            else{
                nbSnoBeesActif--;
            }
        }
        else{
            p.remove(sb);
            if(nbSnoBeesCache>0){
                naissanceSnobees();
                nbSnoBeesCache--;
            }
            else{
                nbSnoBeesActif--;
            }
        }
        checkNombreSnobees();
    }
    
    private void naissanceSnobees(){
        boolean uneNaissance=false;
        int i=0;
        do{
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
            i++;
        }while(i<b.size() && !uneNaissance);
    }
    
    public void progressNaissante(int i, Coordonnees c, Personnages.SnoBees.typeSnobees typeSnobees){
        switch(i){
            case 1:
                m.changeCarte(Map.elementCarte.naissanceSnoobees1, c);
                break;
            case 2:
                m.changeCarte(Map.elementCarte.naissanceSnoobees2, c);
                break;
            case 3:
                m.changeCarte(Map.elementCarte.naissanceSnoobees3, c);
                break;
            case 4:
                m.changeCarte(Map.elementCarte.naissanceSnoobees4, c);
                break;
            case 5:
                m.changeCarte(Map.elementCarte.naissanceSnoobees5, c);
                break;
            case 6:
                m.changeCarte(Map.elementCarte.naissanceSnoobees6, c);
                break;
            default:
                switch(typeSnobees){
                    case normal:
                        m.changeCarte(Map.elementCarte.snoBees, c);
                        break;
                    case miRamboMiIdiot:
                        m.changeCarte(Map.elementCarte.snobeesMiRambo, c);
                        break;
                    case rambo:
                        m.changeCarte(Map.elementCarte.snoBeesRambo, c);
                        break;
                }
                break;
        }
        majAfficheCarte();
    }
    
    /**
     * Change le comportement des snobees selon leur nombre et le niveau de difficulté
     */
    private void checkNombreSnobees(){
        if((nbSnoBeesActif == 1) && (nbSnoBeesCache==0) && niveau!=3){
            for(int i=0;i<p.size();i++){
                if(p.get(i) instanceof SnoBees){
                    SnoBees sb = (SnoBees)p.get(i);
                    if(sb.getComportement() != SnoBees.typeSnobees.rambo && !sb.getVaMourirParBloc())
                         sb.setComportement(SnoBees.typeSnobees.rambo);
                }
            }
        }
        if(nbSnoBeesActif > 1 && niveau==2){
            for(int i=0;i<p.size();i++){
                if(p.get(i) instanceof SnoBees){
                    SnoBees sb = (SnoBees)p.get(i);
                    if(sb.getComportement() == SnoBees.typeSnobees.normal)
                        sb.setComportement(SnoBees.typeSnobees.miRamboMiIdiot);
                }
            }
        }
        if(niveau==3){
            for(int i=0;i<p.size();i++){
                if(p.get(i) instanceof SnoBees){
                    SnoBees sb = (SnoBees)p.get(i);
                    if(sb.getComportement() == SnoBees.typeSnobees.normal || sb.getComportement()==SnoBees.typeSnobees.miRamboMiIdiot)
                        sb.setComportement(SnoBees.typeSnobees.rambo);
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
                pen.mort();
                m.changeCarte(Map.elementCarte.pengoMort, pen.getCoordonnees());
                if(pen.getVie() < 0)
                    gameOver();
            }
            else{
                p.get(i).pause();
            }
        }
        try {
            Thread.sleep(1000);
            m.changeCarte(Map.elementCarte.pengoMort2, p.get(i_pen).getCoordonnees());
            Thread.sleep(1000);
            
            for(int i=0;i<p.size();i++){
                p.get(i).reprise();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        
            
            
            
        }
        P_Pengo pen = (P_Pengo)p.get(i_pen);
        pen.renaissance();
        m.changeCarte(Map.elementCarte.pengo, pen.getCoordonnees());
    }
    
    /**
     * Affiche la carte actuelle en console.
     * Evolution à venir -> retourner la carte afin d'effectuer un affichage graphique
     * 
     */
    public void majAfficheCarte(){
        if(!niveauFini){
            //System.out.print("\033[2J\033[1;1H"); // Clear console
            //System.out.println(m);
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
                        for(int j=0;j<bg.getFinDestruction();j++){
                            majAfficheCarte();
                        }
                    }
                    m.detruireBloc(c, this);
                    i_b = i;
                    if(bg.getContientSnobees()){
                        SnoBees sb=null;
                        for (Personnage p1 : p) {
                            if(p.get(i).getCoordonnees().comp(c))
                                if(p.get(i) instanceof SnoBees){
                                    sb = (SnoBees)p.get(i);
                                }
                        }
                        if(sb!=null)
                            snobeeMort(sb);
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
     * @param o Instance de l'isBlockOrIsMur qui va effectuer l'action
     * @param dir Direction actuel du personnage pour savoir où effectuer l'action
     * @param act L'action qui sera effectuer en fonction de l'isBlockOrIsMur et de la direction
     * @return 
     */
    public boolean action(Object o, Personnages.Personnage.Directions dir, Personnages.Personnage.Actions act){
        //On part du principe que le déplacement est bon
        boolean moveOk = true;
        //Coordonnées qui va etre teste
        Coordonnees c;
        //Selon le bloc, la destruction sera possible ou non
        boolean destructionOK = true;
        int action = -1 ;
        //Si l'isBlockOrIsMur est un P_Pengo
        if(o instanceof P_Pengo){
            //On part du principe qu'aucune vie n'est perdu
            boolean vieEnMoins = false;
            //Cast de l'isBlockOrIsMur passé en paramètre pour l'utiliser
            P_Pengo pen = (P_Pengo) o;
            Coordonnees temp=new Coordonnees(pen.getCoordonnees());
            
            boolean aPousseDetruit = false;
            
            switch(dir){
                case dirHaut :
                    c = new Coordonnees(pen.getCoordonnees().getX(), pen.getCoordonnees().getY()-1);
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        pen.setPousseDetruire(true);
                        pen.setEnAction(true);
                        m.changeCarte(Map.elementCarte.pengoPoussePendant, temp);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX(), c.getY()-1))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
                                    bougerBloc(c, dir);
                                }
                            }
                        }
                        else{
                            m.faireTremblerMur(dir, this);
                        }
                        try {
                            Thread.sleep(1100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pen.setPousseDetruire(false);
                        m.changeCarte(Map.elementCarte.pengoPousseApres, temp);
                        pen.setEnAction(false);
                        aPousseDetruit = true;
                    }
                    
                    break;
                case dirBas :
                    c = new Coordonnees(pen.getCoordonnees().getX(), pen.getCoordonnees().getY()+1);
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        action =  0 ;
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        pen.setPousseDetruire(true);
                        pen.setEnAction(true);
                        m.changeCarte(Map.elementCarte.pengoPoussePendant, temp);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX(), c.getY()+1))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
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
                        pen.setPousseDetruire(false);
                        m.changeCarte(Map.elementCarte.pengoPousseApres, temp);
                        pen.setEnAction(false);
                        aPousseDetruit = true;
                    }
                    break;
                case dirDroite :
                    c = new Coordonnees(pen.getCoordonnees().getX()+1, pen.getCoordonnees().getY());
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        pen.setPousseDetruire(true);
                        pen.setEnAction(true);
                        m.changeCarte(Map.elementCarte.pengoPoussePendant, temp);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX()+1, c.getY()))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
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
                        pen.setPousseDetruire(false);
                        m.changeCarte(Map.elementCarte.pengoPousseApres, temp);
                        pen.setEnAction(false);
                        aPousseDetruit = true;
                    }
                    break;
                case dirGauche :
                    c = new Coordonnees(pen.getCoordonnees().getX()-1, pen.getCoordonnees().getY());
                    
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(pen, pen.getCoordonnees(), c, this))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        pen.setPousseDetruire(true);
                        pen.setEnAction(true);
                        m.changeCarte(Map.elementCarte.pengoPoussePendant, temp);
                        majAfficheCarte();
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX()-1, c.getY()))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
                                    bougerBloc(c, dir);
                                }
                            }
                        }
                        else{
                            m.faireTremblerMur(dir, this);
                        }
                        pen.setPousseDetruire(false);
                        m.changeCarte(Map.elementCarte.pengoPousseApres, temp);
                        pen.setEnAction(false);
                        aPousseDetruit = true;
                    }
                    break;
                default:
                    moveOk = false;
                    destructionOK = false ;
                    break;
            }
            //checkFinJeu();
            if(aPousseDetruit)
                m.changeCarte(Map.elementCarte.pengo, temp);
        }
        //Si l'isBlockOrIsMur est un SnoBees
        else if (o instanceof SnoBees){
            SnoBees sb = (SnoBees) o;
            
            switch(dir){
                
                case dirHaut :
                    c = new Coordonnees(sb.getCoordonnees().getX(), sb.getCoordonnees().getY()-1);
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(sb, sb.getCoordonnees(), c, this))
                            sb.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if (act.equals(Personnage.Actions.pousser_detruire)) {
                        action = 1 ;
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX(), c.getY()-1))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
                                }
                            }
                        }
                    }
                    break;
                case dirBas :
                    c = new Coordonnees(sb.getCoordonnees().getX(), sb.getCoordonnees().getY()+1);
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(sb, sb.getCoordonnees(), c, this))
                            sb.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX(), c.getY()+1))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
                                }
                            }
                        }
                        
                    }
                    
                    break;
                case dirDroite :
                    c = new Coordonnees(sb.getCoordonnees().getX()+1, sb.getCoordonnees().getY());
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(sb, sb.getCoordonnees(), c, this))
                            sb.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX()+1, c.getY()))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
                                }
                            }
                        }
                    }
                    break;
                case dirGauche :
                    c = new Coordonnees(sb.getCoordonnees().getX()-1, sb.getCoordonnees().getY());
                    if(act.equals(Personnage.Actions.bouger)){
                        action = 0 ;
                        if(m.move(sb, sb.getCoordonnees(), c, this))
                            sb.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        action = 1 ;
                        if(!m.isMur(c, dir)){
                            if(m.isBlockOrIsMur(c)){
                                if(m.isBlockOrIsMur(new Coordonnees(c.getX()-1, c.getY()))){
                                    destructionBloc(c);
                                }
                                else{
                                    destructionOK = false ;
                                }
                            }
                        }
                    }
                    break;
                default:
                    moveOk = false;
                    destructionOK = false ;
            }
        }
        majAfficheCarte();
        if(action == 1)
            return destructionOK ;
        else if (action==0)
            return moveOk ;
        return false ;
    }
    
    
    public Coordonnees PengoDetected(int perimetreDeSecurite, Coordonnees snobee){
        boolean iSeePengo = false ;
        Coordonnees pengoCoord = null ;
        double random = 1 ;
        
        for(int i = 0 ; i < p.size() ; i++){
            if(iSeePengo) {
               random = Math.random() ;
            }
            if(random > 0.5)
                if((p.get(i).getCoordonnees().getX())  < (snobee.getX() + perimetreDeSecurite) 
                    && (p.get(i).getCoordonnees().getX() ) > (snobee.getX() - perimetreDeSecurite) 
                    && (p.get(i).getCoordonnees().getY() < (snobee.getY() + perimetreDeSecurite))
                    && (p.get(i).getCoordonnees().getY() > (snobee.getY() - perimetreDeSecurite))){
                        iSeePengo = true ;
                        pengoCoord = p.get(i).getCoordonnees() ;
                }
        }
        return pengoCoord ;
    }
    

    public void snobeeParalyse(Coordonnees coord, Map.elementCarte e) {
        Coordonnees c = new Coordonnees(coord.getY(), coord.getX());
        for(int i=0;i<p.size();i++){
            
            if(p.get(i).getCoordonnees().comp(c)){
                if(p.get(i) instanceof SnoBees){
                    SnoBees sn = (SnoBees)p.get(i);
                    if(!sn.getCacheDansBloc()){
                        if(e.equals(Map.elementCarte.SnoBeesParalyse)
                            || e.equals(Map.elementCarte.snoBeesMiRamboParalyse)
                            || e.equals(Map.elementCarte.SnoBeesRamboParalyse)){
                            
                            sn.setParalyse(true);
                            
                            switch(sn.getComportement()){
                                case normal:
                                    m.changeSnoBees(c, Map.elementCarte.SnoBeesParalyse, sn.getComportement());
                                    break;
                                case miRamboMiIdiot:
                                    m.changeSnoBees(c, Map.elementCarte.snoBeesMiRamboParalyse, sn.getComportement());
                                    break;
                                case rambo:
                                    m.changeSnoBees(c, Map.elementCarte.SnoBeesRamboParalyse, sn.getComportement());
                                    break;
                            }
                            majAfficheCarte();
                        }
                        else if(e.equals(Map.elementCarte.snoBees)
                                || e.equals(Map.elementCarte.snobeesMiRambo)
                                || e.equals(Map.elementCarte.snoBeesRambo)){
                            
                            sn.setParalyse(false);
                            switch(sn.getComportement()){
                                case normal:
                                    m.changeSnoBees(c, Map.elementCarte.snoBees, sn.getComportement());
                                    break;
                                case miRamboMiIdiot:
                                    m.changeSnoBees(c, Map.elementCarte.snobeesMiRambo, sn.getComportement());
                                    break;
                                case rambo:
                                    m.changeSnoBees(c, Map.elementCarte.snoBeesRambo, sn.getComportement());
                                    break;
                            }
                            majAfficheCarte();
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
    
    public P_Pengo getPengo(Coordonnees c){
        P_Pengo r = null ;
        for(int i=0;i<p.size();i++){
            if(p.get(i).getCoordonnees().comp(c))
                r=(P_Pengo)p.get(i);
        }
        return r ;
    }
    
    
    
    public SnoBees getSnobee(Coordonnees c){
        SnoBees r=null;
        for(int i=0;i<p.size();i++){
            if(p.get(i).getCoordonnees().comp(c))
                r=(SnoBees)p.get(i);
        }
        if(r==null)
            System.out.println(m);
        return r;
    }
    
    public boolean isSnobees(Coordonnees c){
        boolean res=false;
        for(int i=0;i<p.size();i++)
            if(p.get(i).getCoordonnees().comp(c))
                res = true;
        
        return res;
    }
    
    public Mur getMur(Coordonnees c){
        Mur mu = null ;
        for(int i = 0 ; i < mur.size() ; i++){
            if(mur.get(i).getCoordonnees().comp(c))
                mu=mur.get(i);
        }
        return mu ; 
    }
    
    public void murTremble(Coordonnees c, boolean b){
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

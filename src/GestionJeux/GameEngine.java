/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.*;
import Ressources.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author loisr
 */
public class GameEngine {
    
    // Déclaration des variables
    private List<Personnage> p;
    private List<Bloc> b;
    private final Niveaux n;
    private final Map m;
    
    private boolean niveauFini;
    
    public GameEngine(){
        //Instanciation des variables
        p = new ArrayList<>();
        b = new ArrayList<>();
        n = new Niveaux();
        m = new Map(1, n);
        
        //Initialisation de la carte
        init();
    }
    
    /**
     * Permet d'initialiser le niveau (création des threads de Pengo et de SnoBees et des différents blocs).
     */
    private void init(){
        p = m.initThread(this);
        b = m.initBloc();
        niveauFini = false;
        
        for(int i=0;i<p.size();i++){
            p.get(i).start();
        }
        
        //TEMPORAIRE
            JFrame f = new JFrame();
            for (int i=0;i<p.size();i++)
                if(p.get(i).getJoueur())
                    f.addKeyListener(p.get(i));
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        ///////////////
    }
    
    /**
     * Affiche la carte actuelle en console.
     * Evolution à venir -> retourner la carte afin d'effectuer un affichage graphique
     * 
     * @deprecated 
     * @version 1.0
     */
    public void majAfficheCarte(){
        System.out.println(m);
    }
    
    /**
     * Permet la destruction du bloc à la coordonnée passé en paramètre.
     * 
     * @param c Coordonnées du bloc à détruire
     */
    private void destructionBloc(Coordonnees c){
        //Transformation d'un foreach par le warning
        b.stream().filter((lb) -> (lb.getCoordonnees().comp(c))).forEach((_item) -> {
            m.detruireBloc(c, this);
        });
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
                        if(m.move(pen, pen.getCoordonnees(), c))
                            pen.getCoordonnees().setCoordonnees(c);
                        else
                            moveOk = false;
                    }
                    else if(act.equals(Personnage.Actions.pousser_detruire)){
                        
                        if(m.objet(c)){
                            if(m.objet(new Coordonnees(c.getX(), c.getY()-1))){
                                destructionBloc(c);
                            }
                            else{
                                bougerBloc(c, dir);
                            }
                        }
                    }
                    
                    break;
                case dirBas :
                    c = new Coordonnees(pen.getCoordonnees().getX(), pen.getCoordonnees().getY()+1);
                    if(m.move(pen, pen.getCoordonnees(), c))
                        pen.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirDroite :
                    c = new Coordonnees(pen.getCoordonnees().getX()+1, pen.getCoordonnees().getY());
                    if(m.move(pen, pen.getCoordonnees(), c))
                        pen.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirGauche :
                    c = new Coordonnees(pen.getCoordonnees().getX()-1, pen.getCoordonnees().getY());
                    if(m.move(pen, pen.getCoordonnees(), c))
                        pen.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                default:
                    moveOk = false;
            }
        }
        //Si l'objet est un SnoBees
        else if (o instanceof SnoBees){
            SnoBees sb = (SnoBees) o;
            
            switch(dir){
                case dirHaut :
                    c = new Coordonnees(sb.getCoordonnees().getX(), sb.getCoordonnees().getY()-1);
                    if(m.move(sb, sb.getCoordonnees(), c))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirBas :
                    c = new Coordonnees(sb.getCoordonnees().getX(), sb.getCoordonnees().getY()+1);
                    if(m.move(sb, sb.getCoordonnees(), c))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirDroite :
                    c = new Coordonnees(sb.getCoordonnees().getX()+1, sb.getCoordonnees().getY());
                    if(m.move(sb, sb.getCoordonnees(), c))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                case dirGauche :
                    c = new Coordonnees(sb.getCoordonnees().getX()-1, sb.getCoordonnees().getY());
                    if(m.move(sb, sb.getCoordonnees(), c))
                        sb.getCoordonnees().setCoordonnees(c);
                    else
                        moveOk = false;
                    break;
                default:
                    moveOk = false;
            }
        }
        
        if(moveOk){
            this.majAfficheCarte();
        }
        return moveOk;
    }
}

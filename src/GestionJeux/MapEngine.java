/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import Personnages.P_Pengo;
import Ressources.Coordonnees;
import java.awt.Rectangle;

/**
 *
 * @author loisr
 */
public class MapEngine {
    //private Rectangle[] rt;
    private P_Pengo[] pt;
    private int nbInPt;
    private Boolean nouvellesInformations;
    
    public MapEngine(Object[] o){
        nouvellesInformations = false;
        //rt = new Rectangle[o.length];
        pt = new P_Pengo[o.length];
        for (int i=0;i<o.length;i++) {
            if(o[i] instanceof P_Pengo){
                P_Pengo p = (P_Pengo) o[i];
                pt[i] = p;
                /*System.out.println("Je suis un joueur ? "+p.getJoueur()+" Je met mon rectangle en "+i);
                System.out.println("JOUEUR  => "+rt[0]);
                System.out.println("PNJ     => "+rt[1]+"\n");*/
                //rt[i] = p.getMyImage().getRec();
            }
        }
        nbInPt = pt.length;
    }
    
    /**
     * Permet de détecter les collisions entre deux images (constitué de rectangle)
     * 
     * @param o Object que l'on va tester
     * @param c Nouvelles coordonnées voulu par l'objet appelant
     * deprecated Modifier pour les blocs  
    * @return true s'il y a une collision, false sinon
     */
    public synchronized boolean collisionDetect(Object o, Coordonnees c){
        Boolean collision=true;
        Boolean collisionTotale = false;
        //System.out.println("\t\tRestart res : "+res);
        if(o instanceof P_Pengo){
            P_Pengo p = (P_Pengo)o;
            Rectangle rec = new Rectangle(c.getX(), c.getY(),
                                          (int)p.getMyImage().getRec().getWidth(),
                                          (int)p.getMyImage().getRec().getHeight());

            if(this.nbInPt >= 2){
                for (int i=0;i<pt.length;i++) {
                    // Si la case du tableau n'est pas null, 
                    // on continue le traitement
                    if (pt[i] != null) {
                        // System.out.println("i non null : "+i);
                        // Si la case du tableau n'est pas le même objet 
                        // que l'objet qu'on traite, on continue le traitement
                        if (!(pt[i].equals(p))) {

                            collision  = true;
                            // On vérifie si une intersection a lieu entre 
                            // la nouvelle coordonnée et l'objet a détruire
                            if (!(rec.intersects(pt[i].getMyImage().getRec()))) {
                                collision = false;
                            }

                            collisionTotale = collisionTotale || collision;
                        }
                    }
                }
            }
        }
        
        return collisionTotale;
    }
    
    /**
     * @eprecated modifier pour les blocs
     * @eprecated a optimiser pour savoir quel bloc manger (la où l'intersection est la plus grande)
     * @param o 
     */
    public synchronized void detruire(Object o){
        if(o instanceof P_Pengo){
            P_Pengo p = (P_Pengo)o;
            
            Coordonnees c = new Coordonnees();
            
            switch (p.getDirectionActuel()) {
                case dirBas:
                    c.setX((p.getCoordonnees().getX()));
                    c.setY((p.getCoordonnees().getY())+(1*p.getVitesse()));
                    break;
                case dirHaut:
                    c.setX((p.getCoordonnees().getX()));
                    c.setY((p.getCoordonnees().getY())+(-1*p.getVitesse()));
                    break;
                case dirDroite:
                    c.setX((p.getCoordonnees().getX())+(1*p.getVitesse()));
                    c.setY((p.getCoordonnees().getY()));
                    break;
                case dirGauche:
                    c.setX((p.getCoordonnees().getX())+(-1*p.getVitesse()));
                    c.setY((p.getCoordonnees().getY()));
                    break;
            }
            
            Rectangle rec = new Rectangle(c.getX(), c.getY(), (int)p.getMyImage().getRec().getWidth(), (int)p.getMyImage().getRec().getHeight());
            Boolean dejaDetruit = false;
            for(int i =0;i<pt.length;i++){
                //Si la case du tableau n'est pas null, on continue le traitement
                if(pt[i]!=null){
                    //Si la case du tableau n'est pas le même objet que l'objet qu'on traite, on continue le traitement
                    if(!(pt[i].equals(p))){
                        //On vérifie si une intersection a lieu entre la nouvelle coordonnée et l'objet a détruire
                        if(rec.intersects(pt[i].getMyImage().getRec())){
                            if(!dejaDetruit){
                                this.pt[i] = null;
                                this.nouvellesInformations();
                                this.nbInPt--;
                                dejaDetruit = true;
                            }
                        }
                    }
                }
            }
        }
        
    }
    
    private void nouvellesInformations(){
        this.nouvellesInformations = true;
    }
    
    /**
     * @return un boolean qui indique si les informations de la classe ont été changées
     */
    public Boolean getNouvellesInformations(){
        return this.nouvellesInformations;
    }
    
    /**
     * Utilisé pour indiqué que l'information a été prise
     * /!\ Fonctionne uniquement pour GameEngine /!\
     */
    public void informationsRecupere(){
        if(Thread.currentThread().getStackTrace()[2].equals(GameEngine.class)){
            System.out.println("C'est moi");
        }
        //this.nouvellesInformations = false;
    }
    
    public P_Pengo[] getTabP_Pengo(){
        return this.pt;
    }
}

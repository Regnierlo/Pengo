/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ressources;

import Personnages.P_Pengo;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author loisr
 */
public class MapTestC {
    public Rectangle[] rt;
    public Coordonnees[] ct;
    
    public MapTestC(Object[] o){
        rt = new Rectangle[o.length];
        for (int i=0;i<o.length;i++) {
            if(o[i] instanceof P_Pengo){
                P_Pengo p = (P_Pengo) o[i];
                /*System.out.println("Je suis un joueur ? "+p.getJoueur()+" Je met mon rectangle en "+i);
                System.out.println("JOUEUR  => "+rt[0]);
                System.out.println("PNJ     => "+rt[1]+"\n");*/
                rt[i] = p.getMyImage().getRec();
                //ct[i] = p.getCoordonnees();
            }
        }
    }
    
    /**
     * 
     * @param o Object que l'on va tester
     * @return true s'il y a une collision, false sinon
     */
    public synchronized boolean collisionDetect(Object o, Coordonnees c){
        Boolean res=false;
        //System.out.println("\t\tRestart res : "+res);
        if(o instanceof P_Pengo){
            res = false;
            P_Pengo p = (P_Pengo)o;
            Rectangle r = new Rectangle(c.getX(), c.getY(), (int)p.getMyImage().getRec().getWidth(), (int)p.getMyImage().getRec().getHeight());
            
            for(int i=0;i<rt.length;i++){
                if(!(rt[i].equals(p.getMyImage().getRec()))){
                    /*if(!p.getMyImage().getRec().intersects(rt[i])){
                        res = true;
                    }*/
                    if(!r.intersects(rt[i])){
                        res = true;
                    }
                }
            }
            
        }
        
        return !res;
    }
}

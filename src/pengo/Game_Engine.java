package pengo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Personnages.*;
import Ressources.*;
import Vue.*;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loisr
 */
public class Game_Engine {
    
    private boolean jeuFini = false;
    private Ecran ec;
    private P_Pengo[] threadPengo;
    private P_Snobees[] threadMonstres;
    private Mur[] ressourcesMur = new Mur[48];
    
    public Game_Engine(){
        newGame();
    }
    
    /**
     * Creation d'une nouvelle partie
     */
    public void newGame(){
        
        
        //Chargement de la carte et création des threads et des ressources//
        loadMap(1);
        
        afficheCarte(threadPengo);
        
        
        
        //Gestion du jeu
        do{
            update();
            //checkMove();
        }while (!jeuFini);
        
    }
    
    private void checkMove(){
        
        //Rectangle utilisé pour savoir où repaint
        Rectangle r = new Rectangle();
        
        for(P_Pengo t : threadPengo){
            if(t != null){
                //System.out.println("Bougé ? "+t.getMove());
                if(t.getMove()){ //Si le Pengo a bougé
                    System.out.println("Bougé ? "+t.getMove());
                    System.out.println("\tNouvelle coordonnées : ["+t.getPositions().getX()+";"+t.getPositions().getY()+"]");
                    System.out.println("\tDimension image : ["+t.getImgWidth()+";"+t.getImgHeight()+"]");
                    r.setBounds(t.getPositions().getX()-50, t.getPositions().getY()-50, t.getImgWidth()+50, t.getImgHeight()+50);
                    t.informationMoveTaken();
                    System.out.println("Bougé ? "+t.getMove());
                }
            }
        }
        for(P_Snobees t : threadMonstres){
            if(t != null){
                
            }
                
        }
    }
    
    private void afficheCarte(KeyListener[] k){
        ec = new Ecran(k);
    }
    
    private void update(){
        for(P_Pengo t : threadPengo){
            if(t != null)
                ec.afficheMap(t.getPositions());
            
        }
        /*for(P_Snobees t : threadMonstres){
            if(t != null)
                ec.afficheMap(t.getPositions());
        }
        for(Mur rm : ressourcesMur){
            if(rm != null)
                ec.afficheMap(rm.getPositions());
                //ec.afficheMap(rm.getPositions(), new Rectangle(rm.getPositions().getX(), rm.getPositions().getY(), (rm.getPositions().getX()+rm.getImg().getWidth(ec)), (rm.getPositions().getY()+rm.getImg().getHeight(ec))));
        }*/
        
        
        //A faire la meme chose pour les blocs
        
        
        ec.repaint();
    }
    
    private void loadMap(int lvl){
        BufferedReader br=null;
        FileReader fr;
        int niveau=0;
        int nbMonstre=0;
        int nbPengo=1;
            
        try {
            if(lvl==1){
                fr=new FileReader(new File(getClass().getResource("/Niveaux//test.txt").toURI()));
                //Accede a la ressource du niveau et le lit avec br
                br = new BufferedReader(fr);
                //Recupere les informations
                niveau = Integer.parseInt(br.readLine());
                nbMonstre = Integer.parseInt(br.readLine());
                nbPengo+= Integer.parseInt(br.readLine());
                System.out.println("Niveau : "+niveau);
                System.out.println("Pengo : "+nbPengo);
                System.out.println("Monstres : "+nbMonstre+"\n");
            }else{
                System.out.println("Carte non trouve");
            }
            
            
            
            //Creation des threads
            threadPengo = new P_Pengo[nbPengo];
            threadMonstres = new P_Snobees[nbMonstre];
            int iThreadPengo = 0;
            int iThreadMonstre =0;
            int iMur=0;
            Positions pos=new Positions();
            //Initialisation du char de lecture
            char cm='0';
            
            while(cm!='F'){
                
                
                //REPRENDRE CREATION CARTE
                //PROBLEME SUR LA RECUPERATION DELA POSITION DE P
                
                
                
                //Permet de recréer la map
                cm = (char)br.read();
                
                //System.out.println("Lecture ["+pos.getX()+";"+pos.getY()+"] -> \""+cm+"\"");
                switch(cm){
                    case 'P':
                        if(iThreadPengo<nbPengo){
                            System.out.println("Pengo : ["+pos.getX()+";"+pos.getY()+"]");
                            threadPengo[iThreadPengo]=new P_Pengo(true, new Positions((pos.getX()+400), (pos.getY()+400)));
                            iThreadPengo++;
                        }
                        pos.setX(pos.getX()+1);
                        break;
                    case 'B':
                        pos.setX(pos.getX()+1);
                        break;
                    case 'S':
                        pos.setX(pos.getX()+1);
                        break;
                    case '0':
                        if(iMur<ressourcesMur.length){
                            //ressourcesMur[iMur]=new Mur(new Positions(pos.getX()+100, pos.getY()+100));/////////////////////////////////////////////////////////////MODIFIER
                            iMur++;
                        }
                        pos.setX(pos.getX()+1);
                        break;
                    case '\n':
                        pos.setX(0);
                        pos.setY(pos.getY()+1);
                        break;
                    case 'M':
                        if(iThreadMonstre<nbMonstre){
                            System.out.println("Monstres : ["+pos.getX()+";"+pos.getY()+"]");
                            //threadMonstres[iThreadMonstre]=new P_Snobees(false, new Positions(pos.getX()+200, pos.getY()+200));
                            iThreadMonstre++;
                        }
                        break;
                    default:
                        break;
                }
            }

            
            
            
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game_Engine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game_Engine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Game_Engine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Game_Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

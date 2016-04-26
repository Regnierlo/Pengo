/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Score {
    private int score;
    private int hs; //High Score
    private static final String chemin="topScore.txt";
    private final int nbTopScoreEnregistre=5;
    
    public Score(){
        score = 0;
        hs = rechercheHS();
    }
    
    public void pointBlocSpeciaux(boolean contreMur){
        if(contreMur)
            score += 5000;
        else
            score += 10000;
    }
    
    public void pointSnobeesEcrase(){
        score += 400;
    }
    
    public void pointSnobeesAttrape(){
        score += 100;
    }
    
    public void pointFinNiveau(int s){
        if(s < 20)
            score += 5000;
        else if(s >= 20 && s <30)
            score += 2000;
        else if(s >= 30 && s <40)
            score += 1000;
        else if(s >=40 && s <50)
            score += 500;
        else if(s >=50 && s<60)
            score+=10;
    }
    
    /**
     * Ecrit les scores dans un fichier
     *
     * @param n Le nom du joueur
     */
    public void writeScore(String n){
        
        //Ajout du nom à la fin dans le fichier score
        try {
            //Creation d'un OBJET fichier
            File f = new File(chemin);
            //Si le fichier n'existe pas on le crée
            if(!f.exists())
                f.createNewFile();
            //On crée un filewriter sur le fichier et l'écriture se fait à la fin
            FileWriter fw = new FileWriter(f, true);
            //Création de la string à ajouter
            String txt = n+" "+Integer.toString(score)+"\n";
            System.out.println(txt);
            System.out.print("fefzef");
            //Ajout de la string créée
            fw.write(txt);
            //Fermeture 
            fw.close();
            //trie du fichier
            trierScore(f);
        } catch (IOException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getHS(){
        return hs;
    }
    
    private int rechercheHS(){
        int r=-1;
        
        BufferedReader br = null;
        
        try{
            String ligne;
            String[] tmp;
            File f = new File(chemin);
            
            br=new BufferedReader(new FileReader(f));
            
            if((ligne=br.readLine()) != null){
                tmp=ligne.split(" ");
                r = Integer.parseInt(tmp[1]);
            }
            br.close();
            
        }catch(FileNotFoundException ex){
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                br.close();
            } catch (Exception ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return r;
    }
    
    /**
     * Trie le fichier des scores après le dernier ajout
     * 
     * @param f Fichier à trier
     */
    private void trierScore(File f){
        //Déclaration d'un BefferedReader à null
        BufferedReader br = null;
        
        try {
            //Instanciation de variables locales
            String ligne; //Ligne courante pour la lecture du fichier
            String tmp[]; //Tableau de string utilisé pour parser les lignes
            List<Integer> li = new ArrayList<>(); //Liste des scores
            List<String> ls = new ArrayList<>(); //Liste des lignes lu
            br = new BufferedReader(new FileReader(f)); 
            
            //LECTURE
            //Lecture du fichier de scores
            while((ligne=br.readLine()) != null){
                ls.add(ligne); //ajout de la ligne à la liste de string
                tmp=ligne.split(" "); //Décomposition de la ligne avec " " comme séparateur
                li.add(Integer.parseInt(tmp[1])); //On ajoute la partie score dans la liste des scores
            }
            br.close(); //On ferme le bufferedReader
            
            //TRIE
            Collections.sort(li); //Trie des scores par ordre croissant
            Collections.reverse(li); //Inversion des scores (trie par ordre decroissant)
            
            //NOUVELLE LISTE ORDONNEE
            String[][] topScore = new String[5][2];
            boolean trouver = false;
            int j = 0;
            for(int i=0;i<nbTopScoreEnregistre;i++){
                while(!trouver && j<ls.size()){
                    tmp=ls.get(j).split(" "); //Décomposition du string
                    if(li.get(i)==(Integer.parseInt(tmp[1]))){ //Après la récupération du score on vérifie si c'est le score qu'on cherche
                        //Si c'est ce qu'on cherche
                        topScore[i][0] = tmp[0];//On ajoute le nom dans la premiere case
                        topScore[i][1] = tmp[1];//On ajoute le score dans la seconde
                        trouver = true; //On indique qu'on l'a trouvé
                        ls.remove(j); //On l'enleve de l'ancienne liste pour éviter de le récupérer si deux joueurs ont eut égalité
                    }
                    j++;
                }
                j=0;
                trouver = false;
            }
            
            //CREATION DU NOUVEAU FICHIER TRIER
            f.delete(); //On supprime l'ancien
            f.createNewFile(); //On en crée un nouveau
            FileWriter fw = new FileWriter(f, true); // On écrit dans le fichier déclaré et à la fin
            
            //ECRITURE DES SCORES TRIES
            for(int i=0;i<nbTopScoreEnregistre;i++){
                fw.write(topScore[i][0]+" "+topScore[i][1]+"\n");
                //fw.write(nl.get(i)+"\n"); //On écrit dans le fichier les scores triés qui sont dans la liste définitive
            }
            fw.close();//On ferme l'outile d'écriture
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } finally { // Execution obligatoire même en cas d'erreur
            try {
                br.close(); // Fermeture du vefferedReader
            } catch (IOException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void afficheScore(){
        BufferedReader br = null;
        try {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            String ligne;
            br = new BufferedReader(new FileReader(new File(chemin)));
            while((ligne=br.readLine()) != null){
                System.out.println(ligne);
            }   br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String[][] getTopScore(){
        String[][] topScore = new String[5][2];
        
        BufferedReader br=null;
        File f = new File(chemin);
        
        try{
            String ligne;
            String tmp[];
            int i=0;
            br=new BufferedReader(new FileReader(f));
            
            while((ligne=br.readLine()) != null && i<5){
                tmp=ligne.split(" "); //Décomposition de la ligne avec " " comme séparateur
                topScore[i][0] = tmp[0];
                topScore[i][1] = tmp[1];
                i++;
            }
            br.close(); //On ferme le bufferedReader
            
            
        }catch(FileNotFoundException ex){
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                br.close();
            } catch (IOException ex) {
            }
        }
        
        
        return topScore ;
    }
    
    public int getScore(){
        return score;
    }
}

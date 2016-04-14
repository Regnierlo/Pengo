/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

/**
 *
 * @author loisr
 */
public class Niveaux {
    
    public String[][] getMap(int i){
        return this.choixNiveau(i);
    }
    
    /**
     * Verifie combien de pengo il reste sur la carte
     * 
     * @param c Carte passé en paramètre où sera effectué le traitement
     * @return Retourne le nombre de pengo sur la carte
     */
    public int majPengo(final String[][] c){
        int r = 0;
        
        for (String[] c1 : c) {
            for (int j = 0; j<c[0].length; j++) {
                if ("P".equals(c1[j])) {
                    r++;
                }
            }
        }
        
        return r;
    }
    
    /**
     * Vérifie combien de snobees il reste sur la carte
     * 
     * @param c Carte passé en paramètre où sera effectué le traitement
     * @return Retourne le nombre de snobees sur la carte
     */
    public int majSnowBees(final String[][] c){
        int r=0;
        
        for (String[] c1 : c) {
            for (int j = 0; j<c[0].length; j++) {
                if ("SB".equals(c1[j])) {
                    r++;
                }
            }
        }
        
        return r;
    }
    
    /**
     * Choisit un niveau dans ceux proposé
     * 
     * @param c
     * @return 
     */
    private String[][] choixNiveau(int c){
        
        String[][] r;
        switch(c){
                case 1:
                    String[][] choix1 = {
                        {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                        {"M", " ", "BG", "BG", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", "BG", "BG", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", "BG", "BS", "BG", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", "SB", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", "SB", " ", " ", "BG", "BG", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", "BG", "BG", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", "BG", " ", " ", " ", " ", "M"},
                        {"M", "SB", " ", "BG", "P", "BG", " ", "BG", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", "BG", " ", " ", " ", " ", "M"},
                        {"M", "SB", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"}
                    };
                    r=choix1;
                    break;
                case 2:
                    String[][] choix2 = {
                        {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                        {"M", "P", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"}
                    };
                    r=choix2;
                    break;
                default:
                    String[][] choixDefaut = {
                        {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", "P", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                        {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"}
                    };
                    r=choixDefaut;
                    break;
        }
        return r;
    }
}
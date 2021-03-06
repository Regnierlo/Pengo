/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

public class Niveaux {
    
    
    public Niveaux(){
    }
    
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
                if (Map.elementCarte.pengo.equalsName(c1[j])) {
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
    public int majSnobees(final String[][] c){
        int r=0;
        
        for (String[] c1 : c) {
            for (int j = 0; j<c[0].length; j++) {
                if (Map.elementCarte.snoBees.equalsName(c1[j])) {
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
        Labyrinthe lab=new Labyrinthe(10, 16);
        
        switch(c){
            case -3:
                r=lab.generation_labyrinthe(1, 2, 5);
                break;
            case -2:
                String[][] choix_1=lab.generation_labyrinthe(1, 3, 1);
                r=choix_1;
                break;
            case -1:
                r=lab.generation_labyrinthe(1, 2, 1);
                break;
            case 1:
                String[][] choix1 = {
                    {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                    {"M", " ", "G", "G", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", "G", "G", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", "G", "S", "G", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", "E", " ", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", "E", " ", " ", " ", "G", " ", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", "G", "G", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", " ", "G", "E", "G", " ", " ", "M"},
                    {"M", "E", " ", "G", "P", "G", " ", "G", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", " ", "G", " ", " ", " ", " ", "M"},
                    {"M", "E", " ", " ", " ", " ", " ", " ", " ", "M"},
                    {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"}
                };
                r=choix1;
                break;
            case 2:
                String[][] choix2 = {
                    {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                    {"M", "P", " ", " ", " ", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", " ", "S", " ", " ", " ", " ", "M"},
                    {"M", " ", "E", "S", " ", "S", " ", " ", " ", "M"},
                    {"M", " ", "S", " ", "G", " ", " ", " ", " ", "M"},
                    {"M", " ", " ", "E", " ", " ", " ", " ", " ", "M"},
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
            case 3:
                String[][] choix3 = {
                    {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                    {"M", "P", "E", " ", "C", " ", " ", " ", " ", "M"},
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
                r=choix3;
                break;
            case 4:
                String[][] choix4 = {
                    {"M", "M", "M", "M", "M", "M", "M", "M", "M", "M"},
                    {"M", " ", " ", " ", " ", "G", "E", "G", " ", "M"},
                    {"M", " ", " ", " ", " ", "G", "E", "G", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", "G", "C", " ", "M"},
                    {"M", " ", " ", " ", " ", " ", "P", " ", " ", "M"},
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
                r=choix4;
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

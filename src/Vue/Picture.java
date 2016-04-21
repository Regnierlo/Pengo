/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Image ;
import java.io.File ;

import java.io.IOException ;
import javax.imageio.ImageIO ;

public class Picture {
    Image[][] tabImages ;
    private final String chemin = "";
    
    public Picture() {
        
        tabImages = new Image[2][25] ;
        
        /// ----- PENGO ----- ///
        
        ////////// - De derrière
        
            try{
                tabImages[0][0] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Derriere_Pied_Droit.png"));    
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo vue de derrière, pied droit en avant");
            }
            try{
                tabImages[1][0] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Derriere_Pied_Gauche.png")); 
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo vue de derrière, pied gauche en avant");
            }
            
        ////////// - Allant vers la droite
        
            try{
                tabImages[0][1] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Droit_Pieds_Ecartes.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo allant à droite, les pieds écartés");
            }
            try{
                tabImages[1][1] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Droit_Pieds_Ensembles.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo allant à droite, les pieds joints");
            }
 
        ////////// - Vue de face
        
            try{
                tabImages[0][2] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Face_Pied_Droit.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo vue de face, pied droit en avant");
            }
            try{
                tabImages[1][2] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Face_Pied_Gauche.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo vue de face, pied gauche en avant");
            }           
            

        ////////// - Allant vers la gauche
        
            try{
                tabImages[0][3] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Gauche_Pieds_Ecartes.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo allant à gauche, les pieds écartés");
            }
            try{
                tabImages[1][3] =  ImageIO.read(new File(chemin+"src/Images/Pengo_Gauche_Pieds_Ensembles.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image de Pengo allant à gauche, les pieds joints");
            }
        
        /// ----- SnoBees ----- ///
        
        ////////// - De derrière
        
            try{
                tabImages[0][4] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Derriere.png"));    
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee vu de derrière");
            }
            try{
                tabImages[1][4] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Derriere_aplati.png")); 
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee vu de derrière aplati");
            }
            
        ////////// - Allant vers la droite
        
            try{
                tabImages[0][5] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Droit.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee allant à droite");
            }
            try{
                tabImages[1][5] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Droit_aplati.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee allant à droite, aplati");
            }
 
        ////////// - Vue de face
        
            try{
                tabImages[0][6] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Face.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee vu de face");
            }
            try{
                tabImages[1][6] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Face_aplati.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee vu de face, aplati");
            }           
            

        ////////// - Allant vers la gauche
        
            try{
                tabImages[0][7] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Gauche.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee allant à gauche");
            }
            try{
                tabImages[1][7] =  ImageIO.read(new File(chemin+"src/Images/SnoBees_Gauche_aplati.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image d'un snobee allant à gauche, aplati");
            }
        
        /// ---- BLOCS ---------------
        
            /// Bloc lambda
            try{
                tabImages[0][8] =  ImageIO.read(new File(chemin+"src/Images/bloc_normal.png"));     
            }catch(IOException e){
                System.out.println("Pas l'image du bloc de glace");
            }
            
            /// Bloc avec à l'intérieur un oeuf de sno-bees
            try{
                tabImages[1][8] =  ImageIO.read(new File(chemin+"src/Images/juste_un_bloc.png"));   
            }catch(IOException e){
               System.out.println("Pas l'image du bloc contenant un oeuf de snobee");
            }
            
            /// Companion Cube, remplace Diamond Block
            try{
                tabImages[0][9] =  ImageIO.read(new File(chemin+"src/Images/companion_cube.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du diamond block");
            }
         
        /// ---- FOND NOIR
        
        try{
            tabImages[1][9] =  ImageIO.read(new File(chemin+"src/Images/fond_noir.png"));   
        }catch(IOException e){
            System.out.println("L'image du fond n'est pas trouvé");
        }
        
        /// ----- MURS SIMPLES
        
            ///// Mur du bas
            
            try{
                tabImages[0][10] =  ImageIO.read(new File(chemin+"src/Images/mur_du_bas.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du bas");
            }
            
            ///// Mur de droite
            
            try{
                tabImages[0][11] =  ImageIO.read(new File(chemin+"src/Images/mur_de_droite.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur de droite");
            }
            
            ///// Mur du haut
            
            try{
                tabImages[0][12] =  ImageIO.read(new File(chemin+"src/Images/mur_du_haut.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du haut");
            }
            
            ///// Mur de gauche
            
            try{
                tabImages[0][13] =  ImageIO.read(new File(chemin+"src/Images/mur_de_gauche.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur de gauche");
            }
        
        /// ----- MURS QUI TREMBLENT
        
            ///// Mur du bas
            
            try{
                tabImages[1][10] =  ImageIO.read(new File(chemin+"src/Images/Mur_Bas_Tremble.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du bas qui tremble");
            }
            
            ///// Mur de droite
            
            try{
                tabImages[1][11] =  ImageIO.read(new File(chemin+"src/Images/Mur_Droit_Tremble.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur de droite qui tremble");
            }
            
            ///// Mur du haut
            
            try{
                tabImages[1][12] =  ImageIO.read(new File(chemin+"src/Images/Mur_Haut_Tremble.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du haut qui tremble");
            }
            
            ///// Mur de gauche
            
            try{
                tabImages[1][13] =  ImageIO.read(new File(chemin+"src/Images/Mur_Gauche_Tremble.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur de gauche qui tremble");
            }
            
        /// ---------- COIN 
        
            /// Coin en bas et à gauche
            try{
                tabImages[0][14] =  ImageIO.read(new File(chemin+"src/Images/mur_coin_bas_gauche.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du coin B/G");
            }
            
            /// Coin en bas et à droite
            try{
                tabImages[1][14] =  ImageIO.read(new File(chemin+"src/Images/mur_coin_bas_droit.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du coin B/D");
            }
            
            /// Coin en haut et à gauche
            try{
                tabImages[0][15] =  ImageIO.read(new File(chemin+"src/Images/mur_coin_haut_gauche.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du coin H/G");
            }
            
            /// Coin en haut et à droite
            try{
                tabImages[1][15] =  ImageIO.read(new File(chemin+"src/Images/mur_coin_haut_droit.png"));   
            }catch(IOException e){
                System.out.println("Pas l'image du mur du coin H/D");
            }
            
        ///------- UN SNOBEE SAUVAGE EST APPARU
        
            /// Naissance 01
                try{
                    tabImages[0][16] =  ImageIO.read(new File(chemin+"src/Images/naissance_1.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de la naissance du snobee 01");
                }
            
            /// Naissance 02
                try{
                    tabImages[1][16] =  ImageIO.read(new File(chemin+"src/Images/naissance_2.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de la naissance du snobee 02");
                }
            
            /// Naissance 03
                try{
                    tabImages[0][17] =  ImageIO.read(new File(chemin+"src/Images/naissance_3.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de la naissance du snobee 03");
                }
            
            /// Naissance 04
                try{
                    tabImages[1][17] =  ImageIO.read(new File(chemin+"src/Images/naissance_4.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de la naissance du snobee 04");
                }
            
            /// Naissance 05
                try{
                    tabImages[0][18] =  ImageIO.read(new File(chemin+"src/Images/naissance_5.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de la naissance du snobee 05");
                }
            
            /// Naissance 06
                try{
                    tabImages[1][18] =  ImageIO.read(new File(chemin+"src/Images/naissance_6.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de la naissance du snobee 06");
                }    
                
        /// ----- Pengo pousse un bloc
            //// Vue de derriere - après et pendant 
                try{
                    tabImages[0][19] =  ImageIO.read(new File(chemin+"src/Images/derriere_pousse_apres.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - derriere - après");
                } 
                try{
                    tabImages[1][19] =  ImageIO.read(new File(chemin+"src/Images/derriere_pousse_pendant.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - derriere - pendant");
                } 
            
            //// Va à droite - après et pendant 
                try{
                    tabImages[0][20] =  ImageIO.read(new File(chemin+"src/Images/droit_pousse_apres.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - droite - après");
                } 
                try{
                    tabImages[1][20] =  ImageIO.read(new File(chemin+"src/Images/droit_pousse_pendant.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - droite - pendant");
                } 
            
            //// Va à gauche - après et pendant 
                try{
                    tabImages[0][21] =  ImageIO.read(new File(chemin+"src/Images/gauche_pousse_apres.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - gauche - après");
                } 
                try{
                    tabImages[1][21] =  ImageIO.read(new File(chemin+"src/Images/gauche_pousse_pendant.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - gauche - pendant");
                } 
            
            //// Vue de face - après et pendant 
                try{
                    tabImages[0][22] =  ImageIO.read(new File(chemin+"src/Images/face_pousse_apres.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - face - après");
                } 
                try{
                    tabImages[1][22] =  ImageIO.read(new File(chemin+"src/Images/face_pousse_pendant.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de Pengo qui pousse un bloc - face - pendant");
                } 
            
        /// DESTRUCTION Bloc
        
            /// --- Destruction 1
                try{
                    tabImages[0][23] =  ImageIO.read(new File(chemin+"src/Images/destruction_1.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de destruction de bloc - 1");
                } 
            
            /// --- Destruction 2
            
                try{
                    tabImages[1][23] =  ImageIO.read(new File(chemin+"src/Images/destruction_4.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de destruction de bloc - 2");
                } 
            
            /// --- Destruction 3
                try{
                    tabImages[0][24] =  ImageIO.read(new File(chemin+"src/Images/destruction_5.png"));   
                }catch(IOException e){
                    System.out.println("Pas l'image de destruction de bloc - 3");
                } 
            
            ////
    }
    
    public Image getPicture(int x, int y){
        return tabImages[x][y] ;
    }
}

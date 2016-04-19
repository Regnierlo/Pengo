/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame ;
import javax.swing.JPanel ;

/**
 * @author Marie *
 */
public class Gestion_Ecran extends JFrame {

        public static void main(String[] args)
        {
            JFrame ecran = new JFrame();
        //}
    
        //public Gestion_Ecran(){
          
            ecran.setSize(450,600); // Définit la taille de la fenêtre
            ecran.setLocationRelativeTo(null); // On positionne la fenêtre au centre
            ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //  Termine le processus lorsqu'on clique sur la croix rouge
            ecran.setResizable(false); // N'autorise pas le redimensionnement de la fenêtre
            ecran.setUndecorated(false); // Si true : Retire les contours et les boutons de contrôle
            
            ecran.setLayout(new BorderLayout()); //
            
            
            Hud pan = new Hud();
            
            
            
            ecran.setVisible(true); // On rend visible la fenêtre
        
        }
}

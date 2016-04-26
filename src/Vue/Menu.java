/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import GestionJeux.GameEngine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame {

    JPanel centre = new JPanel(new GridLayout(3,2));
    BoutonMenu play = new BoutonMenu("P L A Y");
    BoutonMenu score = new BoutonMenu("S C O R E");
    BoutonMenu quitter = new BoutonMenu("E X I T");
    String chemin ="";
    ImageIcon imagePengo ;
    Border blackline ;
    JLabel labelImagePengo ;
    public Menu(){
    
        constructionMenu() ;
    
    }

    private void constructionMenu(){
        //blackline = BorderFactory.createLineBorder(Color.black);
        this.setSize(400,700) ;
        this.setLocationRelativeTo(null) ;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        play.addActionListener(new appuyerPlay());
        score.addActionListener(new appuyerScore());
        quitter.addActionListener(new appuyerQuitter());
        
        
        centre.setBackground(Color.BLACK);
        centre.add(new LabelMenu(1));
        centre.add(play);
        
        centre.add(new LabelMenu(2));
        centre.add(score);
        
        centre.add(new LabelMenu(3));
        centre.add(quitter);
        centre.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        
        try{
            imagePengo = new ImageIcon(chemin+"src/Images/pengo.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'image");
        }
        
        labelImagePengo = new JLabel(imagePengo);
        labelImagePengo.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        //// NORTH
        this.getContentPane().add(labelImagePengo, BorderLayout.NORTH);       
        /// CENTER
        this.getContentPane().add(centre, BorderLayout.CENTER);
        /// SOUTH
        this.getContentPane().add(new JLabel("Image rigolote à mettre"), BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    class appuyerPlay implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("vous avez appuyé sur play");
            setVisible(false);
            new GameEngine();
        }
    }
    
    class appuyerScore implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("vous avez appuyé sur score");
            
            new TableauScore();
        }
    }
    
     class appuyerQuitter implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}

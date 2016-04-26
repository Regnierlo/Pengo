/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import GestionJeux.GameEngine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame {

    private JPanel centre ;
    private BoutonMenu play ;
    private BoutonMenu score ;
    private BoutonMenu quitter ;
    private ImageIcon imagePengo ;
    private JLabel labelImagePengo ;
    
    public Menu(){
    
        constructionMenu() ;
    
    }

    private void constructionMenu(){
        
        this.setSize(400,700) ;
        this.setLocationRelativeTo(null) ;
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        this.setBackground(Color.BLACK);
        
        this.centre = new JPanel(new GridLayout(3,2));
        this.centre.setBackground(Color.BLACK);
        this.centre.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
         
        this.play = new BoutonMenu("P L A Y");
        this.play.addActionListener(new appuyerPlay());
        
        this.score = new BoutonMenu("S C O R E");
        this.score.addActionListener(new appuyerScore());
        
        this.quitter = new BoutonMenu("E X I T");
        this.quitter.addActionListener(new appuyerQuitter());
        
        
        this.centre.add(new LabelMenu(1));
        this.centre.add(play);
        
        this.centre.add(new LabelMenu(2));
        this.centre.add(score);
        
        this.centre.add(new LabelMenu(3));
        this.centre.add(quitter);
        
       
        try{
            this.imagePengo = new ImageIcon(Picture.chemin+"src/Images/pengo.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'image");
        }
        
        this.labelImagePengo = new JLabel(imagePengo);
        this.labelImagePengo.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        
        
        //// NORTH
        this.getContentPane().add(labelImagePengo, BorderLayout.NORTH);       
        /// CENTER
        this.getContentPane().add(centre, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
    
    class appuyerPlay implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("vous avez appuyé sur play");
            setVisible(false);
            new GameEngine();
        }
    }
    
    class appuyerScore implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("vous avez appuyé sur score");
            new TableauScore();
        }
    }
    
     class appuyerQuitter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}

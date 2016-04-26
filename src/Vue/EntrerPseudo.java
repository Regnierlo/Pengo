/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EntrerPseudo extends JDialog{
    
    private JTextField pseudo ;
    private JButton valider ;
    private ImageIcon imagePseudo ;
    private ImageIcon ok ;
    private ImageIcon ok_hover ;
    
   /* JPanel imagePanel = new JPanel(new BorderLayout()){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(imagePseudo.getImage(),0,0,this);
        }
    };*/
    
    public EntrerPseudo(){
        this.setResizable(false);
        pseudo = new JTextField("AAA") ;
        
        try{
            imagePseudo = new ImageIcon(Picture.chemin+"src/Images/pseudo.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'image");
        }
        
        try{
           ok = new ImageIcon(Picture.chemin+"src/Images/pseudo_ok.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        
        try{
           ok_hover = new ImageIcon(Picture.chemin+"src/Images/pseudo_ok_hover.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        
        constructionDialog();
    }
    
    
    private void constructionDialog(){
        
        this.setSize(400,200);
        this.setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        
        
        Font police = new Font("Courier",Font.BOLD, 24);
        pseudo.setFont(police);
        pseudo.setPreferredSize(new Dimension(400,25));
        pseudo.setBackground(Color.BLACK);
        pseudo.setForeground(Color.WHITE);
        pseudo.setHorizontalAlignment(JTextField.CENTER);
        
        valider.setIcon(ok);
        
        valider.addActionListener(new validerPseudo());
        
        this.getContentPane().add(new JLabel(imagePseudo),BorderLayout.NORTH);
        this.getContentPane().add(pseudo, BorderLayout.CENTER);
        this.getContentPane().add(valider, BorderLayout.SOUTH);
        
        this.setVisible(true);
        
        
    }
    
    class validerPseudo implements ActionListener{
        public void actionPerformed(ActionEvent e){
            valider.setIcon(ok_hover);
            System.out.println(pseudo.getText());
        }
    }
            
}

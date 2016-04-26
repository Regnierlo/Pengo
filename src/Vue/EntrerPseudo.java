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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EntrerPseudo extends JDialog{
    
    String chemin="";
    private JTextField pseudo = new JTextField("AAA");
    private JButton valider = new JButton("V A L I D E R");
    private ImageIcon imagePseudo ;
    private boolean disparu;
    
   /* JPanel imagePanel = new JPanel(new BorderLayout()){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(imagePseudo.getImage(),0,0,this);
        }
    };*/
    
    public EntrerPseudo(){
        disparu=false;
        try{
            imagePseudo = new ImageIcon(chemin+"src/Images/pseudo.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        
        constructionDialog();
    }
    
    
    private void constructionDialog(){
        this.setSize(400,200);
        this.setBackground(Color.black);
        Font police = new Font("Courier",Font.BOLD, 24);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        pseudo.setFont(police);
        pseudo.setPreferredSize(new Dimension(400,25));
        pseudo.setBackground(Color.BLACK);
        pseudo.setForeground(Color.WHITE);
        pseudo.setHorizontalAlignment(JTextField.CENTER);
        
        Font policeBouton = new Font("Courier",Font.PLAIN, 14);
        valider.setFont(policeBouton);
        valider.setBackground(Color.BLACK);
        valider.setForeground(Color.YELLOW);
        
        
        valider.addActionListener(new validerPseudo());
        
        this.getContentPane().add(new JLabel(imagePseudo),BorderLayout.NORTH);
        this.getContentPane().add(pseudo, BorderLayout.CENTER);
        
        this.getContentPane().add(valider, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }
    
    public String getPseudo(){
        return pseudo.getText();
    }
    
    public boolean getEtat(){
        return disparu;
    }
    
    class validerPseudo implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            disparu=true;
        }
    }
}

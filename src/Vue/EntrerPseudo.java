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
    
    private JTextField pseudo = new JTextField("AAA");
    private JButton valider ;
    private ImageIcon imagePseudo ;
    private ImageIcon imageOK ;
    private boolean disparu;
    private Dimension dimension ; 
    private int height;
    private int width;
    
   /* JPanel imagePanel = new JPanel(new BorderLayout()){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(imagePseudo.getImage(),0,0,this);
        }
    };*/
    
    public EntrerPseudo(){
        disparu=false;
        dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        height = (int)dimension.getHeight();
        width  = (int)dimension.getWidth();

        try{
            imagePseudo = new ImageIcon(Picture.chemin+"src/Images/pseudo.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'iamge");
        }
        try{
            imageOK = new ImageIcon(Picture.chemin+"src/Images/pseudo_ok.png");
        }catch(NullPointerException e){     
            System.out.println("Pas d'image");
        }
        constructionDialog();
    }
    
    
    private void constructionDialog(){
        this.setSize(400,200);
        this.setBackground(Color.black);
        Font police = new Font("Courier",Font.BOLD, 24);
        this.setLocation((width/3)*2,height/3);
        this.setLayout(new BorderLayout());
        pseudo.setFont(police);
        pseudo.setPreferredSize(new Dimension(400,25));
        pseudo.setBackground(Color.BLACK);
        pseudo.setForeground(Color.WHITE);
        pseudo.setHorizontalAlignment(JTextField.CENTER);
        
        //Font policeBouton = new Font("Courier",Font.PLAIN, 14);
        valider = new JButton();
        valider.setIcon(imageOK);
        valider.setBorderPainted(false);
        valider.setFocusPainted(false);
        
        
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

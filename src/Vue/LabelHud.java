/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

public class LabelHud extends JLabel{
    
    private ImageIcon img ;
    private String score ;
    
    public LabelHud(int type, String txt){
        super(txt) ;
        switch (type){
            case 1 :
                construction1P();
                break ;
            case 2 :
                constructionScore();
                break ;
            
            case 3 :
                constructionHI() ;
                break ;
        }
    }
    
    private void construction1P(){
        this.setVerticalTextPosition(CENTER) ;
        this.setHorizontalAlignment(LEFT);
        this.setHorizontalTextPosition(LEFT);
        this.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.CYAN);
        this.setOpaque(true);
    }
    
    private void constructionHI(){
        this.setVerticalTextPosition(CENTER) ;
        this.setHorizontalAlignment(LEFT);
        this.setHorizontalTextPosition(LEFT);
        this.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.YELLOW);
        this.setOpaque(true);
    }
    
    private void constructionScore(){
        this.setBackground(Color.BLACK);
        this.setVerticalTextPosition(CENTER) ;
        this.setHorizontalAlignment(RIGHT);
        this.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        
    }
    void setScore(String score){
        this.score = score ;
        repaint();
    }
    public void paintComponent(Graphics g, String score){
        g.setFont(new Font("Tahoma", Font.PLAIN, 20)) ;
        g.setColor(Color.WHITE);
        g.drawString(score, TOP, TOP);
    }
    
    
    
}

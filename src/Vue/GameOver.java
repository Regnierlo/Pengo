/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import javax.swing.JFrame;


public class GameOver extends JFrame{
    
    private Entry gameover;
    
    public GameOver(){
        
        this.setSize(400,750) ;
        this.setLocationRelativeTo(null) ;
        this.setResizable(false) ;
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE) ;
        
        try{
            gameover = new Entry("src/Images/game_over_2.png");
        }catch(NullPointerException e){
            
        }
        
        this.getContentPane().add(gameover);
        this.setVisible(true);
        
}
}

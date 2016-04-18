/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionJeux;

/**
 *
 * @author loisr
 */
public class Score {
    private int score;
    
    public Score(){
        score = 0;
    }
    
    public void pointBlocSpeciaux(boolean contreMur){
        if(contreMur)
            score += 5000;
        else
            score += 10000;
    }
    
    public void pointSnobeesEcrase(){
        score += 400;
    }
    
    public void pointSnobeesAttrape(){
        score += 100;
    }
    
    public void pointFinNiveau(int s, String name){
        if(s < 20)
            score += 5000;
        else if(s >= 20 && s <30)
            score += 2000;
        else if(s >= 30 && s <40)
            score += 1000;
        else if(s >=40 && s <50)
            score += 500;
        else if(s >=50 && s<60)
            score+=10;
        
        writeScore(name);
    }
    
    private void writeScore(String n){
        try {
            
        } catch (Exception e) {
        }
    }
    
    public int getScore(){
        return score;
    }
}

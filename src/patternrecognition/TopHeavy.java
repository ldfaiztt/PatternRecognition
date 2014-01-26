/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternrecognition;

/**
 *
 * @author Lucien
 */
public class TopHeavy extends Classifier{
    private int topHeavy;
    TopHeavy(){
        
    }
    public void addSets(TrainingData d1, TrainingData d2){
        data1 = d1;
        data2 = d2;
    }
    public void processTrainingData(){
        
    }
    public void classifyData(Letter data){
        
    }
    private int topHeavy(Letter data){
        return 0;
    }
    private int topArea(Letter data){
        int midpoint = data.height/2;
        
        return 0;
    }
    private int bottomArea(Letter data){
        int midpoint = data.height/2;
        
        return 0;
    }
    private int blackArea(Letter data){
        
        return 0;
    }
    
}

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
        int classifier = 0;
        classifier = (int)((100.0*topArea(data))/bottomArea(data)+0.5);
        return classifier;
    }
    private int topArea(Letter data){
        int black = 0;
        int midpoint = data.height/2;
        for(int i = 0; i < midpoint; i++){
            for(int j = 0; j < data.width; j++){
                black += data.image[i][j];
            }
        }
        return black;
    }
    private int bottomArea(Letter data){
        int black = 0;
        int midpoint = data.height/2;
        for(int i = midpoint; i < data.height; i++){
            for(int j = 0; j < data.width; j++){
                black += data.image[i][j];
            }
        }
        return black;
    }
    public int blackArea(Letter data){
        int black = 0;
        for(int i = 0; i < data.height; i++){
            for(int j = 0; j < data.width; j++){
                black += data.image[i][j];
            }
        }
        return black;
    }
    
}

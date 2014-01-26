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
public abstract class Classifier{
    Dataset data;
    Histogram hist1;
    Histogram hist2;
    Classifier(){
    
    }
    Classifier(Dataset d){
        data = d;
    }
    public void processTrainingData(){
       hist1 = new Histogram();
       hist2 = new Histogram();
    }
    public boolean classifyData(Letter data){
        return false;
    }
}

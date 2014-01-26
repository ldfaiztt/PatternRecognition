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
    TrainingData data1;
    TrainingData data2;
    Histogram hist1;
    Histogram hist2;
    Classifier(){
    
    }
    Classifier(TrainingData d1, TrainingData d2){
        data1 = d1;
        data2 = d2;
    }
    public void processTrainingData(){
       hist1 = new Histogram();
       hist2 = new Histogram();
    }
}

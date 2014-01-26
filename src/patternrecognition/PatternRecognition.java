package patternrecognition;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.*;
/**
 *
 * @author Lucien
 */
public class PatternRecognition {

    private static final int numTrainingSets = 1;
    public static void main(String[] args) {
        //partA();
        //partB();
        partC();
    }
    public static void partA(){
        String[] dataSetNames = {"data/HW2_data_c_", "data/HW2_data_e_"};
        Dataset[] datasets = new Dataset[10];
        try{
             //trainingSets = new Dataset(dataSetNames, numTrainingSets, 1);
             for(int i = 1; i <= 10; i++){
                 datasets[i-1] = new Dataset(dataSetNames, 1, i);
             }
        }catch(InvalidImageFormatException e){
            System.out.println("Image format error: please file formatting.");
            System.exit(1);
        }catch(IOException e){
            System.out.println("File read error: file does not exist, has insufficient permissions, or had unexpected difficulties while reading.");
            System.exit(2);
        }
        TopHeavy classify = new TopHeavy(datasets[0]);
        
        classify.processTrainingData();
        TopHeavyError err = classify.calculateThreshold();
        System.out.println("Part A Training Threshold: "+classify.getThreshold());
        System.out.format("Threshold Set Error: %.4f; c:%d, e:%d out of %d total. \n", err.errorRate, err.hist1error,err.hist2error, err.totalSamples);
        double mean=0;
        double [] errors = new double[9];
        for(int i = 1; i < 10; i++){
            int cErrors = 0;
            int eErrors = 0;
            for(Letter a : datasets[i].letterSets.get(0)){
                if(classify.classifyData(a)){
                    cErrors++;
                }
            }
            for(Letter a : datasets[i].letterSets.get(1)){
                if(!classify.classifyData(a)){
                    eErrors++;
                }
            }
            errors[i-1]=((double)cErrors+(double)eErrors)/(datasets[i].letterSets.get(0).size()+datasets[i].letterSets.get(1).size());
            mean += errors[i-1];
            System.out.println("Test Data pair: "+i+" Error:"+errors[i-1]+" C:"+cErrors+" E:"+eErrors+" tot:"+(cErrors+eErrors));   
        }
        mean /= 9.0;
        double sumOfSquaredDifferences=0;
        for(int i = 0; i < 9; i++){
            sumOfSquaredDifferences += java.lang.Math.pow(errors[i]-mean, 2);
        }
        double standardError = java.lang.Math.sqrt(sumOfSquaredDifferences/(9-1));
        System.out.format("Test Mean error: %.4f, Standard error: %.4f\n\n", mean, standardError);
        
        
    }
    
    public static void partB(){
        String[] dataSetNames = {"data/HW2_data_c_", "data/HW2_data_e_"};
        Dataset trainingData = null;
        Dataset testData = null;
        try{
            trainingData = new Dataset(dataSetNames,5,1);
            testData = new Dataset(dataSetNames,5,6);
        }catch(Exception e){
            System.out.println("Error in part b");
        }
        
        TopHeavy classify = new TopHeavy(trainingData);
        classify.processTrainingData();
        TopHeavyError err = classify.calculateThreshold();
        System.out.println("Part B Training Threshold: "+classify.getThreshold());
        System.out.format("Threshold Set Error: %.4f; c:%d, e:%d out of %d total. \n", err.errorRate, err.hist1error,err.hist2error, err.totalSamples);
        double errors = 0;
        int cErrors = 0;
            int eErrors = 0;
            for(Letter a : testData.letterSets.get(0)){
                if(classify.classifyData(a)){
                    cErrors++;
                }
            }
            for(Letter a : testData.letterSets.get(1)){
                if(!classify.classifyData(a)){
                    eErrors++;
                }
            }
            errors=((double)cErrors+(double)eErrors)/(testData.letterSets.get(0).size()+testData.letterSets.get(1).size());
            System.out.format("Test Set Error: %.4f C: %d E:%d tot:%d\n\n", errors, cErrors, eErrors, (cErrors+eErrors));
    }
    public static void partC(){
        String[] dataSetNames = {"data/HW2_data_c_", "data/HW2_data_e_"};
        Dataset[] datasets = new Dataset[10];
        Dataset[] testsets = new Dataset[10];
        for(int i = 1; i <= 10; i++){
            datasets[i-1] = new Dataset();
            testsets[i-1] = new Dataset();
            for(int j = 1; j <= 10; j++){
                if(i!=j){
                    String[] tempSetName = new String[2];

                    tempSetName[0]=dataSetNames[0]+j;
                    tempSetName[1]=dataSetNames[1]+j;
                    try{
                        datasets[i-1].addSet(tempSetName);
                    }catch(Exception e){
                        System.out.println("Error adding to set "+i);
                    }
                }
                else{
                    String[] tempSetName = new String[2];
                    tempSetName[0]=dataSetNames[0]+i;
                    tempSetName[1]=dataSetNames[1]+i;
                    try{
                        testsets[i-1].addSet(tempSetName);
                    }catch(Exception e){
                        System.out.println("Error adding to error set "+i);
                    }
                }
            }
        }
        TopHeavy [] classifiers = new TopHeavy[10];
        TopHeavyError [] classErrors = new TopHeavyError[10];
        for(int i = 0; i < 10; i++){
            classifiers[i] = new TopHeavy(datasets[i]);
            classifiers[i].processTrainingData();
            classErrors[i] = classifiers[i].calculateThreshold();
        }
        System.out.println("Part C training thresholds:");
        for(int i = 0; i < 10; i++){
            System.out.print("Set "+(1+i)+": Threshold: "+classifiers[i].getThreshold()+ " ");
            System.out.format("Threshold Set Error: %.4f; c:%d, e:%d out of %d total. \n", 
                    classErrors[i].errorRate, classErrors[i].hist1error,classErrors[i].hist2error, classErrors[i].totalSamples);
        }
        double mean = 0;
        double errors[] = new double[10];
        for(int i = 0; i < 10; i++){
            int cErrors = 0;
            int eErrors = 0;
            for(Letter a : testsets[i].letterSets.get(0)){
                if(classifiers[i].classifyData(a)){
                    cErrors++;
                }
            }
            for(Letter a : testsets[i].letterSets.get(1)){
                if(!classifiers[i].classifyData(a)){
                    eErrors++;
                }
            }
            errors[i]=((double)cErrors+(double)eErrors)/(400.0);
            mean += errors[i];
            
            System.out.format("Set %d- Error: %.4f C:%d E:%d tot:%d\n",(i+1),errors[i],cErrors,eErrors,(cErrors+eErrors));
        }
        mean /=10;
        double sumOfSquaredDifferences=0;
        for(int i = 0; i < 10; i++){
            sumOfSquaredDifferences += java.lang.Math.pow(errors[i]-mean, 2);
        }
        double standardError = java.lang.Math.sqrt(sumOfSquaredDifferences/(10-1));
        System.out.format("Test Mean error: %.4f, Standard error: %.4f\n\n", mean, standardError);
    }
}

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
        partA();
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
        TopHeavy eAndC = new TopHeavy(datasets[0]);
        
        eAndC.processTrainingData();
        TopHeavyError err = eAndC.calculateThreshold();
        System.out.println("Training Threshold: "+eAndC.getThreshold());
        System.out.format("Error: %.4f; c:%d, e:%d out of %d total. \n", err.errorRate, err.hist1error,err.hist2error, err.totalSamples);
        double mean=0;
        double [] errors = new double[9];
        for(int i = 1; i < 10; i++){
            int cErrors = 0;
            int eErrors = 0;
            for(Letter a : datasets[i].dataSets.get(0)){
                if(eAndC.classifyData(a)){
                    cErrors++;
                }
            }
            for(Letter a : datasets[i].dataSets.get(1)){
                if(!eAndC.classifyData(a)){
                    eErrors++;
                }
            }
            errors[i-1]=((double)cErrors+(double)eErrors)/(datasets[i].dataSets.get(0).size()+datasets[i].dataSets.get(1).size());
            mean += errors[i-1];
            System.out.println("Data pair: "+i+" Error:"+errors[i-1]+" C:"+cErrors+" E:"+eErrors+" tot:"+(cErrors+eErrors));   
        }
        mean /= 9.0;
        double sumOfSquaredDifferences=0;
        for(int i = 0; i < 9; i++){
            sumOfSquaredDifferences += java.lang.Math.pow(errors[i]-mean, 2);
        }
        double standardError = java.lang.Math.sqrt(sumOfSquaredDifferences/(9-1));
        System.out.format("Mean error: %.4f, Standard error: %.4f\n", mean, standardError);
        
        
    }
    
    
}

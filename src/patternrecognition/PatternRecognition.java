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
//        BufferedReader Ereader = new BufferedReader(new FileReader("HW1_e.txt"));
//        BufferedReader Creader = new BufferedReader(new FileReader("HW1_C.txt"));
//        ArrayList<Letter> eList = readBuffer(Ereader);
//        ArrayList<Letter> cList = readBuffer(Creader);
//        Histogram eHist = new Histogram();
//        Histogram cHist = new Histogram();
//        String trainingDataSet1="data/HW2_data_c_";
//        String trainingDataSet2="data/HW2_data_e_";
        String[] trainingDataSets = {"data/HW2_data_c_", "data/HW2_data_e_"};
//        trainingDataSets[0] = trainingDataSet1;
//        trainingDataSets[1] = trainingDataSet2;
        Dataset trainingSets=null;
        try{
             trainingSets = new Dataset(trainingDataSets, numTrainingSets, 1);
        }catch(InvalidImageFormatException e){
            System.out.println("Image format error: please file formatting.");
            System.exit(1);
        }catch(IOException e){
            System.out.println("File read error: file does not exist, has insufficient permissions, or had unexpected difficulties while reading.");
            System.exit(2);
        }
        TopHeavy eAndC = new TopHeavy(trainingSets);
        
        eAndC.processTrainingData();
        TopHeavyError err = eAndC.calculateThreshold();
        System.out.format("Error: %.4f%%; c:%d, e:%d out of %d total. \n", err.errorRate, err.hist1error,err.hist2error, err.totalSamples);
        
    
        
    }
    
}

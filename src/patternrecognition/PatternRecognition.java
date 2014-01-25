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
        String trainingDataSet1="HW2_data_c_";
        String trainingDataSet2="HW2_data_e_";
        String[] trainingDataSets = new String[2];
        trainingDataSets[0] = trainingDataSet1;
        trainingDataSets[1] = trainingDataSet2;
        TrainingData trainingSets=null;
        try{
             trainingSets = new TrainingData(trainingDataSets, numTrainingSets);
        }catch(InvalidImageFormatException e){
            System.out.println("Image format error: please file formatting.");
            System.exit(1);
        }catch(IOException e){
            System.out.println("File read error: file does not exist, has insufficient permissions, or had unexpected difficulties while reading.");
            System.exit(2);
        }
        
    
        
    }
    
}

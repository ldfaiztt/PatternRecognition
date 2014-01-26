/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternrecognition;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
/**
 *
 * @author Lucien
 */
public class Dataset {
    //outer arraylist contains the arraylists containing all of one type of letter
    public ArrayList<ArrayList<Letter>> dataSets;
    //filenames are in an array, for expansion later on to more types
    public Dataset(String[] trainingSetFilenames, int numTrainingSets, int beginningSet) throws InvalidImageFormatException, IOException{ 
        dataSets = new ArrayList<>();
        //create array
        for(int i = 0; i < trainingSetFilenames.length; i++){
            //new letter list for each classification of letter
            dataSets.add(new ArrayList<Letter>());
            for(int j = 0; j < numTrainingSets; j++){
                BufferedReader currentSet = new BufferedReader(new FileReader(trainingSetFilenames[i] + (j+beginningSet) + ".txt" ));
                dataSets.get(i).addAll(readBuffer(currentSet)); //concatenate all in list from readbuffer into correct trainingset
            }
        }
    }
    public static ArrayList<Letter> readBuffer(BufferedReader reader) throws InvalidImageFormatException, IOException{
        String line;
        ArrayList<Letter> letters = new ArrayList();
        boolean firstline = true;
        while((line=reader.readLine())!=null){
            if(firstline){
                firstline=false;
                String splitted[] = line.split(" ");
                int dimensions[] = new int[3];
                for(int i = 0; i < 3; i ++){
                    dimensions[i] = Integer.parseInt(splitted[i+1].substring(1));
                }
                Letter temp = new Letter(dimensions[0], dimensions[1], dimensions[2]);
                for(int i = 0; i < temp.height; i++){
                    line = reader.readLine();
                    if(line != null && line.length() == temp.width){
                        for(int j = 0; j < temp.width; j++){
                            if(line.charAt(j) == 'x')
                                temp.image[i][j] = 1;
                            else
                                temp.image[i][j] = 0;
                        }
                    }
                    else{
                        throw new InvalidImageFormatException("Invalid image size from input file");
                    }
                }
                letters.add(temp);
                firstline = true;
            }
        }
        System.out.println("Read in "+letters.size()+" letter images.");
        return letters;
    }
    
}
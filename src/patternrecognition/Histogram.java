package patternrecognition;
import java.util.HashMap;
/**
 *
 * @author Lucien
 */
public class Histogram {
    public HashMap<Integer, Integer> counts;
    private Integer lowestTally;
    private Integer highestTally;
    private int totalTallies=0;
    public Histogram(){
        counts = new HashMap();
    }
    public void tally(int number){
        if(counts.containsKey(number)){
            counts.put(number, counts.get(number).intValue() +1);
        }
        else{
            counts.put(number, 1);
        }
        if(lowestTally != null){
            if(number<lowestTally.intValue()){
                lowestTally = number;
            }
        }
        else{
            lowestTally=new Integer(number);
        }
        if(highestTally != null){
            if(number>highestTally.intValue()){
                highestTally = number;
            }
        }
        else{
            highestTally=new Integer(number);
        }
        totalTallies++;
    }
    public int getLowestTally(){
        return lowestTally;
    }
    public int getHighestTally(){
        return highestTally;
    }
    public int size(){
        return counts.size();
    }
    public int getTotalTallies(){
        return totalTallies;
    }
}

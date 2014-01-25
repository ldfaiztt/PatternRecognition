package patternrecognition;
import java.util.ArrayList;
/**
 *
 * @author Lucien
 */
public class Histogram {
    ArrayList<Integer> counts;
    public Histogram(){
        counts = new ArrayList<Integer>();
    }
    public void tally(int number){
        if(number > counts.size() - 1){
            while(counts.size() < number+1)
                counts.add(new Integer(0));
        }
        Integer temp = new Integer(counts.get(number).intValue()+1);
        counts.set(number, temp);
    }
}


package patternrecognition;
import java.util.Set;
import java.lang.Math;
/**
 *
 * @author Lucien
 */
public class TopHeavy extends Classifier{
    private int threshold=-1;
    private boolean thresholdDirection = false;
    
    TopHeavy(Dataset d){
        data = d;
    }
    public void addSets(Dataset d){
        data = d;
    }
    @Override
    public void processTrainingData(){
        //first set (c's)
        hist1 = new Histogram();
        for(int i = 0; i < data.letterSets.get(0).size(); i++){
            hist1.tally(topHeavy(data.letterSets.get(0).get(i)));
        }
        hist2 = new Histogram();
        //second set (e's)
        for(int i = 0; i < data.letterSets.get(1).size(); i++){
            hist2.tally(topHeavy(data.letterSets.get(1).get(i)));
        }
    }
    public TopHeavyError calculateThreshold(){
        int low = hist1.getLowestTally();
        int low2 = hist2.getLowestTally();
        low = (low<low2)?low:low2;
        int high = hist1.size();
        int high2 = hist2.size();
        high = (high<high2)?high:high2;
        int currentPos=0;
        int currentHist1 = hist1.getTotalTallies();
        int currentHist2 = hist2.getTotalTallies();
        int currentTotal = currentHist1+currentHist2;
        boolean toggle = false;
        for(int i = low; i < high; i ++){
            int hist1a=0, hist1b=0, hist2a=0, hist2b=0;
            boolean direction = toggle;
            Set<Integer> hist1set = hist1.counts.keySet();
            for(Integer vals : hist1set){
                if(vals.intValue() < i){
                    hist1a+=hist1.counts.get(vals);
                }
                else if(vals.intValue() >= i){
                    hist1b+=hist1.counts.get(vals);
                }
            }
            Set<Integer> hist2set = hist2.counts.keySet();
            for(Integer vals : hist2set){
                if(vals.intValue() < i){
                    hist2a+=hist2.counts.get(vals);
                }
                else if(vals.intValue() >= i){
                    hist2b+=hist2.counts.get(vals);
                }
            }
            // hist2 below threshold, hist1 above
            if(hist1a+hist2b < currentTotal){
                toggle = false;
                currentHist1 = hist1a;
                currentHist2 = hist2b;
                currentTotal = currentHist1+currentHist2;
                currentPos = i;
            }
            if(hist1b + hist2a < currentTotal){
                toggle = true;
                currentHist1 = hist1b;
                currentHist2 = hist2a;
                currentTotal = currentHist1+currentHist2;
                currentPos = i;
            }
        }
        thresholdDirection = toggle;
        threshold = currentPos;
        double errorRate = ((double)currentTotal/(hist1.getTotalTallies()+hist2.getTotalTallies()));
        TopHeavyError returns = new TopHeavyError(errorRate, currentHist1, currentHist2, hist1.getTotalTallies()+hist2.getTotalTallies());
        return returns;
    }
    @Override
    public boolean classifyData(Letter data){
        if(thresholdDirection){
            return topHeavy(data)>=threshold;
        }else{
            return topHeavy(data)<threshold;
        }
        
    }
    public int getThreshold(){
        return threshold;
    }
    private int topHeavy(Letter data){
        int classifier;
        classifier = (int)Math.floor(((100.0*topArea(data))/bottomArea(data)+0.5));
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
        return topArea(data)-blackArea(data);
    }
    private int blackArea(Letter data){
        int black = 0;
        for(int i = 0; i < data.height; i++){
            for(int j = 0; j < data.width; j++){
                black += data.image[i][j];
            }
        }
        return black;
    }

}

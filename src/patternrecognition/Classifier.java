

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

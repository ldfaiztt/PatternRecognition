

package patternrecognition;

/**
 *
 * @author Lucien
 */
public abstract class ThresholdError {
    public double errorRate;
    public ThresholdError(){
        errorRate = 0;
    }
    public ThresholdError(double error){
        errorRate = error;
    }
}

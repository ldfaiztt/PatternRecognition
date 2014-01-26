

package patternrecognition;

/**
 *
 * @author Lucien
 */
public class TopHeavyError extends ThresholdError{
    int hist1error;
    int hist2error;
    int totalSamples;
    public TopHeavyError(double a, int b, int c,int d){
        errorRate = a;
        hist1error = b;
        hist2error = c;
        totalSamples=d;
    }
}

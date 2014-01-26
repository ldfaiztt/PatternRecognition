package patternrecognition;

/**
 *
 * @author Lucien
 */
public class Letter{
    public final int height;
    public final int width;
    public final int baseline;
    public int image[][];
    public int blackArea;
    public Letter(int h, int w, int b){
        height = h;
        width = w;
        baseline = b;
        image = new int[h][];
        for(int i = 0; i < h; i++){
            image[i]=new int[w];
        }
    }
    
}

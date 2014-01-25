package patternrecognition;

/**
 *
 * @author Lucien
 */
public class InvalidImageFormatException extends Exception{
    public InvalidImageFormatException(){}
    public InvalidImageFormatException(String message){
        super(message);
    }
}

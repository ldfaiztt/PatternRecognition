/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitsonair1;

import java.util.*;
/**
 *
 * @author rh_2
 */
public class Channel1 {
    
    public Channel1() {  // Konstruktor
        System.out.println("Channel1");
    }

        /**
     * Methode zur Berechnung der Bitfehlerrate
     * @param encodedBitsequence
     * @param q
     * @return 
     */
    public boolean[] bsc_channel(boolean[] encodedBitsequence, double q){
        boolean[] channelBitsequence = null;
        if( (null != encodedBitsequence) && (0.0 <= q) && (1.0 >= q)) {
            int N = encodedBitsequence.length;
            channelBitsequence = new boolean[N];
            // laufe über alle Bits der Bitsequence
            for( int k=0; k<N; k++) {
                if( Math.random() < q ) {
                    channelBitsequence[k]=!encodedBitsequence[k];  // Negation mit WK q
                } else {
                    channelBitsequence[k]=encodedBitsequence[k];   // Identitaet mit WK 1-q
                }
            }
        } else { 
        }
        return(channelBitsequence);
    }
     
    public double[] awgn_channel(double[] dataIn, double sigma){
        double[] dataOut = null;
        if( (null != dataIn) && (0.0 <= sigma) && (1.0 >= sigma)) {
            int N = dataIn.length;
            dataOut = new double[N];
            Random rand = new Random();
            // laufe über alle Bits der Bitsequence
            for( int k=0; k<N; k++) {
                dataOut[k] = dataIn[k] + sigma*rand.nextGaussian();
            }
        } else { 
        }
        return(dataOut);
    }


}

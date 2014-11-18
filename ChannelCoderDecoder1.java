/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitsonair1;

/**
 *
 * @author rh_2
 */
public class ChannelCoderDecoder1 {
    
    public ChannelCoderDecoder1() {  // Konstruktor
        System.out.println("ChannelCoderDecoder1");
    }

    /**
     * Methode zur Kanalcodierung durch Repetition-Code (Wiederholung der Bits)
     * @param bitsequence
     * @param numreps
     * @return 
     */
    public boolean[] encode_repetitioncode(boolean[] bitsequence, int numreps) {
        boolean[] encodedBitsequence = new boolean[numreps*bitsequence.length];
        int i=0;
        // laufe über alle Bits der Bitsequence
        for(int k=0; k<bitsequence.length; k++) {
            for(int l=0; l<numreps; l++) {
                encodedBitsequence[i++]=bitsequence[k]; // wiederhole numreps mal den Wert
            }         
        }
        return(encodedBitsequence);
    }

    /**
     * Methode zur Kanaldecodierung durch Repetition-DeCode (Mehrheitsentscheid)
     * @param bitsequence
     * @param numreps
     * @return 
     */
    public boolean[] decode_repetitioncode(boolean[] bitsequence, int numreps) {
        boolean[] decodedBitsequence = null;
        if(null != bitsequence) {
            decodedBitsequence = new boolean[bitsequence.length/numreps];
            // laufe über alle Bits der Bitsequence
            for(int i=0,k=0; k<decodedBitsequence.length; k++) {
                int val = 0;
                for(int l=0; l<numreps; l++) {
                    if(true == bitsequence[i++]) {
                        val +=1;
                    } else { 
                    }
                }   // val = Anzahl true im Block der Laenge numreps
                if(numreps < (val<<1)) {
                    decodedBitsequence[k] = true; // falls mehr als numreps/2 mal true
                } else { 
                    decodedBitsequence[k] = false; // sonst mindestens numreps/2 mal false
                }     
            }
        } else { 
        }
        return(decodedBitsequence);
    }
    


}

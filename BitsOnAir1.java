/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitsonair1;

//import java.util.*;

/**
 * The class BitsOnAir1 implements an application that
 * simply generates and executes a communication.
 * 
 * @author rh
 */
public class BitsOnAir1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // hier geht's los
            System.out.println("Bits on Air!"); //Display the string.
            
            // generiere die zu sendende Bitsequence in der Quelle
            Source1 source = new Source1(1000,0.5);
            System.out.println("generatedBitsequence");
            printBitsequence(source.getBS());
            
            int numreps = 3;
            ChannelCoderDecoder1 channelCodec = new ChannelCoderDecoder1();
            // codiere durch einfachen Kanalcoder (Repetition)
            boolean[] encodedBitsequence = channelCodec.encode_repetitioncode(source.getBS(), numreps);
            System.out.println("encodedBitsequence");
            printBitsequence(encodedBitsequence);
            
            //// uebertrage die Bitsequence ueber einen binaeren symmetrischen Kanal
            //Channel1 channel = new Channel1();
            //            
            //boolean[] channelBitsequence = channel.bsc_channel(encodedBitsequence,0.2);
            //System.out.println("channelBitsequence.length="+String.valueOf(channelBitsequence.length));
            //printBitsequence(channelBitsequence);
                
            double A = 0.7;  // Amplitude
            double timeSymbol = 0.0008; // 800us Symboldauer ( 1250 bit/s)
            double time0 = 0.0002;  // 200us Periode fuer 0  (  5 kHz  )
            double time1 = 0.00008; //  80us Periode fuer 1  ( 12.5kHz )
            double sampleRate = 44100;     // Abtastfrequenz ( 44.1kHz )
                
            Modulator1 modulator = new Modulator1();
            double[] modulatedSamples = modulator.modulate_FSK(encodedBitsequence, A, timeSymbol, time0, time1, sampleRate );
              
            // uebertrage die modulierten Daten ueber einen AWGN-Kanal
            Channel1 channel = new Channel1();
            double[] receivedSamples = channel.awgn_channel(modulatedSamples,0.5); 
               
            WavWriter1 wavWriter = new WavWriter1();
            wavWriter.writeToWav("testFile004a.wav", modulatedSamples, sampleRate );
            wavWriter.writeToWav("testFile004b.wav", receivedSamples, sampleRate );
            
            boolean[] channelBitsequence = modulator.demodulate_FSK(receivedSamples, timeSymbol, time0, time1, sampleRate );
            System.out.println("demodulatedBitsequence.length="+String.valueOf(channelBitsequence.length));
            printBitsequence(channelBitsequence);
            
            // decodiere durch Kanaldecoder (Repetition/Mehrheitsentscheidung)
            boolean[] reveivedBitsequence = channelCodec.decode_repetitioncode(channelBitsequence, numreps);
            
            // uebergebe die Bitsequence an die Senke
            Drain1 drain = new Drain1(reveivedBitsequence);    
            //drain.generateStatistics();
            System.out.println("decodedBitsequence");
            printBitsequence(drain.getBS());
            
            // Ausgabe der statistischen Auswertungen
            System.out.println("mue="+String.valueOf(drain.getMue()));
            System.out.println("sigma="+String.valueOf(drain.getSigma()));
            
            // Berechne die Bitfehlerrate
            double ber = calc_BER(source.getBS(), drain.getBS());
            System.out.println("BER="+String.valueOf(ber));
        } 
        catch (Exception e) {
            System.err.println(e);
        }
    }
    
    /**
     * Methode zur Berechnung der Bitfehlerrate (bit error rate)
     */
    static double calc_BER(boolean[] bitsequence1, boolean[] bitsequence2) {
        double retval = 0.0;
        if((null != bitsequence1) && (null != bitsequence2)) {
            int N = Math.min(bitsequence1.length,bitsequence2.length);
            if(0<N) { // falls mehr als ein Bit gesendet und empfangen wurde
                int sum = 0;
                // laufe über alle Bits der Bitsequence
                for( int k=0; k<N; k++) {
                    if(bitsequence1[k] != bitsequence2[k]) {
                        sum += 1;
                    } else {
                    }
                }  // sum = Anzahl der Fehlerbits
                retval = (double)sum/(double)N; 
            } else { 
            }
        } else { 
        }
        return(retval);
    }
    
    
    /**
     * Methode zur Ausgabe der Bitsequence
     */
    static void printBitsequence(boolean[] bs) {
        if(null != bs) {
            // laufe über alle Bits der Bitsequence
            for(int k=0; k<bs.length; k++) {
                System.out.print( bs[k] ? 1 : 0 );
                
                if(99==(k%100)) {  // zusaetzlicher Zeilenumbruch nach 80 bits
                    System.out.println("");
                } else {
                }
            }
            System.out.println("");
        }
    }    
}




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
public class Modulator1 {
    
    public Modulator1() {  // Konstruktor
        System.out.println("Modulator1");
    }

    public double[] modulate_FSK(boolean[] bitsequence, double A, double Tsymbol, double T0, double T1, double fA ) {
        return(modulate_FSK(bitsequence,A,A,Tsymbol,T0,T1,fA));
    }

    /**
     * Methode zur Modulation der Bitsequence durch 'Frequency Shift Keying'
     * @param bitsequence Bitfolge, die in ein FSK-Signal umzusetzen ist
     * @param A0 Amplitude einer 0
     * @param A1 Amplitude einer 1
     * @param Tsymbol Dauer eines Symbols
     * @param T0 Periode bei einer 0
     * @param T1 Periode bei einer 1
     * @param fA Abtastfrequenz des generierten Signals
     * @return 
     */
    public double[] modulate_FSK(boolean[] bitsequence, double A0, double A1, double Tsymbol, double T0, double T1, double fA ) {
        double[] y = null;
        if(null != bitsequence) {
            int N = bitsequence.length;
            double D = Math.floor(fA*Tsymbol); // 'Anzahl'(mit Nachkommastellen) Abtastwerte pro Symbol
            //int M = (int) Math.ceil(((double)N)*D);
            //y = new double[M];
            y = new double[(int) Math.ceil(((double)N)*fA*Tsymbol)];
            // laufe über alle Abtastwerte
            for(int n=0; n<N; n++) {
                double T,A;
                if(true == bitsequence[n]) {
                    T = T1;
                    A = A1;
                } else {
                    T = T0;
                    A = A0;
                }
                for(int k=(int)Math.floor(n*D); k<(int)Math.floor((n+1)*D); k++) {
                    y[k] = A*Math.sin(2*Math.PI*k/(T*fA));
                }
            }
            System.out.println("modulate_fsk");
        }
        return(y);
    }
    
    public boolean[] demodulate_FSK(double[] data, double Tsymbol, double T0, double T1, double fA ) {
        boolean[] bs = null;
        if(null != data) {
            double D = Math.floor(fA*Tsymbol); // Anzahl Abtastwerte pro Symbol
            int N = (int) Math.floor(((double)data.length)/(fA*Tsymbol));
            bs = new boolean[N];
            // laufe über alle erwarteten Bits
            for(int n=0; n<N; n++) {
                double sum1 = 0.0;
                double sum0 = 0.0;
                for(int k=(int)Math.floor(n*D); k<(int)Math.floor((n+1)*D); k++) {
                    // Korrelliere ueber die Symboldauer
                    sum1 = sum1 + data[k]*Math.sin(2*Math.PI*k/(T1*fA));
                    sum0 = sum0 + data[k]*Math.sin(2*Math.PI*k/(T0*fA));
                }
                bs[n] = (sum1 >= sum0) ? true : false;
            }
            System.out.println("demodulate_fsk");
        }
        return(bs);
    }
}

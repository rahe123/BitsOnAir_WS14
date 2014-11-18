/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitsonair1;


/**
 * Die Klasse Drain1 implementiert eine Senke ....
 * 
 * @author rh
 */
class Drain1 {
    
    //BitSet bs = new BitSet();
    boolean[] bs = null;
    double mue = 0.0;
    double sigma = 0.0;
      
    public Drain1(boolean[] bitsequence) {  // Konstruktor
        System.out.println("Drain1");
        bs = bitsequence;
        this.generateStatistics();
    }
    
    double getMue() {
        return(mue);
    }
    
    double getSigma() {
        return(sigma);
    }
       
    boolean[] getBS() {
        return bs;
    }
    
    final void generateStatistics() {
        mue=0.0; sigma=0.0;
        if(null != bs){
            double sum=0.0;
            int N=bs.length;
            // laufe über alle Bits der Bitsequence   
            for(int k=0; k<N; k++) {
                if( true == bs[k] ) {
                    sum += 1.0;
                } else {
                }        
            }
            if(0 <= N) {
                mue = sum/N;
            } else {
                mue = 0.0;
            }
            sum = 0.0;
            // laufe über alle Bits der Bitsequence
            for(int k=0; k<N; k++) {
                if( true == bs[k] ) {
                    sum += (1.0-mue)*(1.0-mue);
                } else {
                    sum += (0.0-mue)*(0.0-mue);
                }        
            }
            if(1 < N) {
                sigma = Math.sqrt(sum/(N-1));        
            } else {
                sigma = 0.0; 
            }
        }
    }  
}


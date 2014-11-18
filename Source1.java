/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitsonair1;

/**
 * Die Klasse Source1 implementiert eine Quelle ....
 * 
 * @author rh
 */
class Source1 {
    
    //BitSet bs = new BitSet();
    boolean[] bs = null;

    public Source1(int N, double p) {  // Konstruktor
        System.out.println("Source1");
        bs = new boolean[N];
        //generateNewBitsequence(p);
        // laufe über alle Bits der Bitsequence
        for(int k=0; k<N; k++) {
            bs[k]=Math.random()<p;
            //if( Math.random() < p ) {
            //    bs[k]=true;
            //} else {
            //    bs[k]=false;
            //}
        }
    }
    
    boolean[] getBS() {
        return bs;
    }
    
    void generateNewBitsequence(double p) {
        // laufe über alle Bits der Bitsequence
        for(int k=0; k<bs.length; k++) {
            bs[k]=Math.random()<p;
            //if( Math.random() < p ) {
            //    bs[k]=true;
            //} else {
            //    bs[k]=false; 
            //}
        }
    }
}

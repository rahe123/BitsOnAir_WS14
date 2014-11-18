/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitsonair1;

import java.io.*;

/**
 *
 * @author rh_2
 */
public class WavWriter1 {

    public WavWriter1() {  // Konstruktor
        System.out.println("WavWriter1");
    }

    public void writeToWav(String fileName, double[] sequence, double sampleRate ) {
        try {
            // determine the number of frames
            int numFrames = sequence.length;
            
            // Create a wav file with the name specified as the first argument
            WavFile wavFile = WavFile.newWavFile(new File(fileName), 1, numFrames, 16, (long)sampleRate);
            
            // Create a buffer of 100 frames
            //double[][] buffer = new double[2][100];
            double[] buffer = new double[100];
            
            // Initialise a local frame counter
            int frameCounter = 0;
            
            // Loop until all frames written
            while (frameCounter < numFrames) {
                // Determine how many frames to write, up to a maximum of the buffer size
                long remaining = wavFile.getFramesRemaining();
                int toWrite = (remaining > 100) ? 100 : (int) remaining;
                
                // Fill the buffer, one tone per channel
                for (int k=0 ; k<toWrite ; k++, frameCounter++) {
                    //buffer[1][k] = Math.sin(2.0 * Math.PI * 400 * frameCounter / sampleRate);
                    //buffer[2][k] = Math.sin(2.0 * Math.PI * 500 * frameCounter / sampleRate);
                    buffer[k] = sequence[frameCounter];
                }
                // Write the buffer
                wavFile.writeFrames(buffer, toWrite);
            }
            // Close the wavFile
            wavFile.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}

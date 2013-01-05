package cpoo.noise.model;

import java.util.Random;

/** 
Generate pseudo-random floating point values, with an 
approximately Gaussian (normal) distribution.

Many physical measurements have an approximately Gaussian 
distribution; this provides a way of simulating such values. 
*/
public final class RandomGaussian {
    
  private Random fRandom = new Random();
  
  public double getGaussian(double aMean, double aVariance){
      return aMean + fRandom.nextGaussian() * aVariance;
  }
} 
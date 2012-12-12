package Canny;

import java.awt.image.BufferedImage;


public class Canny
{
	private CannyEdgeDetector instance;
	
	public Canny(float low_treshold, float high_treshold)
	{
		instance = new CannyEdgeDetector();
		instance.setLowThreshold(low_treshold);
		instance.setHighThreshold(high_treshold);
	}
	
	public BufferedImage process(BufferedImage source)
	{
		instance.setSourceImage(source);
		instance.process();
		BufferedImage edges = instance.getEdgesImage();
		return edges;
	}
}
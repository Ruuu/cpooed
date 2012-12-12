package Sobel;

import java.awt.image.BufferedImage;


public class Sobel
{
	private SobelEdgeProcessor instance;
	
	public Sobel()
	{
		instance = new SobelEdgeProcessor();
	}
	
	public BufferedImage process(BufferedImage source)
	{
		BufferedImage edges = instance.sobelEdgeDetection(source);
		return edges;
	}
}
package OurAlgorithm;

import java.awt.image.BufferedImage;


public class OurAlgorithm
{
	static float stdevTreshold = 15;
	static int stdevRange = 1;
	private OurAlgorithmEdgeProcessor instance;
		
	
	public OurAlgorithm() {
		this.instance = new OurAlgorithmEdgeProcessor();
	}

	
	public BufferedImage process(BufferedImage source)
	{
		BufferedImage edges = instance.ourAlgorithmEdgeDetection(source, stdevTreshold, stdevRange);
		return edges;
	}
	
	
	

	static public float getStdevTreshold() {
		return stdevTreshold;
	}

	static public void setStdevTreshold(float varianceTreshold) {
		OurAlgorithm.stdevTreshold = varianceTreshold;
	}

	static public int getStdevRange() {
		return stdevRange;
	}

	static public void setStdevRange(int varianceRange) {
		OurAlgorithm.stdevRange = varianceRange;
	}

	
	
	
}
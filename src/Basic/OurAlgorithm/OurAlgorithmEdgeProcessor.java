package OurAlgorithm;

import java.awt.Color;
import java.awt.image.*;

public class OurAlgorithmEdgeProcessor {

	//gauss 3x3
	private static int[][] gauss = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };
	
	//gauss 5x5
	private static int[][] gauss2 = { {0, 1, 2, 1, 0}, 
									{1, 4, 8, 4, 1}, 
									{2, 8, 16, 8, 2}, 
									{1, 4, 8, 4, 1}, 
									{0, 1, 2, 1, 0} };
	//laplasjan 3x3
	private static int[][] laplacian = { { 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };
	
	//inny laplasjan 3x3
	private static int[][] laplacian2 = { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };
	
	//laplasjan 5x5 - eksperymentalnie dobrany 
	private static int[][] laplacianExp = { {1, 1, 2, 1, 1}, 
											{1, 1, 4, 1, 1}, 
											{2, 4, -40, 4, 2}, 
											{1, 1, 4, 1, 1}, 
											{1, 1, 2, 1, 1} };
	
	public OurAlgorithmEdgeProcessor() {}

	public static int rgbToLuminance(int rgb) {
		int r = (rgb & 0xff0000) >> 16;
		int g = (rgb & 0xff00) >> 8;
		int b = (rgb & 0xff);
		return (r+g+b)/3;
	}

	public static int levelToGrayscale(int level) {
		return (level << 16) + (level << 8) + level;
	}

	public static BufferedImage makeEmptyGrayImageFromImage(BufferedImage image) {
		return new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
	}

	static BufferedImage deepImageCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}


	/**
	 * Filtr medianowy 
	 * 
	 * @param image
	 * @param windowSize
	 * @return
	 */
	public static BufferedImage medianFilter(BufferedImage image, int windowSize) {
		BufferedImage ret =deepImageCopy(image);
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		int middle = (windowSize * windowSize) / 2;
		for (int x = 0; x < w - windowSize; ++x) {
			for (int y = 0; y < h - windowSize; ++y) {
				int pixels[] = new int[windowSize * windowSize];
				for (int i = 0; i < windowSize; ++i) {
					for (int j = 0; j < windowSize; ++j) {
						pixels[i * windowSize + j] = image.getRGB(x + i, y + j);
					}
				}
				java.util.Arrays.sort(pixels);
				
				ret.setRGB(x + windowSize / 2, y + windowSize / 2, pixels[middle]);

			}
		}
		return ret;
	}
	
	/**
	 * Zastosowanie filtru do obrazka. Zalozenie ze filtr jest macierza kwadratowa - jesli nie bedzie to sie wysypie
	 * 
	 * @param image
	 * @param windowSize
	 * @return
	 */
	public static BufferedImage gaussFilter(BufferedImage image, int filter[][] ) {
		BufferedImage ret = makeEmptyGrayImageFromImage(image);
		

		int w = image.getWidth();
		int h = image.getHeight();
		int windowSize = filter.length;
		int norm = 0;
		for (int i = 0; i < windowSize; ++i) {
			for (int j = 0; j < windowSize; ++j) {
				norm += filter[i][j];
			}
		}
		if ( norm == 0)
			norm = 1;

		//dla unikniecia efektu pozostawianej czarnej ramki 
		for (int x = 0; x < w ; ++x) {
			for (int y = 0; y < h ; ++y) {
				ret.setRGB(x, y, levelToGrayscale( rgbToLuminance( image.getRGB(x, y) ) )); 
			}
		}
		for (int x = 0; x < w - windowSize; ++x) {
			for (int y = 0; y < h - windowSize; ++y) {
				int level = 0;
				
				
				for (int i = 0; i < windowSize; ++i) {
					for (int j = 0; j < windowSize; ++j) {
						level += filter[i][j] * rgbToLuminance( image.getRGB(x + i, y + j) );
						
					}
				}
				level = level/norm;
				level = levelToGrayscale(level);

				ret.setRGB(x + windowSize / 2, y + windowSize / 2, level );
				
			}
		}
		
		return ret;
	}

	
	
	
	/**
	 * Zastosowanie laplasjanu do obrazka. Zalozenie ze filtr jest macierza kwadratowa - jesli nie bedzie to sie wysypie
	 * 
	 * @param image
	 * @param filter
	 * @param stdevTreshold 
	 * @param varianceRange - musi byc dodatnie
	 * @return
	 */
	public static BufferedImage laplacianFilter(BufferedImage image, int filter[][], float stdevTreshold, int varianceRange) {
		BufferedImage ret = makeEmptyGrayImageFromImage(image);

		int w = image.getWidth();
		int h = image.getHeight();
		int windowSize = filter.length;
		
		//wyliczenie normy do znomalizowania filtru
		int norm = 0;
		for (int i = 0; i < windowSize; ++i) {
			for (int j = 0; j < windowSize; ++j) {
				norm += filter[i][j];
			}
		}
		if ( norm == 0)
			norm = 1;
		
		
		int in[][] = new int[w][h];
		int out[][] = new int[w][h];
		
		//przepisanie przekonwertowanych na (0..255) wartosci z obrazu image do tablicy
		for (int x = 0; x < w ; ++x) {
			for (int y = 0; y < h ; ++y) {
				in[x][y] = rgbToLuminance( image.getRGB(x, y) ); 
			}
		}
		
		for (int x = 0; x < w - windowSize; ++x) {
			for (int y = 0; y < h - windowSize; ++y) {
				int level = 0;
				
				
				for (int i = 0; i < windowSize; ++i) {
					for (int j = 0; j < windowSize; ++j) {
						level += filter[i][j] * in[x+i][y+j];
						
					}
				}
				out[x + windowSize / 2][ y + windowSize / 2 ] = level;
			}
		}
		
		if ( varianceRange < 1 )
			varianceRange = 1;
		
		double varianceTreshold = stdevTreshold * stdevTreshold; //prog wariancji
		int range = varianceRange*2+1;		//zasieg sprawdzania lokalnej wariancji 
		
		//detekcja przejscia przez 0
		for (int x = range/2; x < w - range/2; ++x) {
			for (int y = range/2; y < h - range/2 ; ++y) {
				int pix = 0;
				
				boolean isEdge = false;
				//sprawdzanie przejscia przez 0
				for (int i = -1; i <= 1; ++i) {
					for (int j = -1; j <= 1; ++j) {
						if ( out[x][y] * out[x+i][y+j] < 0 && Math.abs(out[x][y]) < Math.abs(out[x+i][y+j]) ){
							
							//wyliczanie lokalnej wariancji tego piksela 
							int sumx = 0, sumx2 = 0;
							for (int k = -range/2; k <= range/2; k++) {
								for (int l = -range/2; l <= range/2; l++) {
									sumx += in[x+k][y+l];
									sumx2 += in[x+k][y+l]*in[x+k][y+l];
								}
							}
							double variance = sumx2*1.0/(range*range) - sumx*1.0/(range*range) * sumx*1.0/(range*range);
							
							if ( variance > varianceTreshold ){
								pix = 255;
								isEdge = true;
								break;
							}
							
						}
					}
					//jesli jest krawedzia to juz nie ma sensu liczyc dalej
					if ( isEdge )
						break;
				}
				ret.setRGB( x,y, levelToGrayscale( pix ) );
			}
		}
				
		return ret;
	}
	
	
	
	

	public BufferedImage ourAlgorithmEdgeDetection(BufferedImage image, float varianceTreshold, int varianceRange) {
		
	
		image = gaussFilter(image, gauss2);
		image = laplacianFilter(image, laplacianExp, varianceTreshold, varianceRange);
//		image = medianFilter(image, 3);
		
		return image;
	}
}
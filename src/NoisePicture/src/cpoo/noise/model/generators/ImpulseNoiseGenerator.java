package cpoo.noise.model.generators;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * @author Rafał Radziejewski
 *
 */
public class ImpulseNoiseGenerator {

	private static Random rand = new Random();

	/**
	 * Generacja obrazka z dodanym szumem Gaussa.
	 * 
	 * @param image
	 * @param mean
	 *            - wartość średnia rozkładu
	 * @param variance
	 *            - wariancja
	 * @return
	 */
	public static BufferedImage generatePicture(BufferedImage image, double p1,
			double val1, double p2, double val2) {
		int w = image.getWidth();
		int h = image.getHeight();
		// Obraz wynikowy
		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < w; ++x)
			for (int y = 0; y < h; ++y) {
				int rgb = image.getRGB(x, y);

				// Kolor piksela
				float red = new Color(rgb).getRed();
				float green = new Color(rgb).getGreen();
				float blue = new Color(rgb).getBlue();

				float newRand = rand.nextFloat();
				if (newRand < p1) {
					// Piksel przyjmuje wartość większą o val1
					red += Math.pow(-1, rand.nextInt(6)) * val1;
					green += Math.pow(-1, rand.nextInt(6)) * val1;
					blue += Math.pow(-1, rand.nextInt(6)) * val1;
				}
				if (newRand >= p1 && newRand < p2 + p1) {
					// Piksel przyjmuje wartość większą o val2
					red += Math.pow(-1, rand.nextInt(6)) * val2;
					green += Math.pow(-1, rand.nextInt(6)) * val2;
					blue += Math.pow(-1, rand.nextInt(6)) * val2;
				}
				// W p.p. Piksel zostaje z oryginalną wartością

				// Przycięcie wyniku dodawania
				if (red > 255) {
					red = 255;
				} else if (red < 0) {
					red = 0;
				}
				if (green > 255) {
					green = 255;
				} else if (green < 0) {
					green = 0;
				}
				if (blue > 255) {
					blue = 255;
				} else if (blue < 0) {
					blue = 0;
				}

				// Przeskalowanie do przedziału (0.0 - 1.0);
				Color resultColor = new Color(red / 255, green / 255,
						blue / 255, 1f);

				result.setRGB(x, y, resultColor.getRGB());
			}

		return result;
	}
}

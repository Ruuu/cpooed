package cpoo.noise.model.generators;

import java.awt.Color;
import java.awt.image.BufferedImage;

import cpoo.noise.model.RandomGaussian;

/**
 * 
 * @author Rafał Radziejewski
 *
 */
public class GaussianNoiseGenerator {

	private static RandomGaussian randomGaussian = new RandomGaussian();

	/**
	 * Generacja obrazka z dodanym szumem Gaussa.
	 * @param image
	 * @param mean - wartość średnia rozkładu
	 * @param variance - wariancja
	 * @return
	 */
	public static BufferedImage generatePicture(BufferedImage image,
			double mean, double variance) {
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

				// Szum
				int noise = (int) randomGaussian.getGaussian(mean, variance);

				red += noise;
				green += noise;
				blue += noise;

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

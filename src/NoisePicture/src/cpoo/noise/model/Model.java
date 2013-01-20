package cpoo.noise.model;

import java.awt.image.BufferedImage;
import java.util.Locale;

import cpoo.noise.controller.Controller;
import cpoo.noise.model.generators.GaussianNoiseGenerator;
import cpoo.noise.model.generators.ImpulseNoiseGenerator;

/**
 * 
 * @author Rafał Radziejewski
 * 
 */
public class Model {

	public Model() {

	}

	/**
	 * Generuje obrazki z szumem Gaussowskim w trybie generacji seryjnej. Zadane
	 * są parametry rozkładu normalnego, liczba obrazków do wygenerowania, oraz
	 * modyfikatory parametrów rozkładu z każdym krokiem (kolejnym obrazkiem).
	 * Modyfikatory są w każdej iteracji dodawane do zadanych wartości.
	 * Modyfikatory mogą przyjmować zarówno wartości dodatnie, jak i ujemne.
	 * 
	 * @param image
	 *            - obraz
	 * @param name
	 *            - nazwa pliku z obrazem
	 * @param mean
	 *            - wartość oczekiwana rozkładu normalnego
	 * @param variance
	 *            - wartość wariancji rozkładu normalnego
	 * @param numberOfPictures
	 *            - liczba obrazków do wygenerowania
	 * @param meanStep
	 *            - modyfikator średniej
	 * @param varianceStep
	 *            - modyfikator wariancji
	 */
	public void generateGaussianNoise(BufferedImage image, String name,
			double mean, double variance, int numberOfPictures,
			double meanStep, double varianceStep) {
		for (int i = 0; i < numberOfPictures; i++) {
			generateGaussianNoise(image, name, mean + meanStep * i, variance
					+ varianceStep * i);
			Controller.setProgress(100 * (i + 1) / numberOfPictures);
		}
	}

	/**
	 * Generuje obrazki z szumem Gaussowskim w trybie generacji pojedynczej.
	 * Zadane są parametry rozkładu normalnego.
	 * 
	 * @param image
	 *            - obraz
	 * @param name
	 *            - nazwa pliku z obrazem
	 * @param mean
	 *            - wartość oczekiwana rozkładu normalnego
	 */
	public void generateGaussianNoise(BufferedImage image, String name,
			double mean, double variance) {
		BufferedImage result = GaussianNoiseGenerator.generatePicture(image,
				mean, variance);
		// Odciecie rozszerzenia z nazwy pliku
		name = name.substring(0, name.lastIndexOf('.'));
		name += "_gauss_mean_" + String.format(Locale.ENGLISH, "%.2f%n", mean)
				+ "_var_" + String.format(Locale.ENGLISH, "%.2f%n", variance)
				+ ".png";
		name.replaceAll(" ", "");
		Controller.addImageTab(result, name);
	}

	/**
	 * * Generuje obrazki z szumem impulsowym w trybie generacji seryjnej.
	 * Zadane są parametry szumu impulsowego: dwa prawdopodobieństwa (p)
	 * wystąpienia kolejnych dwóch wartości (v), liczba obrazków do
	 * wygenerowania, oraz modyfikatory parametrów szumu z każdym krokiem
	 * (kolejnym obrazkiem). Modyfikatory są w każdej iteracji dodawane do
	 * zadanych wartości. Modyfikatory mogą przyjmować zarówno wartości
	 * dodatnie, jak i ujemne.
	 * 
	 * @param image
	 *            - obraz
	 * @param name
	 *            - nazwa pliku z obrazem
	 * @param p1
	 *            - prawdopodobieństwo zakłócenia piksela wartością 1. Zakres
	 *            zmiennej - (0-1)
	 * @param v1
	 *            - wartość 1. Sensowne wartości z przedziału (0-255)
	 * @param p2
	 *            - prawdopodobieństwo zakłócenia piksela wartością 2. Zakres
	 *            zmiennej - (0-'1-p1')
	 * @param v2
	 *            - wartość 2. Sensowne wartości z przedziału (0-255)
	 * @param numberOfPictures
	 *            - liczba obrazków do wygenerowania
	 * @param p1Step
	 *            - skok wartości p1
	 * @param v1Step
	 *            - skok wartości p1
	 * @param p2Step
	 *            - skok wartości p2
	 * @param v2Step
	 *            - skok wartości v2
	 */
	public void generateImpulseNoise(BufferedImage image, String name,
			double p1, int v1, double p2, int v2, int numberOfPictures,
			double p1Step, int v1Step, double p2Step, int v2Step) {

		for (int i = 0; i < numberOfPictures; i++) {
			generateImpulseNoise(image, name, p1 + p1Step * i, v1 + v1Step * i,
					p2 + p1Step * i, v2 + v2Step * i);
			Controller.setProgress(100 * (i + 1) / numberOfPictures);
		}

	}

	/**
	 * * Generuje obrazki z szumem impulsowym w trybie generacji pojedynczej.
	 * Zadane są parametry szumu impulsowego: dwa prawdopodobieństwa (p)
	 * wystąpienia kolejnych dwóch wartości (v).
	 * 
	 * @param image
	 *            - obraz
	 * @param name
	 *            - nazwa pliku z obrazem
	 * @param p1
	 *            - prawdopodobieństwo zakłócenia piksela wartością 1. Zakres
	 *            zmiennej - (0-1)
	 * @param v1
	 *            - wartość 1. Sensowne wartości z przedziału (0-255)
	 * @param p2
	 *            - prawdopodobieństwo zakłócenia piksela wartością 2. Zakres
	 *            zmiennej - (0-'1-p1')
	 * @param v2
	 *            - wartość 2. Sensowne wartości z przedziału (0-255)
	 */
	public void generateImpulseNoise(BufferedImage image, String name,
			double p1, int v1, double p2, int v2) {

		BufferedImage result = ImpulseNoiseGenerator.generatePicture(image, p1,
				v1, p2, v2);
		// Odciecie rozszerzenia z nazwy pliku
		name = name.substring(0, name.lastIndexOf('.'));
		Controller.addImageTab(
				result,
				name + "_impulse_p1_"
						+ String.format(Locale.ENGLISH, "%.2f%n", p1) + "_v1_"
						+ v1 + "_p2_"
						+ String.format(Locale.ENGLISH, "%.2f%n", p2) + "_v2_"
						+ v2 + ".png");
	}

}

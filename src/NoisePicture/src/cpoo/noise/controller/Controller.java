package cpoo.noise.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import cpoo.noise.model.Model;
import cpoo.noise.view.View;

/**
 * 
 * @author Rafał Radziejewski
 * 
 */
public class Controller {

	private static Controller instance = null;

	private static Model model;
	private static View view;

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public Controller() {
		model = new Model();
		view = new View();
	}

	/**
	 * Generacja obrazków z szumem gaussa.
	 * 
	 * @param image
	 * @param name
	 * @param mean
	 * @param variance
	 * @param numberOfPictures
	 * @param meanStep
	 * @param varianceStep
	 */
	public static void generateGaussianNoise(BufferedImage image, String name,
			double mean, double variance, int numberOfPictures,
			double meanStep, double varianceStep) {
		model.generateGaussianNoise(image, name, mean, variance,
				numberOfPictures, meanStep, varianceStep);
	}

	/**
	 * Generacja obrazka z szumem gaussa.
	 * 
	 * @param image
	 * @param name
	 * @param mean
	 * @param variance
	 */
	public static void generateGaussianNoise(BufferedImage image, String name,
			double mean, double variance) {
		model.generateGaussianNoise(image, name, mean, variance);
	}

	/**
	 * Generacja obrazków z szumem impulsowym.
	 * 
	 * @param image
	 * @param name
	 * @param p1
	 * @param v1
	 * @param p2
	 * @param v2
	 * @param numberOfPictures
	 * @param p1Step
	 * @param v1Step
	 * @param p2Step
	 * @param v2Step
	 */
	public static void generateImpulseNoise(BufferedImage image, String name,
			double p1, int v1, double p2, int v2, int numberOfPictures,
			double p1Step, int v1Step, double p2Step, int v2Step) {
		model.generateImpulseNoise(image, name, p1, v1, p2, v2,
				numberOfPictures, p1Step, v1Step, p2Step, v2Step);
	}

	/**
	 * Generacja obrazka z szumem impulsowym.
	 * 
	 * @param image
	 * @param name
	 * @param p1
	 * @param v1
	 * @param p2
	 * @param v2
	 */
	public static void generateImpulseNoise(BufferedImage image, String name,
			double p1, int v1, double p2, int v2) {
		model.generateImpulseNoise(image, name, p1, v1, p2, v2);
	}

	/**
	 * Dodanie do okna głównego zakładki z nowo wygenerowanym obrazkiem.
	 * 
	 * @param result
	 * @param title
	 */
	public static void addImageTab(BufferedImage result, String title) {
		try {
			view.getMainWindow().getContent().addExistingItem(result, title);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Update wartości progressbara.
	 * 
	 * @param progress
	 */
	public static void setProgress(int progress) {
		view.setProgres(progress);
	}
}
